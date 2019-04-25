package com.example.rastreiopaciente.Banco

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class Doenca {


    @PrimaryKey(autoGenerate = true)
    var id_doenca:Long?=0
    @ColumnInfo(name = "nome")
    var nome:String?=null
    @ColumnInfo(name = "tipo")
    var tipo:Int?=0

}
