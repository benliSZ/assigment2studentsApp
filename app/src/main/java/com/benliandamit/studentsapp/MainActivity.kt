package com.benliandamit.studentsapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addStudentButton: Button = findViewById(R.id.add_student_button)
        addStudentButton.setOnClickListener {
            val intent = Intent(this, StudentListActivity::class.java)
            startActivity(intent)
        }
    }
}