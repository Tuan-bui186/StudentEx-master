package com.example.studentex

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class StudentAdapter(
    private val context: Context,
    private val students: ArrayList<Student>
) : BaseAdapter() {

    override fun getCount(): Int = students.size

    override fun getItem(position: Int): Any = students[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_student, parent, false)

        val student = students[position]
        val textName = view.findViewById<TextView>(R.id.textName)
        val textMSSV = view.findViewById<TextView>(R.id.textMSSV)

        textName.text = student.name
        textMSSV.text = student.mssv

        return view
    }
}