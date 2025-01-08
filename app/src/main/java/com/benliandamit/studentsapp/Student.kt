package com.benliandamit.studentsapp

import java.util.UUID

data class Student(
    val id: String,
    val name: String,
    val phone: String,
    val address: String,
    var isChecked: Boolean = false,
    val uuid: UUID = UUID.randomUUID()
)