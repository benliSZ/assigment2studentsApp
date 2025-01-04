package com.benliandamit.studentsapp

object StudentRepository {
    private val students = mutableListOf(
        Student("1", "John Doe", "123-456-7890", "123 Main St", false),
        Student("2", "Jane Smith", "987-654-3210", "456 Elm St", false),
        Student("3", "Alice Johnson", "555-123-4567", "789 Oak St", false),
        Student("4", "Bob Brown", "444-555-6666", "101 Pine St", false)
    )

    fun getStudents(): List<Student> = students

    fun updateStudent(student: Student) {
        val index = students.indexOfFirst { it.id == student.id }
        if (index != -1) {
            students[index] = student
        }
    }

    fun setStudentChecked(id: String, isChecked: Boolean) {
        val student = students.find { it.id == id }
        if (student != null) {
            student.isChecked = isChecked
            updateStudent(student)
        }
    }

    fun isStudentChecked(id: String): Boolean {
        return students.find { it.id == id }?.isChecked ?: false
    }
}