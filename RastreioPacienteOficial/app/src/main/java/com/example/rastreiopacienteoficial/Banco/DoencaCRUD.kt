package com.example.rastreiopaciente.Banco

import android.arch.persistence.room.*


@Dao
interface DoencaCRUD {

    @Insert  // or OnConflictStrategy.IGNORE
    fun inserirDoenca(doenca: Doenca)

    @Delete
    fun deleteDoenca(doenca: Doenca)

    @Update
    fun updateDoenca(doenca: Doenca)

    @Query("select * from Doenca")
    fun listarDoencas(): List<Doenca>

    @Query("select * from Doenca where id_doenca=:id")
    fun getDoenca(id:Int): Doenca

}