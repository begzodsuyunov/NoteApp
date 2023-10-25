package com.example.noteapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NoteEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getNoteDao(): NoteDao

    companion object {

        private lateinit var _instance: AppDatabase
        fun init(context: Context) {
            if (!Companion::_instance.isInitialized)
                _instance =
                    Room.databaseBuilder(context, AppDatabase::class.java, "note_database")
                        .createFromAsset("note_db.db").build()
        }

        val INSTANCE get() = _instance
    }
}