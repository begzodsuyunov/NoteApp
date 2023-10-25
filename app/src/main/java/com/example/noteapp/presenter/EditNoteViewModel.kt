package com.example.noteapp.presenter

import com.example.noteapp.data.models.NoteData

interface EditNoteViewModel {
    fun update(noteData: NoteData)

    fun delete(noteData: NoteData)
}