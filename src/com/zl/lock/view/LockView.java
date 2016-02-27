package com.zl.lock.view;

import java.util.List;
import java.util.Random;

import com.zl.lock.R;
import com.zl.lock.R.id;
import com.zl.lock.R.layout;
import com.zl.lock.bean.WordBean;
import com.zl.lock.db.dao.Wordsdao;
import com.zl.lock.util.RandomSort;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.PixelFormat;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * ����������
 * 
 * @author ZL 2015.4.20
 * 
 */
public class LockView {
	private static View view = null;
	static Context context;
	static WindowManager windowManager;
	static String word;
	static WordBean wordbean;

	// ---------------�ɰ��İ�ť��------------
	static TextView tv_word;
	static EditText et;
	static Button bt_submit;
	static Button button_1_1;
	static Button button_1_2;
	static Button button_1_3;
	static Button button_1_4;
	static Button button_2_1;
	static Button button_2_2;
	static Button button_2_3;
	static Button button_2_4;

	// ------------------------------

	public static void initLockView(Context ct) {
		context = ct;
		if (view == null) {
			initWord();
			initView();
		}
	}

	// -----------��ʼ����ʾ����-----------------------
	private static void initWord() {
		// ��ѯ�����е���
		Wordsdao ws = new Wordsdao(context);
		List<WordBean> words = ws.findAll();
		if (words.isEmpty()) {
			wordbean = new WordBean(0, "confer", "", "����");
		} else {
			// �����ȡһ������
			wordbean = words.get(getRandom(words.size()));
			// wordbean = new WordBean("����", "confer");
		}

	}

	private static void initView() {

		view = View.inflate(context, R.layout.lock, null);
		tv_word = (TextView) view.findViewById(R.id.tv_word);
		bt_submit = (Button) view.findViewById(R.id.bt_submit);
		button_1_1 = (Button) view.findViewById(R.id.bt_1_1);
		button_1_2 = (Button) view.findViewById(R.id.bt_1_2);
		button_1_3 = (Button) view.findViewById(R.id.bt_1_3);
		button_1_4 = (Button) view.findViewById(R.id.bt_1_4);
		button_2_1 = (Button) view.findViewById(R.id.bt_2_1);
		button_2_2 = (Button) view.findViewById(R.id.bt_2_2);
		button_2_3 = (Button) view.findViewById(R.id.bt_2_3);
		button_2_4 = (Button) view.findViewById(R.id.bt_2_4);
		et = (EditText) view.findViewById(R.id.editText2);
		// ��ʼ��������˼
		tv_word.setText(wordbean.getTranslate());
		// ��ʼ����ť�ı�
		initButtonText(wordbean.getSpell());
		// ----------------------һ�󲨰�ť����¼�-----------------------------------------
		bt_submit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				word = et.getText().toString();
				if (word.equals(wordbean.getSpell())) {
					// Log.i("zl", "word:" + word);
					windowManager.removeView(view);
					view = null;
				} else {
					// Log.i("zl", "word:" + word);
					et.setText("");
				}
			}
		});
		button_1_1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				et.append(button_1_1.getText());
			}
		});
		button_1_2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				et.append(button_1_2.getText());
			}
		});
		button_1_3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				et.append(button_1_3.getText());
			}
		});
		button_1_4.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				et.append(button_1_4.getText());
			}
		});
		button_2_1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				et.append(button_2_1.getText());
			}
		});
		button_2_2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				et.append(button_2_2.getText());
			}
		});
		button_2_3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				et.append(button_2_3.getText());
			}
		});
		button_2_4.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				et.append(button_2_4.getText());
			}
		});
		// --------------------------------------------------------------------------

		windowManager = (WindowManager) context
				.getSystemService(context.WINDOW_SERVICE);
		LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT, LayoutParams.TYPE_SYSTEM_ERROR,
				LayoutParams.FLAG_DISMISS_KEYGUARD
						| LayoutParams.FLAG_FULLSCREEN, PixelFormat.TRANSPARENT);

		windowManager.addView(view, layoutParams);
		KeyguardManager mKeyguardManager = (KeyguardManager) context
				.getSystemService(Context.KEYGUARD_SERVICE);
		KeyguardManager.KeyguardLock mKeyguardLock = mKeyguardManager
				.newKeyguardLock("FxLock");

		mKeyguardLock.disableKeyguard();

	}

	// ----------��ʼ����ť�ı�---------------------------
	private static void initButtonText(String word) {
		// ���浥��������ĸ
		char[] letter = new char[100];
		for (int i = 0; i < word.length(); i++) {
			letter[i] = word.charAt(i);
		}
		// ����˳��
		RandomSort randomsort = new RandomSort(letter, word.length());
		letter = randomsort.changePosition();

		button_1_1.setText(letter[0] + "");
		button_1_2.setText(letter[1] + "");
		button_1_3.setText(letter[2] + "");
		button_1_4.setText(letter[3] + "");
		button_2_1.setText(letter[4] + "");
		button_2_2.setText(letter[5] + "");
		button_2_3.setText(letter[6] + "");
		button_2_4.setText(letter[7] + "");

	}

	/**
	 * ��ȡһ�������
	 * 
	 * @param num
	 *            �����С
	 * @return
	 */
	private static int getRandom(int num) {
		Random random = new Random();
		return random.nextInt(num);

	}
}
