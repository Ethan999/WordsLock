package com.zl.lock.db.dao;

import java.util.ArrayList;
import java.util.List;

import com.zl.lock.bean.WordBean;
import com.zl.lock.db.MNameSpace;
import com.zl.lock.db.WordsBookDBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * ʵ���˶�words���ݿ�ĸ��ֲ���
 * 
 * @author zl
 * 
 */
public class Wordsdao {
	private SQLiteOpenHelper help;

	/**
	 * ��ɶ�WordsBookDBHelper�ĳ�ʼ��
	 * 
	 * @param context
	 */
	public Wordsdao(Context context) {
		help = new WordsBookDBHelper(context);
	}

	/**
	 * 
	 * @param spell����
	 * @param translate����
	 * @param content��ע
	 * @return -1��ʾʧ��
	 */
	public long add(String spell, String translate, String content) {
		ContentValues values = new ContentValues();
		values.put("spell", spell);
		values.put("translate", translate);
		values.put("content", content);
		SQLiteDatabase database = help.getWritableDatabase();
		long result = database.insert(MNameSpace.DBName, null, values);
		database.close();
		return result;
	}

	/**
	 * ɾ������
	 * @param spell
	 *            ����
	 * @return 0��ʾʧ��
	 */
	public long delet(String spell) {
		SQLiteDatabase database = help.getWritableDatabase();
		long result = database.delete(MNameSpace.DBName, "spell = ?",
				new String[] { spell });
		database.close();
		return result;
	}

	/**
	 * ��ѯ���е���
	 * 
	 * @return Word��ļ���
	 */
	public List<WordBean> findAll() {
		SQLiteDatabase database = help.getWritableDatabase();
		List<WordBean> words = new ArrayList<WordBean>();
		Cursor cursor = database.query(MNameSpace.DBName, null, null, null,
				null, null, null);
		while (cursor.moveToNext()) {
			int id = cursor.getInt(cursor.getColumnIndex("id"));
			String spell = cursor.getString(cursor.getColumnIndex("spell"));
			String translate = cursor.getString(cursor
					.getColumnIndex("translate"));
			String content = cursor.getString(cursor.getColumnIndex("content"));
			WordBean word = new WordBean(id, spell, content, translate);
			words.add(word);
		}
		database.close();
		return words;
	}
}
