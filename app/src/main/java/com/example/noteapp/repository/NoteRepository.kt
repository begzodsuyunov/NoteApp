package com.example.noteapp.repository

import com.example.noteapp.data.models.FilterData
import com.example.noteapp.data.models.NoteData
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    suspend fun addNote(noteData: NoteData)

    suspend fun updateNote(noteData: NoteData)

    suspend fun deleteNote(noteData: NoteData)

    suspend fun deleteAllNotes()

    fun getAllNotes(): Flow<List<NoteData>>

    fun getByTag(filterData: FilterData): Flow<List<NoteData>>
}