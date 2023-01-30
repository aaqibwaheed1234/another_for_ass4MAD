package com.example.assignment04

import android.content.Context
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class Database(val context: Context) {
    val database=FirebaseDatabase.getInstance().reference
    fun Insert(obj:students_data){
        database.child("objects").child(obj.reg_no).setValue(obj).addOnSuccessListener {
            Toast.makeText(context, "Added Successfully", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{
            Toast.makeText(context, "Cannot Added...", Toast.LENGTH_SHORT).show()
        }
    }

    fun Delete_Student(reg_no: String){
        database.child("objects").child(reg_no).removeValue().addOnSuccessListener {
            Toast.makeText(context, "Deleted Successfully...", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{
            Toast.makeText(context, "Cannot Deleted...", Toast.LENGTH_SHORT).show()
        }
    }

}