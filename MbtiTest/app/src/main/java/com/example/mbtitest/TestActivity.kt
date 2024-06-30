package com.example.mbtitest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.viewpager2.widget.ViewPager2

class TestActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    //전역 변수 선언

    val questionnaireResults = QuestionResult()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        viewPager = findViewById(R.id.view_ViewPager)
        //뷰 페이저 아이디를 호출하여 전역 변수 갑에 저장
        viewPager.adapter = ViewPagerAdapter(this)
        //전역 변수 값에 'adapter'를 걸고 'ViewPagerAdapter'에 현재 프래그먼트를 넣어 저장
        viewPager.isUserInputEnabled = false
        //모든 질문의 응답이 정상적으로 3개다 선택 됬을 경우에만 다음으로 넘기는 기능을 사용하기 때문에 전역 변수 값에 isUserInputEnabled걸고 이를 false로 저장
    }

    //다음 페이지로 넘기는 함수
    fun moveNewQuestion() {
        Log.d("jblee", "viewPager.currentItem = ${viewPager.currentItem}")

        if (viewPager.currentItem == 3) {
        //뷰 페이지가 0부터 시작하기 때문에 3이 마지막 페이지 번호
            Log.d("jblee", "result = ${ArrayList(questionnaireResults.results)}")
            val intent = Intent(this, ResultActivity::class.java)
            //마지막 페이지 -> 결과 화면으로 이동
            intent.putIntegerArrayListExtra("result", ArrayList(questionnaireResults.results))
            //'QuestionResult'에 저장된 결과를 'ResultActivity'가 받고 화면에 출력
            startActivity(intent)
        } else {
        //다음 페이지 이동
            val nextItem = viewPager.currentItem + 1
            //다음 페이지 번호 저장
            if (nextItem < viewPager.adapter?.itemCount ?: 0) {
            //다음 페이지로 넘어가기전 현재 페이지가 마지막 페이지인지 확인
                viewPager.setCurrentItem(nextItem, true)
                //다음 페이지로 이동
            }
        }
    }
}

//클래스 생성
class QuestionResult {
    val results = mutableListOf<Int>()
    //질문지의 응답을 저장되는 공간 생성

    fun addRespons(response: List<Int>) {
        //리스트에 선택한 답변 번호가 저장됨
        val mostFragment = response.groupingBy { it }.eachCount().maxByOrNull { it.value }?.key
        //'groupingBy'으로 'List에 저장 된 값들을 그룹으로 묶고 'eachCount'으로 번호 개수를 세고, 'maxByOrNull'으로 제일 많은 번호 골라 키값으로 저장
        mostFragment?.let { results.add(it) }
        //저장된 키값을 'result'에 애드
    }
}