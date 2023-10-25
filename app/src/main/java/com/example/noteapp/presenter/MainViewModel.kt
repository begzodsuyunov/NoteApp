package com.example.noteapp.presenter

import androidx.lifecycle.LiveData
import com.example.noteapp.data.models.FilterData
import com.example.noteapp.data.models.NoteData

interface MainViewModel {

    val openAddNoteLiveData: LiveData<Unit>
    val openEditNoteLiveData: LiveData<NoteData>
    val showDeleteDialogLiveData: LiveData<NoteData>

    val notesListLivedata: LiveData<List<NoteData>>

    fun openAddNoteScreen()

    fun openEditNoteScreen(data: NoteData)

    fun showDeleteDialog(data: NoteData)

    fun deleteNote(data: NoteData)

    fun deleteAllNotes()

    fun filterData(filterData: FilterData)
}