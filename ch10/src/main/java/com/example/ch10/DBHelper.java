package com.example.ch10;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//앱의ㅣ 데이터베이스 관리적인 코드(table create, scheme update)를 추상화
public class DBHelper extends SQLiteOpenHelper {
    DBHelper(Context context) {
        super(context, "user.db", null, 1);
    }

    //앱이 인스톨된 후 최초 한번 테이블 만듬
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table tb_user(" +
                "_id integer primary key autoincrement," +
                "name text," +
                "address text)");
    }

    //생성자 정보에 명시한 개발자의 db version이 변경될 떄 마다 스키마 변경
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
