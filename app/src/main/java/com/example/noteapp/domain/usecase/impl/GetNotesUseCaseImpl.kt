package com.example.noteapp.domain.usecase.impl

import com.example.noteapp.data.models.NoteData
import com.example.noteapp.domain.usecase.GetNotesUseCase
import com.example.noteapp.repository.NoteRepository
import com.example.noteapp.repository.impl.NoteRepositoryImpl
import kotlinx.coroutines.flow.Flow

class GetNotesUseCaseImpl : GetNotesUseCase{

    private val noteRepository: NoteRepository = NoteRepositoryImpl.getInstance()

    override fun getAllNotes(): Flow<List<NoteData>> = noteRepository.getAllNotes()
}