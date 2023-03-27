package com.example.splashscreenlastproject.helper

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.example.splashscreenlastproject.model.Article

val DATABASENAME = "crud_article"
val TABLENAME = "article"
val COL_ID  = "id"
val COL_TITLE = "title"
val COL_CREATOR = "creator"
val COL_DESCRIPTION = "description"
val COL_IMAGE = "image"


    class DBHelper(val context: Context): SQLiteOpenHelper(context, DATABASENAME,null,1) {
        override fun onCreate(p0: SQLiteDatabase?) {
            val createTable = "CREATE TABLE " + TABLENAME + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_TITLE + " VARCHAR(50)," + COL_DESCRIPTION + " TEXT, " +
                    COL_CREATOR + " VARCHAR(50), " + COL_IMAGE + " TEXT )"
            p0?.execSQL(createTable)
        }

        override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
            TODO("Not yet implemented")
        }

        fun insertData(article: Article) {
            val database = this.writableDatabase
            val contentValues = ContentValues()
            contentValues.put(COL_TITLE, article.title)
            contentValues.put(COL_CREATOR, article.creator)
            contentValues.put(COL_DESCRIPTION, article.description)
            contentValues.put(COL_IMAGE, article.image)
            val result = database.insert(TABLENAME, null, contentValues)
            if (result == (0).toLong()) {
                Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
            }
        }

        @SuppressLint("Range")
        fun readData(): ArrayList<Article> {
            val list: ArrayList<Article> = ArrayList()
            val db = this.readableDatabase
            val query = "Select * from $TABLENAME"
            val result = db.rawQuery(query, null)
            if (result.moveToFirst()) {
                do {
                    val id = result.getString(result.getColumnIndex(COL_ID))
                    val title = result.getString(result.getColumnIndex(COL_TITLE))
                    val creator = result.getString(result.getColumnIndex(COL_CREATOR))
                    val description = result.getString(result.getColumnIndex(COL_DESCRIPTION))
                    val image = result.getString(result.getColumnIndex(COL_IMAGE))
                    val article = Article(id, title, creator, description, image)
                    list.add(article)
                }
                while (result.moveToNext())
            }
            return list
        }

        fun deleteData(id: Int?){
            val db = this.readableDatabase
            db.execSQL("delete from ${TABLENAME} where ${COL_ID} = '"+id+"'")
        }

    }
