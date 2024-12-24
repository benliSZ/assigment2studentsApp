package com.benliandamit.studentsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView

class StudentDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)

        val studentId = intent.getIntExtra("student_id", -1)
        val student = StudentRepository.getStudents().find { it.id == studentId }

        if (student != null) {
            findViewById<ImageView>(R.id.student_image).setImageResource(R.drawable.ic_student)
            findViewById<TextView>(R.id.student_name).text = student.name
            findViewById<TextView>(R.id.student_id).text = student.id.toString()
        }
    }
}