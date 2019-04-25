package com.example.rastreiopacienteoficial

import android.arch.persistence.room.Room
import android.app.Application
import com.example.rastreiopaciente.Banco.Banco

open class MyApplication : Application() {

    companion object {
        var banco: Banco? = null
    }


    override fun onCreate() {
        super.onCreate()
        //Room
        banco = Room.databaseBuilder(this, Banco::class.java, "my-db").allowMainThreadQueries().build()

    }
}