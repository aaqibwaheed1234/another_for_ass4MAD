package com.example.assignment04

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Database(context: Context, dbName: String) : SQLiteOpenHelper(context, dbName, null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        var table="create table info(name varchar(20),reg_no varchar(20),age varchar(20),cgpa varchar(20), phone varchar(20));"
        db?.execSQL(table)
    }
    fun Insert(obj:students_data){
        var values=ContentValues()
        values.put("name",obj.name)
        values.put("reg_no",obj.reg_no)
        values.put("age",obj.age)
        values.put("cgpa",obj.cgpa)
        values.put("phone",obj.phone)
        writableDatabase.insert("info",null,values)
    }

    fun Delete_Student(reg_no: String){
        writableDatabase.delete("info","reg_no=?", arrayOf(reg_no))
    }

    fun Fetch_Student_Data():ArrayList<students_data>{
        var list=ArrayList<students_data>()
        var fetch="select * from info;"
        var return_data=readableDatabase.rawQuery(fetch,null)
        if(return_data.moveToFirst()){
            do {
                var name=return_data.getString(0)
                var reg_no=return_data.getString(1)
                var age=return_data.getString(2)
                var cgpa=return_data.getString(3)
                var phone=return_data.getString(4)
                var obj=students_data(name,reg_no,age,cgpa,phone)
                list.add(obj)
            }while (return_data.moveToNext())
        }
        return list
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}