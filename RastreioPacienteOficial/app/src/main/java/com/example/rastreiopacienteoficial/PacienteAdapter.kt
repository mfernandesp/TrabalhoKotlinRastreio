package com.example.rastreiopacienteoficial

import android.app.Activity
import android.arch.persistence.room.Room
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.rastreiopaciente.Banco.Banco
import com.example.rastreiopaciente.Banco.Paciente

class PacienteAdapter(var context: Context, var act: Activity)  : BaseAdapter() {


    var banco = Room.databaseBuilder(context, Banco::class.java, "RastreioPaciente").allowMainThreadQueries().build()

    var listaPaciente = banco.pacienteCrud().listarPacientes()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = act.getLayoutInflater().inflate(R.layout.lista_paciente_personalizada, parent, false)

        var paciente = listaPaciente.get(position)

        //pegando as referências das Views
        var nome = view.findViewById<TextView>(R.id.lista_nome_paciente)
        var nascimento = view.findViewById<TextView>(R.id.lista_nascimento)
        var id_paciente = view.findViewById<TextView>(R.id.lista_id_paciente)

        //populando as views
        nome.text = paciente.nome
        nascimento.text = paciente.dataNascimento
        id_paciente.text = paciente.id_paciente.toString()

        return view
    }

    override fun getItem(position: Int): Any {
        return listaPaciente.get(position)
    }

    override fun getItemId(position: Int): Long {
   //     var paciente = listaPaciente.get(position)
       // return paciente.id_paciente?.toLong()!!
        return 0
    }

    override fun getCount(): Int {
        return  listaPaciente.size
     }



}