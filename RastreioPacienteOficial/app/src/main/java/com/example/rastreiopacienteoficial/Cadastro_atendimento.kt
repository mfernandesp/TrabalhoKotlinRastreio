package com.example.rastreiopacienteoficial

import android.arch.persistence.room.Room
import android.content.Context
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.rastreiopaciente.Banco.Atendimento
import com.example.rastreiopaciente.Banco.Banco
import com.example.rastreiopaciente.Banco.Paciente
import kotlinx.android.synthetic.main.activity_cadastro_atendimento.*
import java.lang.Exception

class Cadastro_atendimento : AppCompatActivity() {

    lateinit var banco: Banco


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_atendimento)

        banco = Room.databaseBuilder(applicationContext, Banco::class.java, "RastreioPaciente").allowMainThreadQueries().build()

        buttonCriarAtendimento.setOnClickListener {
            cadastrarAtendimento()
        }


    }

    fun cadastrarAtendimento(){
        val sharedPreferences: SharedPreferences = getSharedPreferences("rastreiopacienteoficial", Context.MODE_PRIVATE)
        val atendimento = Atendimento()
        atendimento.id_atendimento = lastIdAtendimento()
        atendimento.id_paciente= sharedPreferences.getString("paciente_id", "").toInt()
        atendimento.data = dataChegadaAtendimento.text.toString()
        atendimento.doenca = doencaNomeAtendimento.text.toString()
        atendimento.medicacao = medicacaoAtendimento.text.toString()
        atendimento.custo = custoTextAtedimento1.text.toString().toFloat()
        atendimento.observacoes = obstextAtendimento.text.toString()

        try{
            banco?.atendimentoCrud()?.inserirAtendimento(atendimento)
            Toast.makeText(this, "Atendimento registrado com sucesso!.", Toast.LENGTH_LONG).show()
            finish()
        }catch (e: Exception){
            Toast.makeText(this, "Erro ao cadastrar.", Toast.LENGTH_LONG).show()
        }

    }


    fun lastIdAtendimento(): Int {

        var lastId: Int
        try {
            lastId = banco?.atendimentoCrud()?.lastId()?.get(0)?.id_atendimento.toString().toInt() + 1

        }catch (e: Exception) {
            lastId = 1
        }

        return lastId
    }
}
