package com.benliandamit.studentsapp

data class Student(
    val id: Int,
    val name: String,
    var isChecked: Boolean = false
)