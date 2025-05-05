package com.example.studentex

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.studentex.ui.theme.StudentEXTheme

class MainActivity : ComponentActivity() {
    private lateinit var editName: EditText
    private lateinit var editMSSV: EditText
    private lateinit var listView: ListView
    private lateinit var studentAdapter: StudentAdapter
    private val studentList = ArrayList<Student>()
    private var selectedIndex = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editName = findViewById(R.id.editName)
        editMSSV = findViewById(R.id.editMSSV)
        listView = findViewById(R.id.listView)

        val btnAdd = findViewById<Button>(R.id.buttonAdd)
        val btnUpdate = findViewById<Button>(R.id.buttonUpdate)
        val btnDelete = findViewById<Button>(R.id.buttonDelete)

        studentAdapter = StudentAdapter(this, studentList)
        listView.adapter = studentAdapter

        btnAdd.setOnClickListener {
            val name = editName.text.toString()
            val mssv = editMSSV.text.toString()
            if (name.isNotEmpty() && mssv.isNotEmpty()) {
                studentList.add(Student(name, mssv))
                studentAdapter.notifyDataSetChanged()
                clearFields()
            }
        }

        listView.setOnItemClickListener { _, _, position, _ ->
            val student = studentList[position]
            editName.setText(student.name)
            editMSSV.setText(student.mssv)
            selectedIndex = position
        }

        btnUpdate.setOnClickListener {
            if (selectedIndex != -1) {
                studentList[selectedIndex] = Student(editName.text.toString(), editMSSV.text.toString())
                studentAdapter.notifyDataSetChanged()
                clearFields()
                selectedIndex = -1
            }
        }

        btnDelete.setOnClickListener {
            if (selectedIndex != -1) {
                studentList.removeAt(selectedIndex)
                studentAdapter.notifyDataSetChanged()
                clearFields()
                selectedIndex = -1
            }
        }
    }

    private fun clearFields() {
        editName.text.clear()
        editMSSV.text.clear()
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    StudentEXTheme {
        Greeting("Android")
    }
}