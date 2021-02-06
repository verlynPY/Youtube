package com.example.youtube.model.Search

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import android.provider.BaseColumns._ID
import com.example.youtube.model.Search.DataBaseContainer.Busqueda.Companion.TABLE_NAME
import com.example.youtube.model.Search.DataBaseContainer.Busqueda.Companion.TITLE

class DataBaseHelper(context: Context): SQLiteOpenHelper(context, "Search.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val busqueda = "CREATE TABLE " +
                TABLE_NAME + "(" +
                BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                TITLE + " TEXT" +")"
        db!!.execSQL(busqueda)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun InsertData(Title: String): Boolean{
        val db: SQLiteDatabase = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(TITLE, Title)
        val insert_data = db.insert(TABLE_NAME, null, contentValues)
        db.close()
        return !insert_data.equals(-1)
    }

    fun ReadData(): Cursor {
        val db: SQLiteDatabase = this.writableDatabase
        val read: Cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        return read
    }

    fun DeleteData(Id: String): Boolean{
        val db: SQLiteDatabase = this.writableDatabase
        val delete = db.delete(TABLE_NAME, "$_ID=?", arrayOf(Id))
        return !delete.equals(-1)
    }



}