package com.example.noteapp.presenter

import androidx.lifecycle.LiveData
import com.example.noteapp.data.models.NoteData

interface AddNoteViewModel {
    val noteDataLive: LiveData<NoteData>

    fun addToBase(noteData: NoteData)

}