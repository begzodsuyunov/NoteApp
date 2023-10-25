package com.example.noteapp.domain.usecase.impl

import com.example.noteapp.data.models.NoteData
import com.example.noteapp.domain.usecase.DeleteNoteUseCase
import com.example.noteapp.repository.NoteRepository
import com.example.noteapp.repository.impl.NoteRepositoryImpl

class DeleteNoteUseCaseImpl : DeleteNoteUseCase {

    private val noteRepository: NoteRepository = NoteRepositoryImpl.getInstance()


    override suspend fun deleteNote(noteData: NoteData) {
        noteRepository.deleteNote(noteData)
    }
}