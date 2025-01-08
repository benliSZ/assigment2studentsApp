package com.benliandamit.studentsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import android.widget.CheckBox
import java.util.UUID

class StudentDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)

        val returnButton: TextView = findViewById(R.id.return_button)
        returnButton.setOnClickListener {
            setResult(RESULT_OK)
            onBackPressedDispatcher.onBackPressed()
        }

        val studentUUID = intent.getStringExtra("student_uuid") ?: "-1"
        val student = StudentRepository.getStudents().find { it.uuid.toString() == studentUUID }

        if (student != null) {
            findViewById<ImageView>(R.id.student_image).setImageResource(R.drawable.ic_student)
            findViewById<TextView>(R.id.student_name).text = "Name: ${student.name}"
            findViewById<TextView>(R.id.student_id).text = "ID: ${student.id}"
            findViewById<TextView>(R.id.student_phone).text = "Phone: ${student.phone}"
            findViewById<TextView>(R.id.student_address).text = "Address: ${student.address}"
            val studentChecked: CheckBox = findViewById(R.id.student_checked)
            studentChecked.isChecked = StudentRepository.isStudentChecked(student.uuid)

            studentChecked.setOnCheckedChangeListener { _, isChecked ->
                StudentRepository.setStudentChecked(student.uuid, isChecked)
            }
        }
    }
}