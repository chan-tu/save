package com.example.mbtitest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.viewpager2.widget.ViewPager2

class TestActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    val questionnaireResults = QuestionResult()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        viewPager = findViewById(R.id.view_ViewPager)
        viewPager.adapter = ViewPagerAdapter(this)
        viewPager.isUserInputEnabled = false

    }

    fun moveNewQuestion() {
        Log.d("jblee", "viewPager.currentItem = ${viewPager.currentItem}")

        if (viewPager.currentItem == 3) {
            Log.d("jblee", "result = ${ArrayList(questionnaireResults.results)}")
            val intent = Intent(this, ResultActivity::class.java)
            intent.putIntegerArrayListExtra("result", ArrayList(questionnaireResults.results))
            startActivity(intent)
        } else {
            val nextItem = viewPager.currentItem + 1
            if (nextItem < viewPager.adapter?.itemCount ?: 0) {
                viewPager.setCurrentItem(nextItem, true)
            }
        }
    }
}

class QuestionResult {
    val results = mutableListOf<Int>()

    fun addRespons(response: List<Int>) {
        val mostFragment = response.groupingBy { it }.eachCount().maxByOrNull { it.value }?.key
        mostFragment?.let { results.add(it) }
    }
}