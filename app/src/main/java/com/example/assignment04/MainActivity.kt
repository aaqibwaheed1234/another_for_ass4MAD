package com.example.assignment04

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var listView=findViewById<ListView>(R.id.listView)

        var dbobj=Database(this,"Assignment04")
        var adapter_obj=students_adapter(this,dbobj.Fetch_Student_Data())
        listView.adapter=adapter_obj

        listView.setOnItemClickListener{_,view,_,_->
            var dialog=AlertDialog.Builder(this)
            dialog.setTitle("Conformation Dialog")
            dialog.setMessage("Do you want to delete?")
            dialog.setNegativeButton("No"){_,_->}
            dialog.setPositiveButton("Yes"){_,_->
                dbobj.Delete_Student(view.findViewById<TextView>(R.id.reg_no).text.toString())
                var adapter_obj=students_adapter(this,dbobj.Fetch_Student_Data())
                listView.adapter=adapter_obj
            }
            dialog.show()
        }

        findViewById<FloatingActionButton>(R.id.add_new_student).setOnClickListener {
            val intent=Intent(this, AddNewStudent::class.java)
            startActivity(intent)
        }
    }
}