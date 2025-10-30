package com.pw.satisfactionsurveyapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AdminSQLiteOpenHelper(context: Context,
                            name: String,
                            factory: SQLiteDatabase.CursorFactory?,
                            version: Int
) : SQLiteOpenHelper(context,name, factory, version) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table encuesta(id INT PRIMARY KEY AUTOINCREMENT, pregunta1 INT, preguna2 INT, pregunta3 INT, pregunta4 INT, pregunta5 INT)")
    }

    override fun onUpgrade(
        db: SQLiteDatabase?,
        oldVersion: Int,
        newVersion: Int
    ) {

    }
}