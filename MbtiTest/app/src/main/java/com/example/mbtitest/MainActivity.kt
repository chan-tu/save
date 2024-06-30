package com.example.mbtitest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonStart = findViewById<ImageView>(R.id.start_Image)
        //버튼 실행 값 선언 후 'findViewById'로 버튼 이미지 ID 호출

        buttonStart.setOnClickListener {
        //버튼 실행 값에 START이미지를 터치 했을 때 수행되는 동작 리스너 입력
            val intent = Intent(this@MainActivity, TestActivity::class.java)
            //START 이미지 터치 시 'TestActivity'출력
            startActivity(intent)
        }
    }
}