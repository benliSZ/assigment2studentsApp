package com.benliandamit.studentsapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.benliandamit.studentsapp.action.ACTIVITY_ACTION_EXTRA_NAME
import com.benliandamit.studentsapp.action.ActivityAction
import com.benliandamit.studentsapp.action.UPDATED_STUDENT_UUID_EXTRA_NAME
import com.google.android.material.floatingactionbutton.FloatingActionButton

class StudentListActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: StudentAdapter
    private lateinit var activityLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_list)

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = StudentAdapter(StudentRepository.getStudents()) { student ->
            val intent = Intent(this, StudentDetailsActivity::class.java)
            intent.putExtra("student_uuid", student.uuid.toString())
            activityLauncher.launch(intent)
        }
        recyclerView.adapter = adapter

        activityLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    val data: Intent? = result.data
                    val activityAction = data?.getStringExtra(ACTIVITY_ACTION_EXTRA_NAME)

                    when (activityAction) {
                        ActivityAction.ADD_STUDENT.name -> {
                            adapter.notifyItemInserted(StudentRepository.getStudents().size - 1)
                        }

                        ActivityAction.UPDATE_STUDENT.name -> {
                            val studentUUID = data.getStringExtra(UPDATED_STUDENT_UUID_EXTRA_NAME)
                            val studentIndex = StudentRepository.getStudents()
                                .indexOfFirst { it.uuid.toString() == studentUUID }
                            adapter.notifyItemChanged(studentIndex)
                        }
                    }

                }
            }

        var addStudentButton = findViewById<FloatingActionButton>(R.id.add_student_button)
        addStudentButton.setOnClickListener {
            val intent = Intent(this, AddStudentActivity::class.java)
            activityLauncher.launch(intent)
        }
    }
}