package com.example.rastreiopacienteoficial

import android.arch.persistence.room.Room
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
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



        var adapterPaciente = PacienteAdapter(context = this.applicationContext, act = this)
        listaPaciente.adapter = adapterPaciente


        listaPaciente.setOnItemClickListener{parent: AdapterView<*>?, view: View?, position: Int, id: Long ->
            var id_paciente = view?.findViewById<TextView>(R.id.lista_id_paciente)?.text.toString()
            var nome = view?.findViewById<TextView>(R.id.lista_nome_paciente)?.text.toString()
            val sharedPreferences: SharedPreferences = getSharedPreferences("rastreiopacienteoficial", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("paciente_nome", nome)
            editor.putString("paciente_id",id_paciente)
            editor.apply()
            Toast.makeText(this, "Paciente ${nome} escolhido.", Toast.LENGTH_LONG).show()
            startActivity(Intent(this, TelaSelecaoOpccao::class.java))
            finish()
        }

    }
}
