package com.example.beige_level2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.beige_level2.databinding.ActivityMainBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private var job: Job? = null
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        //setContentView(R. layout. activity_main)의 대체
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.male)) { v, inset ->
            val systemBars = inset.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            inset
        }
        setupButton()
        setRadomValueBetweenOneToHunder()
        setJobAndLaunch()

    }

    private fun setupButton() {
        binding.clickButton.setOnClickListener {
            checkAnswerAndShowToast()
            setJobAndLaunch()
        }
    }

    private fun setRadomValueBetweenOneToHunder() {
        val randomValue = (1..100).random()
        binding.randomText.text = randomValue.toString()
    }

    private fun setJobAndLaunch() {
        job?.cancel()
        job = lifecycleScope.launch {
            for (i in 1..100) {
                if (isActive) {
                    binding.numberText.text = i.toString()
                    delay(500)
                }
            }
        }
    }

    private fun checkAnswerAndShowToast() {
        val numberText = binding.numberText.toString()
        val randomText = binding.randomText.toString()
        if (numberText == randomText) {
            Toast.makeText(this, "성공", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "실패", Toast.LENGTH_SHORT).show()
        }
    }

}