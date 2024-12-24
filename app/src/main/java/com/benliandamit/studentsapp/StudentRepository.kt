package com.benliandamit.studentsapp

object StudentRepository {
    private val students = mutableListOf<Student>()

    fun getStudents(): List<Student> = students

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