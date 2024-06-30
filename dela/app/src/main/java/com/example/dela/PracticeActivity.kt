package com.example.dela

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PracticeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_practice)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.practice)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val start = findViewById<TextView>(R.id.startText)
        val click = findViewById<Button>(R.id.clickButton)

        click.setOnClickListener {
            start.text = "Welcome~"
        }
        //클릭했을 때 일어나는 동작

        var i = 0
        while (i < 100) {
            //i가 100보다 작을때 까지

            start.text = i.toString()
            i += 1 //증감 연산자
            //1씩 증가 1 2 3 4 5...
        }
    }
}