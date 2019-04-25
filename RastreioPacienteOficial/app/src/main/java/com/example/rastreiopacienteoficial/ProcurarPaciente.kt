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
import com.example.rastreiopaciente.Banco.Banco
import com.example.rastreiopaciente.Banco.Paciente

class ProcurarPaciente : AppCompatActivity() {

    lateinit var banco: Banco
    lateinit var listaPaciente: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_procurar_paciente)

        banco = Room.databaseBuilder(applicationContext, Banco::class.java, "RastreioPaciente").allowMainThreadQueries().build()

        listaPaciente = findViewById<ListView>(R.id.listViewPesquisa)

        var adapter: ArrayAdapter<String>

        var selectBanco: List<Paciente>
        selectBanco = banco.pacienteCrud().listarPacientes()


        var dado: MutableList<String> = ArrayList()

        for(paciente in selectBanco) {
            dado.add(paciente.nome!!)
        }

        adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1, dado)

        listaPaciente.adapter = adapter

        listaPaciente.setOnItemClickListener{parent: AdapterView<*>?, view: View?, position: Int, id: Long ->
            var paciente = banco.pacienteCrud().getIdPaciente(listaPaciente.getItemAtPosition(position).toString())
            val sharedPreferences: SharedPreferences = getSharedPreferences("rastreiopacienteoficial", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("paciente_nome", paciente.nome)
            editor.putString("paciente_id", paciente.id_paciente.toString())
            editor.apply()
            Toast.makeText(this, "Paciente ${paciente.nome} escolhido.", Toast.LENGTH_LONG).show()
          //  Toast.makeText(this, "Paciente id ${id} escolhido.", Toast.LENGTH_LONG).show()
            startActivity(Intent(this, TelaSelecaoOpccao::class.java))
            finish()

        }

       // paciente.id_paciente!!,
    }
}
