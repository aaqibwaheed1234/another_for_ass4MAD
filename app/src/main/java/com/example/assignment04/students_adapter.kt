package com.example.assignment04

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class students_adapter(context: Context,list:ArrayList<students_data>):BaseAdapter() {
    var context:Context
    var attr:ArrayList<students_data>
    init {
        this.attr=list
        this.context=context
    }
    override fun getCount(): Int {
       return attr.size
    }

    override fun getItem(position: Int): Any {
        return attr[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view=LayoutInflater.from(context).inflate(R.layout.students_resource_file,null,false)
        view.findViewById<TextView>(R.id.name).text=attr[position].name
        view.findViewById<TextView>(R.id.reg_no).text=attr[position].reg_no
        return view
    }
}