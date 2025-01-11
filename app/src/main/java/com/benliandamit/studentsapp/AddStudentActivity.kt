package com.benliandamit.studentsapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.benliandamit.studentsapp.action.ACTIVITY_ACTION_EXTRA_NAME
import com.benliandamit.studentsapp.action.ActivityAction
import com.benliandamit.studentsapp.model.Student

class AddStudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_student)

        val returnButton: TextView = findViewById(R.id.return_button)
        setGoBackClickListener(returnButton)
        val cancelButton: Button = findViewById(R.id.cancel_button)
        setGoBackClickListener(cancelButton)

        val saveButton = findViewById<Button>(R.id.save_button)
        saveButton.setOnClickListener {
            val name = findViewById<EditText>(R.id.student_name_input)
            val id = findViewById<EditText>(R.id.student_id_input)
            val phone = findViewById<EditText>(R.id.student_phone_input)
            val address = findViewById<EditText>(R.id.student_address_input)
            val isChecked = findViewById<CheckBox>(R.id.student_checked)

            val student = Student(
                id = id.text.toString(),
                name = name.text.toString(),
                phone = phone.text.toString(),
                address = address.text.toString(),
                isChecked = isChecked.isChecked
            )

            StudentRepository.addStudent(student)

            returnToList()
        }
    }

    fun setGoBackClickListener(view: View) {
        view.setOnClickListener {
            returnToList()
        }
    }

    private fun returnToList() {
        val intent = Intent(this, StudentListActivity::class.java)
        intent.putExtra(ACTIVITY_ACTION_EXTRA_NAME, ActivityAction.ADD_STUDENT.name)
        setResult(RESULT_OK, intent)
        onBackPressedDispatcher.onBackPressed()
    }
}