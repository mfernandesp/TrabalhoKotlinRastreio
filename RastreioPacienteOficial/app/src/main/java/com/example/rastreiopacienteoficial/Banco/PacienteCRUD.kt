package com.example.rastreiopaciente.Banco

import android.arch.persistence.room.*


@Dao
interface PacienteCRUD {

    @Insert  // or OnConflictStrategy.IGNORE
    fun inserirPaciente(paciente: Paciente)

    @Delete
    fun deletePaciente(paciente: Paciente)

    @Update
    fun updatePaciente(paciente: Paciente)

    @Query("select * from Paciente")
    fun listarPacientes(): List<Paciente>

    @Query("select * from Paciente where id_paciente=:id")
    fun getPaciente(id:Int): Paciente

    @Query("select * from Paciente order by id_paciente desc")
    fun lastId(): List<Paciente>

    @Query("select * from Paciente where nome=:nome")
    fun getIdPaciente(nome:String): Paciente

}