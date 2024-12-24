package com.benliandamit.studentsapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class StudentListActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_list)

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = StudentAdapter(StudentRepository.getStudents()) { student ->
            val intent = Intent(this, StudentDetailsActivity::class.java)
            intent.putExtra("student_id", student.id)
            startActivity(intent)
        }
        recyclerView.adapter = adapter
    }
}