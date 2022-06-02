package com.example.kotlin_omdbapi2.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kotlin_omdbapi2.model.MovieResults

@Database(entities = arrayOf(MovieResults::class), version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao() : MovieDao

    companion object{

        @Volatile private var instance : MovieDatabase? = null
        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock){
            instance ?: makeDatabase(context).also {
                instance = it
            }
        }

        private fun makeDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,MovieDatabase::class.java,"gameDatabase"
        ).build()

    }

}