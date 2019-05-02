package com.example.rastreiopacienteoficial

import android.app.Activity
import android.arch.persistence.room.Room
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.content.SharedPreferences
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.rastreiopaciente.Banco.Banco

class AtendimentoAdapter(var context: Context, var act: Activity)  : BaseAdapter() {


    var banco = Room.databaseBuilder(context, Banco::class.java, "RastreioPaciente").allowMainThreadQueries().build()

    val sharedPreferences: SharedPreferences = context.getSharedPreferences("rastreiopacienteoficial", Context.MODE_PRIVATE)

    var listaAtendimento = banco.atendimentoCrud().getAtendimentoPaciente(sharedPreferences.getString("paciente_id", "").toInt())

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = act.getLayoutInflater().inflate(R.layout.lista_atendimento_personalizada, parent, false)

        var atendimento = listaAtendimento.get(position)

        //pegando as referências das Views
        var doenca = view.findViewById<TextView>(R.id.lista_doenca_paciente)
        var data_atendimento = view.findViewById<TextView>(R.id.lista_data_atendimento)
        var id_atendimento = view.findViewById<TextView>(R.id.lista_id_atendimento)

        //populando as views
        doenca.text = atendimento.doenca
        data_atendimento.text = atendimento.data
        id_atendimento.text = atendimento.id_atendimento.toString()

        return view
    }

    override fun getItem(position: Int): Any {
        return listaAtendimento.get(position)
    }

    override fun getItemId(position: Int): Long {
   //     var paciente = listaPaciente.get(position)
       // return paciente.id_paciente?.toLong()!!
        return 0
    }

    override fun getCount(): Int {
        return  listaAtendimento.size
     }



}