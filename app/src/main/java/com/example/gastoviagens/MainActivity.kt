package com.example.gastoviagens

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.gastoviagens.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    /* [1.0] Configuração do viewBinding
    cria uma variavel "binding" que armazena as referencias para o XML do layout, guardando todos os
    elementos com ID na memoria para serem acessados pela main, dessa forma pode-se instanciar os
    elementos pelo ID como binding.edit_distance, mas ActivityMain sofrer inflate (conversão XML
    para memoria) depois do onCreate, caso contrario será valor nulo (nullPointerException),
    por isso utiliza-se lateinit para ser intanciada dentro de onCreate. Preciso declarar a variavel
    fora de onCreate para poder acessa-la fora do escopo desta função
     */

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        /* [1.1] Configuração do viewBinding
        aqui eu intancio a variavel binding que inciou no lateinit
        também é necessario mudar setContentView para binding.root que faz referencia ao layoute raiz
        (O linear Layout)
        */
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun calculate(){}

}