package com.example.rastreiopacienteoficial

import android.arch.persistence.room.Room
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.rastreiopaciente.Banco.Banco
import com.example.rastreiopaciente.Banco.Paciente
import com.example.rastreiopaciente.Banco.Usuario
import kotlinx.android.synthetic.main.activity_cadastrar_usuario.*
import kotlinx.android.synthetic.main.activity_cadastro_paciente.*
import java.lang.Exception

class Cadastro_paciente : AppCompatActivity() {

    lateinit var banco: Banco

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_paciente)

        banco = Room.databaseBuilder(applicationContext, Banco::class.java, "RastreioPaciente").allowMainThreadQueries().build()

        cadastrarPaciente.setOnClickListener {
            cadastrarPaciente()
        }
    }

    fun cadastrarPaciente(){
        val paciente = Paciente()
        paciente.id_paciente = lastIdPaciente()
        paciente.nome = nomeTextCardastrarPaciente.text.toString()
        paciente.email = emailTextCardastrarPaciente.text.toString()
        paciente.dataNascimento = dataTextCardastrarPaciente.text.toString()
        paciente.telefone = telTextCardastrarPaciente.text.toString()
        paciente.planoSaude = planoSaudeTextCardastrarPaciente.text.toString()
        paciente.cpf = cpfTextCardastrarPaciente.text.toString()

        try{
            banco?.pacienteCrud()?.inserirPaciente(paciente)
            Toast.makeText(this, "Paciente ${paciente.nome} criado com sucesso!.", Toast.LENGTH_LONG).show()
            finish()
        }catch (e: Exception){
            Toast.makeText(this, "Erro ao cadastrar. ${e}  ${lastIdPaciente()} ", Toast.LENGTH_LONG).show()
        }

    }


    fun lastIdPaciente(): Int {

        var lastId: Int
        try {
            lastId = banco?.pacienteCrud()?.lastId()?.get(0)?.id_paciente.toString().toInt() + 1

        }catch (e: Exception) {
            lastId = 1
        }

        return lastId
    }
}
