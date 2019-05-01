package com.example.rastreiopacienteoficial

import android.arch.persistence.room.Room
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.rastreiopaciente.Banco.Atendimento
import com.example.rastreiopaciente.Banco.Banco
import com.example.rastreiopacienteoficial.Banco.AtendimentoAdapter

class ProcurarAtendimento : AppCompatActivity() {


    lateinit var banco: Banco
    lateinit var listaAtendimento: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_procurar_atendimento)

        val sharedPreferences: SharedPreferences = getSharedPreferences("rastreiopacienteoficial", Context.MODE_PRIVATE)

        banco = Room.databaseBuilder(applicationContext, Banco::class.java, "RastreioPaciente").allowMainThreadQueries().build()

        listaAtendimento = findViewById<ListView>(R.id.listViewPesquisaAtendimento)

        var adapterAtendimento = AtendimentoAdapter(context = this.applicationContext, act = this)
        listaAtendimento.adapter = adapterAtendimento

        listaAtendimento.setOnItemClickListener{ parent: AdapterView<*>?, view: View?, position: Int, id: Long ->
            var id_atendimento = view?.findViewById<TextView>(R.id.lista_id_atendimento)?.text.toString()
            var doenca = view?.findViewById<TextView>(R.id.lista_doenca_paciente)?.text.toString()
            val sharedPreferences: SharedPreferences = getSharedPreferences("rastreiopacienteoficial", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("atendimento_id", id_atendimento)
            editor.apply()
            Toast.makeText(this, "Atendimento do dença ${doenca} escolhido.", Toast.LENGTH_LONG).show()
            startActivity(Intent(this, Editar_Atendimento::class.java))
            finish()

        }
    }
}
