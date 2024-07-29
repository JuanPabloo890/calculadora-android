package com.example.aplication1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import net.objecthunter.exp4j.ExpressionBuilder
import kotlin.math.*

class MainActivity : AppCompatActivity() {

    private lateinit var input: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        input = findViewById(R.id.input)
        val button0: Button = findViewById(R.id.button0)
        val button1: Button = findViewById(R.id.button1)
        val button2: Button = findViewById(R.id.button2)
        val button3: Button = findViewById(R.id.button3)
        val button4: Button = findViewById(R.id.button4)
        val button5: Button = findViewById(R.id.button5)
        val button6: Button = findViewById(R.id.button6)
        val button7: Button = findViewById(R.id.button7)
        val button8: Button = findViewById(R.id.button8)
        val button9: Button = findViewById(R.id.button9)
        val buttonAdd: Button = findViewById(R.id.buttonAdd)
        val buttonless: Button = findViewById(R.id.buttonless)
        val buttonpor: Button = findViewById(R.id.buttonpor)
        val buttondiv: Button = findViewById(R.id.buttondiv)
        val buttonpoint: Button = findViewById(R.id.buttonpoint)
        val buttonequal: Button = findViewById(R.id.buttonequal)
        val buttonSin: Button = findViewById(R.id.buttonSin)
        val buttonCos: Button = findViewById(R.id.buttonCos)
        val buttonTan: Button = findViewById(R.id.buttonTan)
        val buttonC: Button = findViewById(R.id.buttonC)

        val buttons = listOf(
            button0, button1, button2, button3, button4, button5, button6, button7, button8, button9
        )
        buttons.forEach { button ->
            button.setOnClickListener { appendToInput((it as Button).text) }
        }

        buttonAdd.setOnClickListener { appendToInput(" + ") }
        buttonless.setOnClickListener { appendToInput(" - ") }
        buttonpor.setOnClickListener { appendToInput(" * ") }
        buttondiv.setOnClickListener { appendToInput(" / ") }
        buttonpoint.setOnClickListener { appendToInput(".") }
        buttonC.setOnClickListener { input.text.clear() }

        buttonequal.setOnClickListener {
            val expression = input.text.toString()
            try {
                val result = evaluateExpression(expression)
                input.setText(result.toString())
            } catch (e: Exception) {
                Toast.makeText(this, "Error en la expresión", Toast.LENGTH_SHORT).show()
            }
        }

        buttonSin.setOnClickListener { calculateTrigFunction(::sin) }
        buttonCos.setOnClickListener { calculateTrigFunction(::cos) }
        buttonTan.setOnClickListener { calculateTrigFunction(::tan) }
    }

    private fun appendToInput(value: CharSequence) {
        input.append(value)
    }

    private fun evaluateExpression(expression: String): Double {
        val exp = ExpressionBuilder(expression).build()
        return exp.evaluate()
    }

    private fun calculateTrigFunction(trigFunction: (Double) -> Double) {
        val inputText = input.text.toString()
        try {
            val value = inputText.toDouble()
            val result = trigFunction(Math.toRadians(value))
            input.setText(result.toString())
        } catch (e: Exception) {
            Toast.makeText(this, "Entrada inválida", Toast.LENGTH_SHORT).show()
        }
    }
}