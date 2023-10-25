package com.example.noteapp.domain.usecase.impl

import com.example.noteapp.domain.usecase.DeleteAllNoteUseCase
import com.example.noteapp.repository.NoteRepository
import com.example.noteapp.repository.impl.NoteRepositoryImpl

class DeleteAllNoteUseCaseImpl : DeleteAllNoteUseCase {

    private val noteRepository: NoteRepository = NoteRepositoryImpl.getInstance()

    override suspend fun deleteAllNotes() {
        noteRepository.deleteAllNotes()
    }
}