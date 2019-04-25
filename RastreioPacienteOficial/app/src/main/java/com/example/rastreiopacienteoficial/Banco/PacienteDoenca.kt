package com.example.rastreiopaciente.Banco

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class PacienteDoenca {


    @PrimaryKey(autoGenerate = true)
    var id_pacienteDoenca:Long?=0
    @ColumnInfo(name = "id_paciente")
    var id_paciente:Long?=0
    @ColumnInfo(name = "id_doenca")
    var id_doenca:Long?=0

}
