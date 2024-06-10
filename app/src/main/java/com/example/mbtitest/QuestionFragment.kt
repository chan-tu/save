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
    private var questionTypes: Int = 0

    private val questionTitle = listOf(
        R.string.question1_title,
        R.string.question2_title,
        R.string.question3_title,
        R.string.question4_title
    )

    private val questionText = listOf(
        listOf(R.string.question1_1, R.string.question1_2, R.string.question1_3),
        listOf(R.string.question2_1, R.string.question2_2, R.string.question2_3),
        listOf(R.string.question3_1, R.string.question3_2, R.string.question3_3),
        listOf(R.string.question4_1, R.string.question4_2, R.string.question4_3)
    )

    private val quesrtionAnswer= listOf(
        listOf(
            listOf(R.string.question1_1_answer1, R.string.question1_1_answer2),
            listOf(R.string.question1_2_answer1, R.string.question1_2_answer2),
            listOf(R.string.question1_3_answer1, R.string.question1_3_answer2)
        ),
        listOf(
            listOf(R.string.question2_1_answer1, R.string.question2_1_answer2),
            listOf(R.string.question2_2_answer1, R.string.question2_2_answer2),
            listOf(R.string.question2_3_answer1, R.string.question2_3_answer2)
        ),
        listOf(
            listOf(R.string.question3_1_answer1, R.string.question3_1_answer2),
            listOf(R.string.question3_2_answer1, R.string.question3_2_answer2),
            listOf(R.string.question3_3_answer1, R.string.question3_3_answer2)
        ),
        listOf(
            listOf(R.string.question4_1_answer1, R.string.question4_1_answer2),
            listOf(R.string.question4_2_answer1, R.string.question4_2_answer2),
            listOf(R.string.question4_3_answer1, R.string.question4_3_answer2)
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            questionTypes=it.getInt(argQuestionType)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_question,container,false)
        val title:TextView=view.findViewById(R.id.questionTitle_Text)
        title.text=getString(questionTitle[questionTypes])

        val questionTextView= listOf<TextView>(
            view.findViewById(R.id.question1_Text),
            view.findViewById(R.id.question2_Text),
            view.findViewById(R.id.question3_Text)
        )

        val answerRadio= listOf<RadioGroup>(
            view.findViewById(R.id.answer1_Group),
            view.findViewById(R.id.answer2_Group),
            view.findViewById(R.id.answer3_Group)

        )

        for (i in questionTextView.indices){
            questionTextView[i].text=getString(questionText[questionTypes][i])
            val radioButton1=answerRadio[i].getChildAt(0)as RadioButton
            val radioButton2=answerRadio[i].getChildAt(1)as RadioButton
            radioButton1.text=getString(quesrtionAnswer[questionTypes][i][0])
            radioButton2.text=getString(quesrtionAnswer[questionTypes][i][1])
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val answerRadioGroup= listOf<RadioGroup>(
            view.findViewById(R.id.answer1_Group),
            view.findViewById(R.id.answer2_Group),
            view.findViewById(R.id.answer3_Group)
        )

        val nextButton:Button=view.findViewById(R.id.next_Button)
        nextButton.setOnClickListener {
            val isAllanswer=answerRadioGroup.all{it.checkedRadioButtonId!=-1}

            if (isAllanswer){
                val respone=answerRadioGroup.map{radioGroup ->
                    val firstRadio=radioGroup.getChildAt(0)as RadioButton
                    if(firstRadio.isChecked)1 else 2
                }

                (activity as? TestActivity)?.questionnaireResults?.addRespons(respone)
                (activity as? TestActivity)?.moveNewQuestion()
            }else{
                Toast.makeText(context, "모든 질문에 답하시오",Toast.LENGTH_SHORT).show()
            }
        }

        if(questionTypes==3){
            nextButton.setText("결과 확인")
        }
    }

    companion object {
        private const val argQuestionType = "QusetionType"

        fun newInstance(questionType: Int): QuestionFragment {
            val fragment = QuestionFragment()
            val arg = Bundle()
            arg.putInt(argQuestionType, questionType)
            fragment.arguments = arg
            return fragment
        }
    }
}