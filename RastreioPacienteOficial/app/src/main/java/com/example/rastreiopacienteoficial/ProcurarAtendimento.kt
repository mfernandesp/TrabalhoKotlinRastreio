package com.example.rastreiopacienteoficial

import android.arch.persistence.room.Room
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.example.rastreiopaciente.Banco.Atendimento
import com.example.rastreiopaciente.Banco.Banco

class ProcurarAtendimento : AppCompatActivity() {


    lateinit var banco: Banco
    lateinit var listaAtendimento: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_procurar_atendimento)

        val sharedPreferences: SharedPreferences = getSharedPreferences("rastreiopacienteoficial", Context.MODE_PRIVATE)

        banco = Room.databaseBuilder(applicationContext, Banco::class.java, "RastreioPaciente").allowMainThreadQueries().build()

        listaAtendimento = findViewById<ListView>(R.id.listViewPesquisaAtendimento)

        var adapter: ArrayAdapter<String>

        var selectBanco: List<Atendimento>
        selectBanco = banco.atendimentoCrud().getAtendimentoPaciente(sharedPreferences.getString("paciente_id", "").toInt())


        var dado: MutableList<String> = ArrayList()

        for(atendimento in selectBanco) {
            dado.add(atendimento.data.toString())
        }

        adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1, dado)

        listaAtendimento.adapter = adapter

        listaAtendimento.setOnItemClickListener{ parent: AdapterView<*>?, view: View?, position: Int, id: Long ->
            var atendimento = banco.atendimentoCrud().getIdAtendimento(listaAtendimento.getItemAtPosition(position).toString())
            val sharedPreferences: SharedPreferences = getSharedPreferences("rastreiopacienteoficial", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("atendimento_id", atendimento.id_atendimento.toString())
            editor.apply()
            Toast.makeText(this, "Atendimento ${atendimento.observacoes} escolhido.", Toast.LENGTH_LONG).show()
            //  Toast.makeText(this, "Paciente id ${id} escolhido.", Toast.LENGTH_LONG).show()
            startActivity(Intent(this, Editar_Atendimento::class.java))
            finish()

        }
    }
}
