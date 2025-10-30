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
        db?.execSQL("create table encuesta(id INTEGER PRIMARY KEY AUTOINCREMENT, pregunta1 INTEGER, pregunta2 INTEGER, pregunta3 INTEGER, pregunta4 INTEGER, pregunta5 INTEGER)")
    }

    override fun onUpgrade(
        db: SQLiteDatabase?,
        oldVersion: Int,
        newVersion: Int
    ) {

    }
}