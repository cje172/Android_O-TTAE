package com.example.chapter5_5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView

class QuestionFragment : Fragment() {

    lateinit var questionProgressSb: SeekBar
    lateinit var questionProgressTv: TextView
    lateinit var questionContentTv: TextView
    lateinit var questionAnswerTopBtn: Button
    lateinit var questionAnswerBottomBtn: Button

    var result = arrayOfNulls<String>(5)  // 사용자의 답변을 저장할 배열

    //var result = arrayOfNulls<Int>(5)
    var resultS: String = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_question, container, false)

        questionProgressSb = view.findViewById(R.id.question_progress_sb)
        questionProgressTv = view.findViewById(R.id.question_progress_tv)
        questionContentTv = view.findViewById(R.id.question_content_tv)
        questionAnswerTopBtn = view.findViewById(R.id.question_answer_top_btn)
        questionAnswerBottomBtn = view.findViewById(R.id.question_answer_bottom_btn)
        //초기화
        resultS = ""

        val transaction = (context as MainActivity).supportFragmentManager.beginTransaction()

        // 상단 답 버튼 클릭 시
        questionAnswerTopBtn.setOnClickListener {
            questionProgressSb.progress += 25  // 진행률 증가


            // 진행률에 따른 질문, 답, 진행된 질문 개수 설정
            when (questionProgressSb.progress) {
                25 -> {
                    result[0] = questionAnswerTopBtn.text.toString()
                    //위에 답 클릭
                    resultS += "0"
                    questionProgressTv.text = "2/5"
                    questionContentTv.text = "휴일을 어떻게 보내나요?"
                    questionAnswerTopBtn.text = "집에서 이것 저것 하면서 논다"
                    questionAnswerBottomBtn.text = "밖에서 여기저기 다니면서 논다"

                }
                50 -> {
                    result[1] = questionAnswerTopBtn.text.toString()
                    //위에 답 클릭
                    resultS += "0"
                    questionProgressTv.text = "3/5"
                    questionContentTv.text = "운동을 좋아하나요?"
                    questionAnswerTopBtn.text = "단백질 얼마 들어있어?를 외치는 운동인"
                    questionAnswerBottomBtn.text = "숨쉬기 운동은 잊지 않는 허약인"

                }
                75 -> {
                    result[2] = questionAnswerTopBtn.text.toString()
                    //위에 답 클릭
                    resultS += "0"
                    questionProgressTv.text = "4/5"
                    questionContentTv.text = "친구는 평소에"
                    questionAnswerTopBtn.text = "없는 것이 없는 보부상 스타일"
                    questionAnswerBottomBtn.text = "있는 것이 없는 미니멀 리스트"

                }
                100 -> {
                    result[3] = questionAnswerTopBtn.text.toString()
                    //위에 답 클릭
                    resultS += "0"
                    questionProgressTv.text = "5/5"
                    questionContentTv.text = "친구의 식성은"
                    questionAnswerTopBtn.text = "진골 한국인 한식파"
                    questionAnswerBottomBtn.text = "폰 외국인 양식파"



                    questionAnswerTopBtn.setOnClickListener {
                        result[4] = questionAnswerTopBtn.text.toString()
                        //위에 답 클릭
                        resultS += "0"

                        // 결과지로 이동
                        sendAnswers()
//                        transaction.replace(R.id.main_frm, ResultFragment())
//                        transaction.addToBackStack(null)
//                        transaction.commit()
                    }

                    questionAnswerBottomBtn.setOnClickListener {
                        result[4] = questionAnswerBottomBtn.text.toString()
                        //아래 답 클릭
                        resultS += "1"

                        // 결과지로 이동
                        sendAnswers()
//                        transaction.replace(R.id.main_frm, ResultFragment())
//                        transaction.addToBackStack(null)
//                        transaction.commit()
                    }

                }
                else -> {
                    // 결과지로 이동

                    sendAnswers()
//                    transaction.replace(R.id.main_frm, ResultFragment())
//                    transaction.addToBackStack(null)
//                    transaction.commit()
                }
            }
        }

        // 하단 답 버튼 클릭 시
        questionAnswerBottomBtn.setOnClickListener {
            questionProgressSb.progress += 25  // 진행률 증가

            // 진행률에 따른 질문, 답, 진행된 질문 개수 설정
            when (questionProgressSb.progress) {
                25 -> {
                    result[0] = questionAnswerBottomBtn.text.toString()
                    //아래 답 클릭
                    resultS += "1"

                    questionProgressTv.text = "2/5"
                    questionContentTv.text = "휴일을 어떻게 보내나요?"
                    questionAnswerTopBtn.text = "집에서 이것 저것 하면서 논다"
                    questionAnswerBottomBtn.text = "밖에서 여기저기 다니면서 논다"

                }
                50 -> {
                    result[1] = questionAnswerBottomBtn.text.toString()
                    //아래 답 클릭
                    resultS += "1"

                    questionProgressTv.text = "3/5"
                    questionContentTv.text = "운동을 좋아하나요?"
                    questionAnswerTopBtn.text = "단백질얼마 들어있어?를 외치는 운동인"
                    questionAnswerBottomBtn.text = "숨쉬기 운동은 잊지 않는 허약인"

                }
                75 -> {
                    result[2] = questionAnswerBottomBtn.text.toString()
                    //아래 답 클릭
                    resultS += "1"

                    questionProgressTv.text = "4/5"
                    questionContentTv.text = "친구는 평소에 "
                    questionAnswerTopBtn.text = "없는 것이 없는 보부상 스타일"
                    questionAnswerBottomBtn.text = "있는 것이 없는 미니멀 리스트"

                }
                100 -> {
                    result[3] = questionAnswerBottomBtn.text.toString()
                    //아래 답 클릭
                    resultS += "1"

                    questionProgressTv.text = "5/5"
                    questionContentTv.text = "친구의 식성은?"
                    questionAnswerTopBtn.text = "진골 한국인 한식파"
                    questionAnswerBottomBtn.text = "폰 외국인 양식파"

                    questionAnswerBottomBtn.setOnClickListener {
                        result[4] = questionAnswerBottomBtn.text.toString()
                        //아래 답 클릭
                        resultS += "1"

                        // 결과지로 이동
                        sendAnswers()
//                        transaction.replace(R.id.main_frm, ResultFragment())
//                        transaction.addToBackStack(null)
//                        transaction.commit()
                    }

                    questionAnswerTopBtn.setOnClickListener {
                        result[4] = questionAnswerTopBtn.text.toString()
                        //아래 답 클릭
                        resultS += "1"

                        // 결과지로 이동
                        sendAnswers()
//                        transaction.replace(R.id.main_frm, ResultFragment())
//                        transaction.addToBackStack(null)
//                        transaction.commit()
                    }

                }
                else -> {
                    // 결과지로 이동
                    sendAnswers()
//                    transaction.replace(R.id.main_frm, ResultFragment())
//                    transaction.addToBackStack(null)
//                    transaction.commit()
                }
            }
        }





        return view
    }


    fun sendAnswers() {

        val mActivity = activity as MainActivity
        mActivity.setDataAtFragment(ResultFragment(), resultS)


    }
}