package com.example.noteapp.di

import android.app.Application
import androidx.room.Room
import com.example.noteapp.note.data.repository.NoteRepositoryImplement
import com.example.noteapp.note.data.source.NoteDatabase
import com.example.noteapp.note.domain.repository.NoteRepository
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single


@Module
@ComponentScan("com.example.noteapp")
class AppModule {

    @Single
    fun provideNoteDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()
    }

    @Single
    fun provideNoteRepository(db: NoteDatabase): NoteRepository {
        return NoteRepositoryImplement(db.noteDAO)
    }
}