package com.example.rastreiopaciente.Banco

import android.arch.persistence.room.*


@Dao
interface PacienteDoencaCRUD {

    @Insert  // or OnConflictStrategy.IGNORE
    fun inserirPacienteDoenca(pacienteDoenca: PacienteDoenca)

    @Delete
    fun deletePacienteDoenca(pacienteDoenca: PacienteDoenca)

    @Update
    fun updatePacienteDoenca(pacienteDoenca: PacienteDoenca)

    @Query("select * from PacienteDoenca")
    fun listarPacienteDoencas(): List<PacienteDoenca>

    @Query("select * from PacienteDoenca where id_pacienteDoenca=:id")
    fun getPacienteDoenca(id:Int): PacienteDoenca

}