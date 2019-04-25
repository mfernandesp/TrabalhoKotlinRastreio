package com.example.rastreiopacienteoficial

import android.arch.persistence.room.Room
import android.content.Context
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import com.example.rastreiopaciente.Banco.Atendimento
import com.example.rastreiopaciente.Banco.Banco
import com.example.rastreiopaciente.Banco.Paciente
import com.example.rastreiopacienteoficial.R
import kotlinx.android.synthetic.main.activity_editar__atendimento.*
import java.lang.Exception

class Editar_Atendimento : AppCompatActivity() {

    lateinit var banco: Banco
    lateinit var atendimento: Atendimento

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar__atendimento)

        val sharedPreferences: SharedPreferences = getSharedPreferences("rastreiopacienteoficial", Context.MODE_PRIVATE)
        banco = Room.databaseBuilder(applicationContext, Banco::class.java, "RastreioPaciente").allowMainThreadQueries().build()

        atendimento = banco.atendimentoCrud().getAtendimento(sharedPreferences.getString("atendimento_id", "").toInt())


        dataChegadaAtendimento1.text = Editable.Factory.getInstance().newEditable(atendimento.data)
        doencaNomeAtendimento1.text = Editable.Factory.getInstance().newEditable(atendimento.doenca)
        medicacaoAtendimento1.text = Editable.Factory.getInstance().newEditable(atendimento.medicacao)
        custoTextAtedimento11.text = Editable.Factory.getInstance().newEditable(atendimento.custo.toString())
        obstextAtendimento1.text = Editable.Factory.getInstance().newEditable(atendimento.observacoes)

        buttonCriarAtendimento1.setOnClickListener {
           alterarAtendimento(sharedPreferences)
        }


    }

    fun alterarAtendimento(sharedPreferences: SharedPreferences){
        atendimento.data = dataChegadaAtendimento1.text.toString()
        atendimento.doenca = doencaNomeAtendimento1.text.toString()
        atendimento.medicacao = medicacaoAtendimento1.text.toString()
        atendimento.custo = custoTextAtedimento11.text.toString().toFloat()
        atendimento.observacoes = obstextAtendimento1.text.toString()

        try{
            banco?.atendimentoCrud()?.updateAtendimento(atendimento)
            Toast.makeText(this, "Atendimento ${atendimento.observacoes} alterado com sucesso!.", Toast.LENGTH_LONG).show()
            finish()
        }catch (e: Exception){
            Toast.makeText(this, "Erro ao cadastrar. ${e} ", Toast.LENGTH_LONG).show()
        }


    }
}
