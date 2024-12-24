package com.benliandamit.studentsapp

object StudentRepository {
    private val students = mutableListOf<Student>()

    fun getStudents(): List<Student> {
        // Return a list of hard-coded students
        return listOf(
            Student(1, "John Doe"),
            Student(2, "Jane Smith"),
            Student(3, "Alice Johnson"),
            Student(4, "Bob Brown")
        )
    }

    fun addStudent(student: Student) {
        students.add(student)
    }

    fun updateStudent(student: Student) {
        val index = students.indexOfFirst { it.id == student.id }
        if (index != -1) {
            students[index] = student
        }
    }
}