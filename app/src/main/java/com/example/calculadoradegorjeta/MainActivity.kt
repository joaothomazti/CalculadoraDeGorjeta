package com.example.calculadoradegorjeta

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.calculadoradegorjeta.databinding.ActivityMainBinding
import java.text.NumberFormat


class MainActivity : AppCompatActivity() {

   private val binding by lazy {
       ActivityMainBinding.inflate(layoutInflater)
   }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.botaoCalcular.setOnClickListener { calcularGorjeta() }
    }


    private fun calcularGorjeta() {

        val valorEmString = binding.custoDoServico.text.toString()
        val valor = valorEmString.toDoubleOrNull()
        if (valor == null){
            binding.resultadoTexto.text = ""
            return
        }

        val porcentagem = when(binding.atendimentoRadioGroup.checkedRadioButtonId) {
            R.id.atendimento_incrivel -> 0.20
            R.id.atendimento_bom -> 0.15
            else -> 0.10
        }
        var resultadoValor = porcentagem * valor

        if (binding.arredondarGorjeta.isChecked) {
            resultadoValor = kotlin.math.ceil(resultadoValor)
        }

        val valorFormatado = NumberFormat.getCurrencyInstance().format(resultadoValor)
        binding.resultadoTexto.text = getString(R.string.resultado, valorFormatado)
    }

}