package com.example.assignment04

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class AddNewStudent : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_student)

        var name=findViewById<EditText>(R.id.name)
        var regNo=findViewById<EditText>(R.id.reg_no)
        var age=findViewById<EditText>(R.id.age)
        var cgpa=findViewById<EditText>(R.id.cgpa)
        var phone=findViewById<EditText>(R.id.phone)
        var db_obj=Database(this)
        findViewById<Button>(R.id.save).setOnClickListener {
            var obj=students_data(name.text.toString(),regNo.text.toString(),age.text.toString(),cgpa.text.toString(),phone.text.toString())
            db_obj.Insert(obj)
            finish()
        }
    }

}