package com.example.noteapp.presenter.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.data.models.FilterData
import com.example.noteapp.data.models.NoteData
import com.example.noteapp.domain.usecase.DeleteAllNoteUseCase
import com.example.noteapp.domain.usecase.DeleteNoteUseCase
import com.example.noteapp.domain.usecase.GetNotesUseCase
import com.example.noteapp.domain.usecase.impl.DeleteAllNoteUseCaseImpl
import com.example.noteapp.domain.usecase.impl.DeleteNoteUseCaseImpl
import com.example.noteapp.domain.usecase.impl.GetNotesUseCaseImpl
import com.example.noteapp.presenter.MainViewModel
import com.example.noteapp.utils.eventLiveData
import com.example.noteapp.utils.eventValueLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainViewModelImpl() : MainViewModel, ViewModel() {
    override val openAddNoteLiveData = eventLiveData()
    override val openEditNoteLiveData = eventValueLiveData<NoteData>()
    override val showDeleteDialogLiveData = eventValueLiveData<NoteData>()
    override val notesListLivedata = eventValueLiveData<List<NoteData>>()

    private val getNotesUseCase: GetNotesUseCase = GetNotesUseCaseImpl()
    private val deleteAllNoteUseCase: DeleteAllNoteUseCase = DeleteAllNoteUseCaseImpl()
    private val deleteNoteUseCase: DeleteNoteUseCase = DeleteNoteUseCaseImpl()


    override fun openAddNoteScreen() {
        openAddNoteLiveData.value = Unit
    }

    override fun openEditNoteScreen(data: NoteData) {
        openEditNoteLiveData.value = data
    }

    override fun showDeleteDialog(data: NoteData) {
        showDeleteDialogLiveData.value = data
    }

    override fun deleteNote(data: NoteData) {
        viewModelScope.launch {
            deleteNoteUseCase.deleteNote(data)
        }    }

    override fun deleteAllNotes() {
        viewModelScope.launch {
            deleteAllNoteUseCase.deleteAllNotes()
        }
    }

    override fun filterData(filterData: FilterData) {
        viewModelScope.launch(Dispatchers.IO) {

            getNotesUseCase.getAllNotes().onEach{
                    list ->
                val filteredList = ArrayList<NoteData>()

                list.forEach {
                    if (filterData.high && it.high)
                        filteredList.add(it)
                    else if (filterData.medium && it.medium)
                        filteredList.add(it)
                    else if (filterData.simple && it.simple)
                        filteredList.add(it)
                }

                notesListLivedata.postValue(filteredList)
            }.collect()
        }
    }

    init {
        viewModelScope.launch {
            getNotesUseCase.getAllNotes().collectLatest {
                notesListLivedata.value = it
            }
        }
    }
}