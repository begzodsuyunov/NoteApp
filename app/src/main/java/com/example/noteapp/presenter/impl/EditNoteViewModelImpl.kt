package com.example.noteapp.presenter.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.data.models.NoteData
import com.example.noteapp.presenter.EditNoteViewModel
import com.example.noteapp.repository.impl.NoteRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditNoteViewModelImpl : EditNoteViewModel, ViewModel() {

    private val repository = NoteRepositoryImpl.getInstance()


    override fun update(noteData: NoteData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateNote(noteData)
        }    }

    override fun delete(noteData: NoteData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteNote(noteData)
        }    }
}