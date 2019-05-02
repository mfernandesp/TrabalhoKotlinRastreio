package com.example.rastreiopacienteoficial

import android.arch.persistence.room.Room
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.rastreiopaciente.Banco.Banco
import kotlinx.android.synthetic.main.activity_tela_selecao_opccao.*

class TelaSelecaoOpccao : AppCompatActivity() {

    lateinit var banco: Banco

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_selecao_opccao)

        val sharedPreferences: SharedPreferences = getSharedPreferences("rastreiopacienteoficial", Context.MODE_PRIVATE)

        banco = Room.databaseBuilder(applicationContext, Banco::class.java, "RastreioPaciente").allowMainThreadQueries().build()

        var viewNome = findViewById<TextView>(R.id.textView10)

        viewNome.text =   sharedPreferences.getString("paciente_nome", "")

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
