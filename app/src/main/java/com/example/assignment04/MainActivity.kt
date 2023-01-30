package com.example.assignment04

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var listView=findViewById<ListView>(R.id.listView)
        var dbobj=Database(this)
        var studentList=ArrayList<students_data>()

        FirebaseDatabase.getInstance().getReference("objects").addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    studentList.clear()
                    for(i in snapshot.children){
                        val obj=i.getValue(students_data::class.java)
                        studentList.add(obj!!)
                    }
                    var adapter_obj=students_adapter(applicationContext, studentList)
                    listView.adapter=adapter_obj
                }
            }
            override fun onCancelled(error: DatabaseError) {}
        })

        listView.setOnItemClickListener{_,view,_,_->
            var dialog=AlertDialog.Builder(this)
            dialog.setTitle("Conformation Dialog")
            dialog.setMessage("Do you want to delete?")
            dialog.setNegativeButton("No"){_,_->}
            dialog.setPositiveButton("Yes"){_,_->
                dbobj.Delete_Student(view.findViewById<TextView>(R.id.reg_no).text.toString())
            }
            dialog.show()
        }
        findViewById<FloatingActionButton>(R.id.add_new_student).setOnClickListener {
            val intent=Intent(this, AddNewStudent::class.java)
            startActivity(intent)
        }
    }
}