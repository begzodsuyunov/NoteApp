package com.example.noteapp.domain.usecase

import com.example.noteapp.data.models.NoteData

interface DeleteNoteUseCase {
    suspend fun deleteNote(noteData: NoteData)

}