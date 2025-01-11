package com.benliandamit.studentsapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import android.widget.CheckBox
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.benliandamit.studentsapp.action.ACTIVITY_ACTION_EXTRA_NAME
import com.benliandamit.studentsapp.action.ActivityAction
import com.benliandamit.studentsapp.action.STUDENT_UUID_EXTRA_NAME
import com.benliandamit.studentsapp.action.UPDATED_STUDENT_UUID_EXTRA_NAME

class StudentDetailsActivity : AppCompatActivity() {
    private lateinit var activityLauncher: ActivityResultLauncher<Intent>
    private val viewModel: StudentDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)

        val studentUUID = intent.getStringExtra(STUDENT_UUID_EXTRA_NAME) ?: ""
        viewModel.setSelectedStudent(studentUUID)

        activityLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    val action = result.data?.getStringExtra(ACTIVITY_ACTION_EXTRA_NAME)

                    if (action == ActivityAction.UPDATE_STUDENT.name) {
                        viewModel.setSelectedStudent(studentUUID)
                    }
                }
            }

        val returnButton: TextView = findViewById(R.id.return_button)
        returnButton.setOnClickListener {
            returnToList(studentUUID)
        }

        val studentChecked: CheckBox = findViewById(R.id.student_checked)
        studentChecked.setOnCheckedChangeListener { _, isChecked ->
            viewModel.updateStudentChecked(isChecked)
        }

        viewModel.selectedStudent.observe(this) { student ->
            findViewById<ImageView>(R.id.student_image).setImageResource(R.drawable.ic_student)
            findViewById<TextView>(R.id.student_name).text = "Name: ${student.name}"
            findViewById<TextView>(R.id.student_id).text = "ID: ${student.id}"
            findViewById<TextView>(R.id.student_phone).text = "Phone: ${student.phone}"
            findViewById<TextView>(R.id.student_address).text = "Address: ${student.address}"
            studentChecked.isChecked = student.isChecked
        }

        val editButton: TextView = findViewById(R.id.edit_button)
        editButton.setOnClickListener {
            val intent = Intent(this, EditStudentActivity::class.java)
            intent.putExtra(STUDENT_UUID_EXTRA_NAME, studentUUID)
            activityLauncher.launch(intent)
        }
    }

    private fun returnToList(studentUUID: String) {
        val intent = Intent(this, StudentListActivity::class.java)
        intent.putExtra(ACTIVITY_ACTION_EXTRA_NAME, ActivityAction.UPDATE_STUDENT.name)
        intent.putExtra(UPDATED_STUDENT_UUID_EXTRA_NAME, studentUUID)
        setResult(RESULT_OK, intent)
        onBackPressedDispatcher.onBackPressed()
    }
}