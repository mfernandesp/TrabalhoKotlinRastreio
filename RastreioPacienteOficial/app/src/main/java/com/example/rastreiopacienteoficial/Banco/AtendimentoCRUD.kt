package com.example.rastreiopaciente.Banco

import android.arch.persistence.room.*


@Dao
interface AtendimentoCRUD {

    @Insert  // or OnConflictStrategy.IGNORE
    fun inserirAtendimento(atendimento: Atendimento)

    @Delete
    fun deleteAtendimento(atendimento: Atendimento)

    @Update
    fun updateAtendimento(atendimento: Atendimento)

    @Query("select * from Atendimento")
    fun listarAtendimentos(): List<Atendimento>

    @Query("select * from Atendimento where id_atendimento=:id")
    fun getAtendimento(id:Int): Atendimento

    @Query("select * from Atendimento where id_paciente=:id")
    fun getAtendimentoPaciente(id:Int): List<Atendimento>

    @Query("select * from Atendimento order by id_atendimento desc")
    fun lastId(): List<Atendimento>

    @Query("select * from Atendimento where data=:obs")
    fun getIdAtendimento(obs:String): Atendimento
}