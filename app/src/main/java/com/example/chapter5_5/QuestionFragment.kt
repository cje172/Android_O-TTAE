package com.example.chapter5_5

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.SeekBar
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.setFragmentResult
import java.util.ArrayList

class QuestionFragment : Fragment() {

    lateinit var questionProgressSb: SeekBar
    lateinit var questionProgressTv: TextView
    lateinit var questionContentTv: TextView
    lateinit var questionAnswerTopBtn: Button
    lateinit var questionAnswerBottomBtn: Button

    var result = arrayOfNulls<String>(5)  // 사용자의 답변을 저장할 배열

    //var result = arrayOfNulls<Int>(5)
    var resultS :String =""
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
        resultS=""

        val transaction = (context as MainActivity).supportFragmentManager.beginTransaction()

        // 상단 답 버튼 클릭 시
        questionAnswerTopBtn.setOnClickListener {
            questionProgressSb.progress += 25  // 진행률 증가

            // 진행률에 따른 질문, 답, 진행된 질문 개수 설정
            when (questionProgressSb.progress) {
                25 -> {
                    result[0] = questionAnswerTopBtn.text.toString()
                    //위에 답 클릭
                    resultS+="0"
                    questionProgressTv.text = "2/5"
                    questionContentTv.text = "주로 무엇을 하고 노는지?"
                    questionAnswerTopBtn.text = "집에서 이것 저것 하면서 논다"
                    questionAnswerBottomBtn.text = "밖에서 여기저기 다니면서 논다"

                }
                50 -> {
                    result[1] = questionAnswerTopBtn.text.toString()
                    //위에 답 클릭
                    resultS+="0"
                    questionProgressTv.text = "3/5"
                    questionContentTv.text = "취미가 무엇인가요?"
                    questionAnswerTopBtn.text = "세 번째 질문의 첫 번째 답"
                    questionAnswerBottomBtn.text = "세 번째 질문의 두 번째 답"

                }
                75 -> {
                    result[2] = questionAnswerTopBtn.text.toString()
                    //위에 답 클릭
                    resultS+="0"
                    questionProgressTv.text = "4/5"
                    questionContentTv.text = "미니멀 vs 맥시멀"
                    questionAnswerTopBtn.text = "미니멀"
                    questionAnswerBottomBtn.text = "맥시멀"

                }
                100 -> {
                    result[3] = questionAnswerTopBtn.text.toString()
                    //위에 답 클릭
                    resultS+="0"
                    questionProgressTv.text = "5/5"
                    questionContentTv.text = "심플 vs 화려"
                    questionAnswerTopBtn.text = "심플"
                    questionAnswerBottomBtn.text = "화려"


                    questionAnswerTopBtn.setOnClickListener {
                        result[4] = questionAnswerTopBtn.text.toString()
                        //위에 답 클릭
                        resultS+="0"

                        // 결과지로 이동
                        sendAnswers()
//                        transaction.replace(R.id.main_frm, ResultFragment())
//                        transaction.addToBackStack(null)
//                        transaction.commit()
                    }

                    questionAnswerBottomBtn.setOnClickListener {
                        result[4] = questionAnswerBottomBtn.text.toString()
                        //위에 답 클릭
                        resultS+="0"

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
                    resultS+="1"

                    questionProgressTv.text = "2/5"
                    questionContentTv.text = "주로 무엇을 하고 노는지?"
                    questionAnswerTopBtn.text = "두 번째 질문의 첫 번째 답"
                    questionAnswerBottomBtn.text = "두 번째 질문의 두 번째 답"

                }
                50 -> {
                    result[1] = questionAnswerBottomBtn.text.toString()
                    //아래 답 클릭
                    resultS+="1"

                    questionProgressTv.text = "3/5"
                    questionContentTv.text = "취미가 무엇인가요?"
                    questionAnswerTopBtn.text = "세 번째 질문의 첫 번째 답"
                    questionAnswerBottomBtn.text = "세 번째 질문의 두 번째 답"

                }
                75 -> {
                    result[2] = questionAnswerBottomBtn.text.toString()
                    //아래 답 클릭
                    resultS+="1"

                    questionProgressTv.text = "4/5"
                    questionContentTv.text = "미니멀 vs 맥시멀"
                    questionAnswerTopBtn.text = "네 번째 질문의 첫 번째 답"
                    questionAnswerBottomBtn.text = "네 번째 질문의 두 번째 답"

                }
                100 -> {
                    result[3] = questionAnswerBottomBtn.text.toString()
                    //아래 답 클릭
                    resultS+="1"

                    questionProgressTv.text = "5/5"
                    questionContentTv.text = "심플 vs 화려"
                    questionAnswerTopBtn.text = "다섯 번째 질문의 첫 번째 답"
                    questionAnswerBottomBtn.text = "다섯 번째 질문의 두 번째 답"

                    questionAnswerBottomBtn.setOnClickListener {
                        result[4] = questionAnswerBottomBtn.text.toString()
                        //아래 답 클릭
                        resultS+="1"

                        // 결과지로 이동
                        sendAnswers()
//                        transaction.replace(R.id.main_frm, ResultFragment())
//                        transaction.addToBackStack(null)
//                        transaction.commit()
                    }

                    questionAnswerTopBtn.setOnClickListener {
                        result[4] = questionAnswerTopBtn.text.toString()
                        //아래 답 클릭
                        resultS+="1"

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


    fun sendAnswers()
    {

        val mActivity = activity as MainActivity
        mActivity.setDataAtFragment(ResultFragment(), resultS)

//            val bundle = Bundle()//번들을 통해 값 전달
//            bundle.putStringArray("result", result)//번들에 넘길 값 저장
//
//            val passBundleBFragment = ResultFragment()//전달하고자 하는 fragment
//            passBundleBFragment.arguments =  bundle (context as MainActivity).supportFragmentManager.beginTransaction()
//                .replace(R.id.main_frm,ResultFragment())
//                .commit()


        //PassBundleFragment는 본인이 전달하고자 하는 Fragment class
        //fragment_container_bundle은 본인의 Fragment가 담겨있는 Container

        // val result = "result"
//            setFragmentResult("requestKey", bundleOf("bundleKey" to result))
//            parentFragmentManager.beginTransaction()
//                .replace(R.id.main_frm, ResultFragment())
//                .commit()

    }
}