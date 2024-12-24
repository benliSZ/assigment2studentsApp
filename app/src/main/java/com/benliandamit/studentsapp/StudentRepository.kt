package com.benliandamit.studentsapp

object StudentRepository {
    private val students = mutableListOf<Student>()

    fun getStudents(): List<Student> {
        // Return a list of hard-coded students
        return listOf(
            Student(1, "John Doe", "123-456-7890", "123 Main St"),
            Student(2, "Jane Smith", "987-654-3210", "456 Elm St"),
            Student(3, "Alice Johnson", "555-123-4567", "789 Oak St"),
            Student(4, "Bob Brown", "444-555-6666", "101 Pine St")
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