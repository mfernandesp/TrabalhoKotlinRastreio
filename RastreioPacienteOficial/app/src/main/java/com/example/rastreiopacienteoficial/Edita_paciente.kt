package com.example.rastreiopacienteoficial

import android.arch.persistence.room.Room
import android.content.Context
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import com.example.rastreiopaciente.Banco.Banco
import com.example.rastreiopaciente.Banco.Paciente
import com.example.rastreiopaciente.Banco.Usuario
import kotlinx.android.synthetic.main.activity_cadastrar_usuario.*
import kotlinx.android.synthetic.main.activity_cadastro_paciente.*
import kotlinx.android.synthetic.main.activity_edita_paciente.*
import java.lang.Exception

class Edita_paciente : AppCompatActivity() {

    lateinit var banco: Banco
    lateinit var paciente: Paciente

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edita_paciente)

        val sharedPreferences: SharedPreferences = getSharedPreferences("rastreiopacienteoficial", Context.MODE_PRIVATE)
        banco = Room.databaseBuilder(applicationContext, Banco::class.java, "RastreioPaciente").allowMainThreadQueries().build()

        paciente = banco.pacienteCrud().getPaciente(sharedPreferences.getString("paciente_id", "").toInt())

        nomeTextCardastrarPaciente2.text = Editable.Factory.getInstance().newEditable(paciente.nome)
        emailTextCardastrarPaciente2.text = Editable.Factory.getInstance().newEditable(paciente.email)
        dataTextCardastrarPaciente2.text = Editable.Factory.getInstance().newEditable(paciente.dataNascimento)
        telTextCardastrarPaciente2.text = Editable.Factory.getInstance().newEditable(paciente.telefone)
        planoSaudeTextCardastrarPaciente2.text = Editable.Factory.getInstance().newEditable(paciente.planoSaude)
        cpfTextCardastrarPaciente2.text = Editable.Factory.getInstance().newEditable(paciente.cpf.toString())

        alterarPaciente.setOnClickListener {
            alterarPaciente(sharedPreferences)
        }
    }

    fun alterarPaciente(sharedPreferences: SharedPreferences){
        paciente.nome = nomeTextCardastrarPaciente2.text.toString()
        paciente.email = emailTextCardastrarPaciente2.text.toString()
        paciente.dataNascimento = dataTextCardastrarPaciente2.text.toString()
        paciente.telefone = telTextCardastrarPaciente2.text.toString()
        paciente.planoSaude = planoSaudeTextCardastrarPaciente2.text.toString()
        paciente.cpf = cpfTextCardastrarPaciente2.text.toString()

        try{
            banco?.pacienteCrud()?.updatePaciente(paciente)
            Toast.makeText(this, "Paciente ${paciente.nome} alterado com sucesso!.", Toast.LENGTH_LONG).show()
            val editor = sharedPreferences.edit()
            editor.putString("paciente_nome", paciente.nome)
            finish()
        }catch (e: Exception){
            Toast.makeText(this, "Erro ao cadastrar. ${e} ", Toast.LENGTH_LONG).show()
        }

    }

}
