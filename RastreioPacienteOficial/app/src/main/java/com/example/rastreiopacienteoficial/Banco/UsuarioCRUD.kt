package com.example.rastreiopaciente.Banco

import android.arch.persistence.room.*


@Dao
interface UsuarioCRUD {

    @Insert  // or OnConflictStrategy.IGNORE
    fun inserirUsuario(usuario: Usuario)

    @Delete
    fun deleteUsuario(usuario: Usuario)

    @Update
    fun updateUsuario(usuario: Usuario)

    @Query("select * from Usuario")
    fun listarUsuarios(): List<Usuario>

    @Query("select * from Usuario where idUsuario=:id")
    fun getUsuario(id:Int): Usuario

    @Query("select * from Usuario where login=:login1 and senha=:senha1")
    fun verifyUruario(login1:String, senha1:String): Usuario

    @Query("select * from Usuario order by idUsuario desc")
    fun lastId(): List<Usuario>


}