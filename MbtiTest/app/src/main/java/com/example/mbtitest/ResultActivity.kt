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
        //인텐트를 호출할 때 보낸 엑스트라 값

        val resultType = listOf(
            listOf("E", "I"),
            //외향형 - 내향형
            listOf("N", "S"),
            //감각형 - 직관형
            listOf("T", "F"),
            //사고형 - 감점형
            listOf("J", "P")
            //판단형 - 인식형
        )

        var resultString = ""
        for (i in result.indices) {
            //4회 반복
            resultString += resultType[i][result[i] - 1]
            //4번 반복하여 MBTI 결과 값 생성
        }

        val resultValue: TextView = findViewById(R.id.resultValue_Text)
        //결과 텍스트 아이디 호출
        resultValue.text = resultString
        //MBTI 알파벳 4개의 문자 저장

        val resultImage: ImageView = findViewById(R.id.result_Image)
        //결과 이미지 아이디 호출
        val resourceImage = resources.getIdentifier(
            "ic_${resultString.toLowerCase(Locale.ROOT)}","drawable",packageName)
                  //'drawable'에서 MBTI 이미지 파일 이름 호출
        resultImage.setImageResource(resourceImage)

        val retryButton: Button = findViewById(R.id.retry_Button)
        //버튼 아이디 호출
        retryButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            //결과화면 -> 메인화면
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            //기존의 플래그 액티비티를 제거하고 새로운 플래그 액티비티 생성
            startActivity(intent)
        }
    }
}