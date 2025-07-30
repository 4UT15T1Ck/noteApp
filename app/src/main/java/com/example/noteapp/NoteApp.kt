package com.example.noteapp

import android.app.Application
import com.example.noteapp.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.ksp.generated.*


class NoteApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@NoteApp)
            modules(AppModule().module)
        }
    }
}