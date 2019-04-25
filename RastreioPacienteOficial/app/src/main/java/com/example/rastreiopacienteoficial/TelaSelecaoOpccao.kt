package com.example.rastreiopacienteoficial

import android.arch.persistence.room.Room
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.rastreiopaciente.Banco.Banco
import kotlinx.android.synthetic.main.activity_tela_selecao_opccao.*

class TelaSelecaoOpccao : AppCompatActivity() {

    lateinit var banco: Banco

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_selecao_opccao)

        banco = Room.databaseBuilder(applicationContext, Banco::class.java, "RastreioPaciente").allowMainThreadQueries().build()

        buttonAdiconarAtendimento.setOnClickListener { adicionarAtendimento() }
        buttonEditarAtendimento.setOnClickListener { editarAtendimento() }
        editarPaciente.setOnClickListener { editarPaciente() }
    }

    fun adicionarAtendimento(){
        startActivity(Intent(this, Cadastro_atendimento::class.java))
    }

    fun editarAtendimento(){
        startActivity(Intent(this, ProcurarAtendimento::class.java))
    }

    fun editarPaciente(){
        startActivity(Intent(this, Edita_paciente::class.java))
    }
}
