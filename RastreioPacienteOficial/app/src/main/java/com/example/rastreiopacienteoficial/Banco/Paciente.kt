package com.example.rastreiopaciente.Banco

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class Paciente {

    @PrimaryKey
    var id_paciente:Int?=0
    @ColumnInfo(name = "nome")
    var nome:String?=null
    @ColumnInfo(name = "email")
    var email:String?=null
    @ColumnInfo(name = "dataNascimento")
    var dataNascimento:String?=null
    @ColumnInfo(name = "telefone")
    var telefone:String?=null
    @ColumnInfo(name = "planoSaude")
    var planoSaude:String?=null
    @ColumnInfo(name = "cpf")
    var cpf:String?=null
}
