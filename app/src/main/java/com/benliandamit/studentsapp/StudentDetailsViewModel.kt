package com.benliandamit.studentsapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.benliandamit.studentsapp.model.Student

class StudentDetailsViewModel : ViewModel() {
    private val _selectedStudent = MutableLiveData<Student>()
    val selectedStudent: LiveData<Student> get() = _selectedStudent

    fun setSelectedStudent(studentUUID: String) {
        _selectedStudent.value = StudentRepository.getStudent(studentUUID)
    }

    fun updateStudentChecked(isChecked: Boolean) {
        val updatedStudent = _selectedStudent.value

        if (updatedStudent == null) {
            return
        }

        updatedStudent.isChecked = isChecked
        StudentRepository.updateStudent(updatedStudent)
        setSelectedStudent(updatedStudent.uuid.toString())
    }
}