package com.example.rastreiopaciente.Banco

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = [Usuario::class, Paciente::class, Doenca::class, Atendimento::class, PacienteDoenca::class], version = 1, exportSchema = false)

public abstract class Banco: RoomDatabase(){

    abstract fun usuarioCrud(): UsuarioCRUD
    abstract fun pacienteCrud(): PacienteCRUD
    abstract fun doencaCrud():DoencaCRUD
    abstract fun pacienteDoencaCrud():PacienteDoencaCRUD
    abstract fun atendimentoCrud(): AtendimentoCRUD
}