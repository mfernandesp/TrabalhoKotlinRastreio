package com.example.rastreiopacienteoficial

import android.arch.persistence.room.Room
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.rastreiopaciente.Banco.Banco
import com.example.rastreiopaciente.Banco.Usuario
import kotlinx.android.synthetic.main.activity_cadastrar_usuario.*
import java.lang.Exception

class CadastrarUsuario : AppCompatActivity() {

    lateinit var banco: Banco

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastrar_usuario)
        title = "Cadastrar Novo Usuário"

        banco = Room.databaseBuilder(applicationContext, Banco::class.java, "RastreioPaciente").allowMainThreadQueries().build()

        cadastrarUsuario.setOnClickListener {
            cadastrar()
        }
    }

    fun cadastrar(){

        //Cria um novo usuario
        val usuario = Usuario()
        usuario.idUsuario = lastIdUsuario()
        usuario.nome = nome.text.toString()
        usuario.login = login_usuario.text.toString()
        usuario.senha = senha.text.toString()
        usuario.email = email.text.toString()
        usuario.credencial = credencial.text.toString()

        try{
            banco?.usuarioCrud()?.inserirUsuario(usuario)
            login()
            Toast.makeText(this, "Usuário ${usuario.nome} criado com sucesso!.", Toast.LENGTH_LONG).show()
        }catch (e: Exception){
            Toast.makeText(this, "Erro ao cadastrar.", Toast.LENGTH_LONG).show()
        }

    }


    fun lastIdUsuario(): Int {

        var lastId: Int
        try {
            lastId = banco?.usuarioCrud()?.lastId()?.get(0)?.idUsuario.toString().toInt() + 1

        }catch (e: Exception) {
            lastId = 1
        }

        return lastId
    }

    fun login() {


        val login = login_usuario.text.toString()
        val senha = senha.text.toString()

        val sharedPreferences: SharedPreferences = getSharedPreferences("rastreiopacienteoficial", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("isLogged", true)
        editor.putString("username", login)
        editor.putString("password", senha)
        editor.putString("name", nome.text.toString())
        editor.apply()
        startActivity(Intent(this, MainActivity::class.java))
        finish()

    }
}
