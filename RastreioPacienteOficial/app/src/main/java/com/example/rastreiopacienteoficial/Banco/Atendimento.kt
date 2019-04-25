package com.example.rastreiopaciente.Banco

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.time.Instant
import java.time.Instant.now
import java.util.*

@Entity
class Atendimento {

    @PrimaryKey
    var id_atendimento:Int?=0
    @ColumnInfo(name = "doenca")
    var doenca:String?=null
    @ColumnInfo(name = "medicacao")
    var medicacao:String?=null
    @ColumnInfo(name = "data")
    var data: String? = null
    @ColumnInfo(name = "custo")
    var custo: Float = 0.0F
    @ColumnInfo(name = "observacoes")
    var observacoes:String?=null
    @ColumnInfo(name = "id_paciente")
    var id_paciente:Int?=0
}
