package com.example.bage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private var job:Job?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)){v, insets->
            val system=insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(system.left, system.top, system.right, system.bottom)
            insets
        }
        setupButton()
        setRandomValueBetweenOneToHundred()
        setJobAndLaunch()
    }    
    private fun setupButton() {
        val button = findViewById<Button>(R.id.clickButton)

        button.setOnClickListener {
            job?.cancel()
            checkAnswerAndShowToast()
        }
    }

    private fun setRandomValueBetweenOneToHundred(){
        val random=findViewById<TextView>(R.id.RandeomText)
        val randomNumber=(1..100).random()

        random.text=randomNumber.toString()
    }

    private fun setJobAndLaunch(){
        val textView=findViewById<TextView>(R.id.spraText)

        job=lifecycleScope.launch{
            for (i in 1..100){
                if (isActive) {
                    textView.text=i.toString()
                    delay(500)
                }
            }
        }
    }

    private fun checkAnswerAndShowToast(){
        val textView=findViewById<TextView>(R.id.spraText)
        val randomTextView=findViewById<TextView>(R.id.RandeomText)

        if (textView.text.toString()==randomTextView.text.toString()){
            Toast.makeText(this,"성공",Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this,"실패", Toast.LENGTH_SHORT).show()
        }

    }
}
