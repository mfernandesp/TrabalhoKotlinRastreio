package com.example.rastreiopaciente.Banco

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class Usuario {


    @PrimaryKey
    var idUsuario:Int?=0
    @ColumnInfo(name = "login")
    var login:String?=null
    @ColumnInfo(name = "senha")
    var senha:String?=null
    @ColumnInfo(name = "email")
    var email:String?=null
    @ColumnInfo(name = "nome")
    var nome:String?=null
    @ColumnInfo(name = "credencial")
    var credencial:String?=null


}
