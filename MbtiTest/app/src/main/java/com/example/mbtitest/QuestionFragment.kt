package com.example.mbtitest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class QuestionFragment : Fragment() {
    //Fragment를 상속

    private var questionTypes: Int = 0
    //전역 변수로 'questionTypes'를 선언하고 초기값 0을 저장

    private val questionTitle = listOf(
        //질문 제목 리스트
        R.string.question1_title,
        R.string.question2_title,
        R.string.question3_title,
        R.string.question4_title
    )

    private val questionText = listOf(
        //질문 리스트
        listOf(R.string.question1_1, R.string.question1_2, R.string.question1_3),
        //질문1 리스트
        listOf(R.string.question2_1, R.string.question2_2, R.string.question2_3),
        //질문2 리스트
        listOf(R.string.question3_1, R.string.question3_2, R.string.question3_3),
        //질문3 리스트
        listOf(R.string.question4_1, R.string.question4_2, R.string.question4_3)
        //질문4 리스트
    )

    private val quesrtionAnswer = listOf(
        //질문 응답 리스트
        listOf(
            //질문1의 응답 리스트
            listOf(R.string.question1_1_answer1, R.string.question1_1_answer2),
            //질문1_1의 응답 리스트
            listOf(R.string.question1_2_answer1, R.string.question1_2_answer2),
            //질문1_2의 응답 리스트
            listOf(R.string.question1_3_answer1, R.string.question1_3_answer2)
            //질문1_3의 응답 리스트
        ),
        listOf(
            //질문2의 응답 리스트
            listOf(R.string.question2_1_answer1, R.string.question2_1_answer2),
            //질문2_1의 응답 리스트
            listOf(R.string.question2_2_answer1, R.string.question2_2_answer2),
            //질문2_2의 응답 리스트
            listOf(R.string.question2_3_answer1, R.string.question2_3_answer2)
            //질문2_2의 응답 리스트
        ),
        listOf(
            //질문3의 응답 리스트
            listOf(R.string.question3_1_answer1, R.string.question3_1_answer2),
            //질문3_1의 응답 리스트
            listOf(R.string.question3_2_answer1, R.string.question3_2_answer2),
            //질문3_2의 응답 리스트
            listOf(R.string.question3_3_answer1, R.string.question3_3_answer2)
            //질문3_3의 응답 리스트
        ),
        listOf(
            //질문4의 응답 리스트
            listOf(R.string.question4_1_answer1, R.string.question4_1_answer2),
            //질문4_1의 응답 리스트
            listOf(R.string.question4_2_answer1, R.string.question4_2_answer2),
            //질문4_2의 응답 리스트
            listOf(R.string.question4_3_answer1, R.string.question4_3_answer2)
            //질문4_3 응답 리스트
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            questionTypes = it.getInt(argQuestionType)
            //현재 화면이 몇 번째 화면인지 키 값을 넣고 전역 변수 값에 저장
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_question, container, false)
        //'inflate'으로 'fragment_question'레이아웃 호출

        //질문 제목 텍스트를 변경하는 코드
        val title: TextView = view.findViewById(R.id.questionTitle_Text)
        //질문 제목 아이디를 호출
        title.text = getString(questionTitle[questionTypes])
        //'questionTypes' 번호에 해당되는 'questionTitle' 번호를 호출

        val questionTextView = listOf<TextView>(
        //'TextView'타입의 리스트를 저장
            view.findViewById(R.id.question1_Text),
            view.findViewById(R.id.question2_Text),
            view.findViewById(R.id.question3_Text)
            //3개의 질문 아이디를 리스트에 저장
        )

        val answerRadio = listOf<RadioGroup>(
        //'RadioGroup'타입의 리스트를 저장
            view.findViewById(R.id.answer1_Group),
            view.findViewById(R.id.answer2_Group),
            view.findViewById(R.id.answer3_Group)
            //3개의 질문 응답 그룹 아이디를 리스트에 저장

        )

        //3개의 질문 제목 아이디와 3개의 질문 응답 그룹 아이디를 반복문으로 돌리는 코드
        for (i in questionTextView.indices) {
        //질문 개수 만큼 반복
            questionTextView[i].text = getString(questionText[questionTypes][i])
            //질문 제목 리스트의 i번째부터 화면에 출력

            //질문 응답 기능
            val radioButton1 = answerRadio[i].getChildAt(0) as RadioButton
            //'getChildAt'에 0번째 인덱스(1번째 질문 응답 텍스트)를 호출하고 'answerRadio'의 i번째에 걸어 저장
            val radioButton2 = answerRadio[i].getChildAt(1) as RadioButton
            //'getChildAt'에 1번째 인덱스(2번째 질문 응답 텍스트)를 호출하고 'answerRadio'의 i번째에 걸어 저장

            radioButton1.text = getString(quesrtionAnswer[questionTypes][i][0])
            //'quesrtionAnswer'의 리스트에서 'questionTypes'의 i번째 인덱스 0번을 저장
            radioButton2.text = getString(quesrtionAnswer[questionTypes][i][1])
            //'quesrtionAnswer'의 리스트에서 'questionTypes'의 i번째 인덱스 1번을 저장
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val answerRadioGroup = listOf<RadioGroup>(
        //'RadioGroup'타입의 리스트를 저장
            view.findViewById(R.id.answer1_Group),
            view.findViewById(R.id.answer2_Group),
            view.findViewById(R.id.answer3_Group)
            //3개의 질문 응답 그룹 아이디를 리스트에 저장
        )

        val nextButton: Button = view.findViewById(R.id.next_Button)
        //버튼 아이디 호출
        nextButton.setOnClickListener {

            //모든 질문에 답하지 않았을 때의 예외처리
            val isAllanswer = answerRadioGroup.all { it.checkedRadioButtonId != -1 }
            //모든 질문에 체크가 되어있으면 'true'로 처리, 안되어 있으면 'false'로 처리

            if (isAllanswer) {
                val respone = answerRadioGroup.map { radioGroup ->
                    val firstRadio = radioGroup.getChildAt(0) as RadioButton
                    if (firstRadio.isChecked) 1 else 2
                }
                //두개의 버튼 중 첫번쨰 버튼이 체크가 되어있으면 'respone'에 1을 저장하고 안되어 있으면 2을 저장

                (activity as? TestActivity)?.questionnaireResults?.addRespons(respone)
                //'respone'에 저장된 값들을 'TestActivity'호출하여 'addRespons'에 저장
                (activity as? TestActivity)?.moveNewQuestion()
                //모든 질문 응답이 되있으면 'moveNewQuestion'를 호출하여 다음 페이지로 이동, 만약 현재 페이지가 마지막 페이지면 결과 화면으로 이동

            }
            else {
                Toast.makeText(context, "모든 질문에 답하시오", Toast.LENGTH_SHORT).show()
                //모든 질문에 하나라도 답하지 않을 경우 출력되는 시스템 메시지
            }
        }

        if (questionTypes == 3) {
            nextButton.setText("결과 확인")
        }
    }

    //생성자 생성
    companion object {
        private const val argQuestionType = "QusetionType"
        //새로운 페이지 번호를 받기 위한 키 값 선언

        fun newInstance(questionType: Int): QuestionFragment {
        //'Int'를 가진 매개변수 하나 선언하고 'QuestionFragment'를 상속
            val fragment = QuestionFragment()
            val arg = Bundle()
            // 데이터를 받을 때는 번들을 사용
            arg.putInt(argQuestionType, questionType)
            //새로운 페이지 번호 저장
            fragment.arguments = arg
            return fragment
        }
    }
}