package com.benliandamit.studentsapp

import com.benliandamit.studentsapp.model.Student
import java.util.UUID

object StudentRepository {
    private val students = mutableListOf(
        Student("1", "John Doe", "123-456-7890", "123 Main St", false),
        Student("2", "Jane Smith", "987-654-3210", "456 Elm St", false),
        Student("3", "Alice Johnson", "555-123-4567", "789 Oak St", false),
        Student("4", "Bob Brown", "444-555-6666", "101 Pine St", false)
    )

    fun getStudents(): List<Student> = students

    fun getStudent(uuid: String): Student? = students.find { it.uuid.toString() == uuid }

    fun updateStudent(student: Student) {
        val index = students.indexOfFirst { it.uuid == student.uuid }
        if (index != -1) {
            students[index] = student
        }
    }

    fun setStudentChecked(uuid: UUID, isChecked: Boolean) {
        val student = students.find { it.uuid == uuid }
        if (student != null) {
            student.isChecked = isChecked
            updateStudent(student)
        }
    }

    fun isStudentChecked(uuid: UUID): Boolean {
        return students.find { it.uuid == uuid }?.isChecked ?: false
    }

    fun addStudent(student: Student) {
        students.add(student)
    }

    fun deleteStudent(uuid: UUID) {
        students.removeIf { it.uuid == uuid }
    }
}