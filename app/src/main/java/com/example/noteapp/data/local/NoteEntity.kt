package com.example.noteapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.noteapp.data.models.NoteData

@Entity
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val title: String,
    val description: String,
    val date: String,
    val colorNumber: Int,
    val high: Boolean = false,
    val medium: Boolean = false,
    val simple: Boolean = false
) {
    fun toNoteData() = NoteData(id, title, description, date, colorNumber, high, medium, simple)
}