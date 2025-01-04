package com.benliandamit.studentsapp

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AddStudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_student)

        val returnButton: TextView = findViewById(R.id.return_button)
        returnButton.setOnClickListener {
            setResult(RESULT_OK)
            onBackPressedDispatcher.onBackPressed()
        }
    }
}