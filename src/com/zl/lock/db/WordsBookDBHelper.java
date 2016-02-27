package com.zl.lock.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * ����SQLiteOpenHelper�� WordsBookDBHelper
 * 
 * @author zl
 * 
 */
public class WordsBookDBHelper extends SQLiteOpenHelper {
	/**
	 * Ĭ�ϴ������ʱ����ݿ�words.db �汾��v1.0
	 * 
	 * @param context
	 */
	public WordsBookDBHelper(Context context) {
		super(context, MNameSpace.DBFName, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// ��ʼ����Ľṹ
		String sql = "create table words (id integer primary key autoincrement,spell varchar,translate varchar,content varchar)";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO �Զ����ɵķ������

	}

}
