package com.example.mbtitest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import java.util.Locale

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val result = intent.getIntegerArrayListExtra("result") ?: arrayListOf()

        val resultType = listOf(
            listOf("E", "I"),
            listOf("N", "S"),
            listOf("T", "F"),
            listOf("J", "P")
        )

        var resultString = ""
        for (i in result.indices) {
            resultString += resultType[i][result[i] - 1]
        }

        val resultValue: TextView = findViewById(R.id.resultValue_Text)
        resultValue.text = resultString

        val resultImage: ImageView = findViewById(R.id.result_Image)
        val resourceImage = resources.getIdentifier(
            "ic_${resultString.lowercase(Locale.ROOT)}","drawable",packageName)
        resultImage.setImageResource(resourceImage)

        val retryButton: Button = findViewById(R.id.retry_Button)
        retryButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}