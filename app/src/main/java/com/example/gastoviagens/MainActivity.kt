package com.example.gastoviagens

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.gastoviagens.databinding.ActivityMainBinding


/* [2.1] Adicionando evento de click ao botão
Como setOnClickListener precisa de uma interface OnClickListener de precisamos implementa-la
na classe MainActivity, lembrando que essa interface herda de View
 */
class MainActivity : AppCompatActivity(), View.OnClickListener {

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


        /* [2.0] Adicionando evento de click ao botão
    setOnClickListener é uma função que recebe uma iterface (contrato de metodos e propriedades)
    chamada OnClickListener, que herda de View, com o metodo onClick que recebe um View
    Continua em [2.1] acima
    Como MainActivity agora herda de OnClickListener é utiliza-do "this" para chamar a função, e
    é necessario implementar os metodos
    Continua em [2.2] Abaixo
     */
        binding.buttonCalculate.setOnClickListener(this)
    }

    /* [2.2] Adicionando evento de click ao botão
Qualquer botão que for pressionado cairá neste override, portanto é necessário identificar
qual foi o botão. Aqui R significa resource que é uma classe do proprio android onde tem todos os
elementos com id ou colors
continua [2.3]
 */
    override fun onClick(view: View) {
        if (view.id == R.id.button_calculate) {
            calculate()
        }
    }

    /* [4.0] Validação de dados */
    private fun isValid(): Boolean {
        return (binding.editDistance.text.toString() != ""
                && binding.editPrice.text.toString() != ""
                && binding.editAutonomy.text.toString() != ""
                && binding.editAutonomy.text.toString().toFloat() != 0f)
    }


    /* [2.3] Adicionando evento de click ao botão
Toast.makeTextt é usado para exibir mensagens curtas e temporárias recebe o parametro
context: O contexto da aplicação (geralmente this ou applicationContext), quem guarda informações
da aplicação.
    [3.0] Adicionando a lógica de calculo
Para resgatar o valor de um editText utilizamos .text.toString
     */
    private fun calculate() {
        if (isValid()) {
            val distance = binding.editDistance.text.toString().toFloat()
            val price = binding.editPrice.text.toString().toFloat()
            val autonomy = binding.editAutonomy.text.toString().toFloat()

            val totalValue = (distance * price) / autonomy
            val totalValueStr = "R$ ${"%.2f".format(totalValue)}"
            binding.textTotalValue.text = totalValueStr
        } else {
            Toast.makeText(this, R.string.validation_fill_all_fields, Toast.LENGTH_SHORT).show()
        }
    }
}