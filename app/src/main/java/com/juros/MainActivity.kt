package com.juros

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.TextFieldValue
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    var capital by remember { mutableStateOf(TextFieldValue("0.0")) }
    var taxaJuros by remember { mutableStateOf(TextFieldValue("0.0")) }
    var tempo by remember { mutableStateOf(TextFieldValue("0.0")) }
    var resultado by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        TextField(
            value = capital,
            onValueChange = { capital = it },
            label = { Text("Capital") }
        )
        TextField(
            value = taxaJuros,
            onValueChange = { taxaJuros = it },
            label = { Text("Taxa de juros") }
        )
        TextField(
            value = tempo,
            onValueChange = { tempo = it },
            label = { Text("Tempo (anos)") }
        )
        Button(
            onClick = {
                val capitalValue = capital.text.toFloat()
                val taxaJurosValue = taxaJuros.text.toFloat() / 100 / 12
                val tempoValue = tempo.text.toFloat() * 12

                val valorFuturo = capitalValue * (1 + taxaJurosValue).pow(tempoValue)
                resultado = valorFuturo.toString()
            },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text("Calcular")
        }
        Text(
            text = resultado,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )
    }
}
