package com.example.rastreiopacienteoficial

import android.arch.persistence.room.Room
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import com.example.rastreiopaciente.Banco.Banco
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var banco: Banco

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //instancia o banco
        banco = Room.databaseBuilder(applicationContext, Banco::class.java, "RastreioPaciente").allowMainThreadQueries().build()


        val sharedPreferences: SharedPreferences = getSharedPreferences("rastreiopacienteoficial", Context.MODE_PRIVATE)
        val isLogged = sharedPreferences.getBoolean("isLogged", false)
        //verifica se o usuario já está logado
        if (isLogged) {
            buttonSair.setOnClickListener { logout(sharedPreferences) }
            buttonVisualizarAtendimento.setOnClickListener {visualizarHistorico ()}
            buttonCriarPaciente.setOnClickListener {criarPaciente ()}
        } else {
            startActivity(Intent(this, Login::class.java))
            finish()
        }


    }

    fun logout(sharedPreferences: SharedPreferences) {
        val editor = sharedPreferences.edit()
        editor.putBoolean("isLogged", false)
        editor.putString("username", "")
        editor.putString("password", "")
        editor.putString("paciente_nome","" )
        editor.putString("paciente_id","" )
        editor.apply()
        startActivity(Intent(this, Login::class.java))
        finish()
    }

    fun visualizarHistorico() {

        startActivity(Intent(this, ProcurarPaciente::class.java))

    }

    fun criarPaciente() {

        startActivity(Intent(this, Cadastro_paciente::class.java))

    }

}
