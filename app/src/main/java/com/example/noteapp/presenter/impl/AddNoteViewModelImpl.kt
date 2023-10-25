package com.example.noteapp.presenter.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.data.models.NoteData
import com.example.noteapp.presenter.AddNoteViewModel
import com.example.noteapp.repository.impl.NoteRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddNoteViewModelImpl : AddNoteViewModel, ViewModel() {

    private val repository = NoteRepositoryImpl.getInstance()


    override val noteDataLive: LiveData<NoteData> = MutableLiveData()

    override fun addToBase(noteData: NoteData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addNote(noteData)
        }    }
}