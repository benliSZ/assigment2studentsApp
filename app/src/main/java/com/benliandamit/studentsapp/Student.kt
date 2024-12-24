package com.benliandamit.studentsapp

data class Student(
    val id: Int,
    val name: String,
    val phone: String,
    val address: String,
    var isChecked: Boolean = false
)