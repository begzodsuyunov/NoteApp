package com.example.noteapp.domain.usecase

import com.example.noteapp.data.models.NoteData
import kotlinx.coroutines.flow.Flow

interface GetNotesUseCase {

    fun getAllNotes(): Flow<List<NoteData>>

}