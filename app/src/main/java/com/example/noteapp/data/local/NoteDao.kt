package com.example.noteapp.data.local

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Insert
    suspend fun addNote(data: NoteEntity)

    @Delete
    suspend fun deleteNote(data: NoteEntity)

    @Query("DELETE FROM NoteEntity")
    suspend fun deleteAllNotes()

    @Update
    suspend fun updateNote(data: NoteEntity)

    @Query("SELECT * FROM NoteEntity")
    fun getNotes(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM NoteEntity Where high=:high or simple=:simple or medium=:medium")
    fun getByTag(simple: Boolean, medium:Boolean, high: Boolean): Flow<List<NoteEntity>>
}