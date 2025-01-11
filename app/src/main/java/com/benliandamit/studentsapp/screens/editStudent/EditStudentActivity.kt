package com.benliandamit.studentsapp.screens.editStudent

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.benliandamit.studentsapp.R
import com.benliandamit.studentsapp.screens.studentDetails.StudentDetailsActivity
import com.benliandamit.studentsapp.dal.StudentRepository
import com.benliandamit.studentsapp.action.ACTIVITY_ACTION_EXTRA_NAME
import com.benliandamit.studentsapp.action.ActivityAction
import com.benliandamit.studentsapp.action.STUDENT_UUID_EXTRA_NAME
import com.benliandamit.studentsapp.action.UPDATED_STUDENT_UUID_EXTRA_NAME
import com.benliandamit.studentsapp.model.Student
import com.benliandamit.studentsapp.screens.studentList.StudentListActivity
import java.util.UUID

class EditStudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_edit_student)

        val studentUUID = intent.getStringExtra(STUDENT_UUID_EXTRA_NAME) ?: "-1"
        val student = StudentRepository.getStudents().find { it.uuid.toString() == studentUUID }

        if (student != null) {
            findViewById<EditText>(R.id.student_name_input).setText(student.name)
            findViewById<EditText>(R.id.student_id_input).setText(student.id)
            findViewById<EditText>(R.id.student_phone_input).setText(student.phone)
            findViewById<EditText>(R.id.student_address_input).setText(student.address)
            val studentChecked: CheckBox = findViewById(R.id.student_checked)
            studentChecked.isChecked = student.isChecked

            studentChecked.setOnCheckedChangeListener { _, isChecked ->
                StudentRepository.setStudentChecked(student.uuid, isChecked)
            }
        }

        findViewById<Button>(R.id.delete_button).setOnClickListener {
            StudentRepository.deleteStudent(UUID.fromString(studentUUID))
            Toast.makeText(this, "Student deleted successfully", Toast.LENGTH_SHORT).show()
            goToList(studentUUID)
        }

        findViewById<Button>(R.id.save_button).setOnClickListener {
            StudentRepository.updateStudent(
                Student(
                    findViewById<EditText>(R.id.student_id_input).text.toString(),
                    findViewById<EditText>(R.id.student_name_input).text.toString(),
                    findViewById<EditText>(R.id.student_phone_input).text.toString(),
                    findViewById<EditText>(R.id.student_address_input).text.toString(),
                    findViewById<CheckBox>(R.id.student_checked).isChecked,
                    UUID.fromString(studentUUID),
                )
            )
            returnToDetails(studentUUID, ActivityAction.UPDATE_STUDENT.name)
        }

        val returnButton: TextView = findViewById(R.id.return_button)
        returnButton.setOnClickListener {
            returnToDetails(studentUUID, ActivityAction.UPDATE_STUDENT.name, RESULT_CANCELED)
        }

        findViewById<Button>(R.id.cancel_button).setOnClickListener {
            returnToDetails(studentUUID, ActivityAction.UPDATE_STUDENT.name, RESULT_CANCELED)
        }
    }

    private fun goToList(studentUUID: String) {
        val intent = Intent(this, StudentListActivity::class.java)
        intent.putExtra(ACTIVITY_ACTION_EXTRA_NAME, ActivityAction.UPDATE_STUDENT.name)
        intent.putExtra(UPDATED_STUDENT_UUID_EXTRA_NAME, studentUUID)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
        finish()
    }

    private fun returnToDetails(studentUUID: String, action: String, resultCode: Int = RESULT_OK) {
        val intent = Intent(this, StudentDetailsActivity::class.java)

        if (resultCode == RESULT_OK) {
            intent.putExtra(UPDATED_STUDENT_UUID_EXTRA_NAME, studentUUID)
            intent.putExtra(ACTIVITY_ACTION_EXTRA_NAME, action)
        }

        setResult(resultCode, intent)
        onBackPressedDispatcher.onBackPressed()
    }
}