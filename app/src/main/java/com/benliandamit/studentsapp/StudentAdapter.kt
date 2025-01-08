package com.benliandamit.studentsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StudentAdapter(
    private val students: List<Student>,
    private val onItemClick: (Student) -> Unit
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_student, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]
        holder.bind(student)
    }

    override fun getItemCount(): Int = students.size

    inner class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val studentName: TextView = itemView.findViewById(R.id.student_name)
        private val studentId: TextView = itemView.findViewById(R.id.student_id)
        private val studentCheckbox: CheckBox = itemView.findViewById(R.id.student_checkbox)

        fun bind(student: Student) {
            studentName.text = student.name
            studentId.text = student.id.toString()
            studentCheckbox.isChecked = StudentRepository.isStudentChecked(student.uuid)
            itemView.setOnClickListener { onItemClick(student) }
            studentCheckbox.setOnCheckedChangeListener { _, isChecked ->
                StudentRepository.setStudentChecked(student.uuid, isChecked)
            }
        }
    }
}