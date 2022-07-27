package com.example.chapter5_5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.SeekBar
import android.widget.TextView
import java.util.ArrayList

class QuestionFragment : Fragment() {

    lateinit var questionProgressSb: SeekBar
    lateinit var questionProgressTv: TextView
    lateinit var questionContentTv: TextView
    lateinit var questionAnswerTopBtn: Button
    lateinit var questionAnswerBottomBtn: Button

    var result = arrayOfNulls<String>(5)  // 사용자의 답변을 저장할 배열

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


        // 상단 답 버튼 클릭 시
        questionAnswerTopBtn.setOnClickListener {
            questionProgressSb.progress += 25  // 진행률 증가

            // 진행률에 따른 질문, 답, 진행된 질문 개수 설정
            when (questionProgressSb.progress) {
                25 -> {
                    result[0] = questionAnswerTopBtn.text.toString()

                    questionProgressTv.text = "2/5"
                    questionContentTv.text = "주로 무엇을 하고 노는지?"
                    questionAnswerTopBtn.text = "두 번째 질문의 첫 번째 답"
                    questionAnswerBottomBtn.text = "두 번째 질문의 두 번째 답"

                }
                50 -> {
                    result[1] = questionAnswerTopBtn.text.toString()

                    questionProgressTv.text = "3/5"
                    questionContentTv.text = "취미가 무엇인가요?"
                    questionAnswerTopBtn.text = "세 번째 질문의 첫 번째 답"
                    questionAnswerBottomBtn.text = "세 번째 질문의 두 번째 답"

                }
                75 -> {
                    result[2] = questionAnswerTopBtn.text.toString()

                    questionProgressTv.text = "4/5"
                    questionContentTv.text = "미니멀 vs 맥시멀"
                    questionAnswerTopBtn.text = "네 번째 질문의 첫 번째 답"
                    questionAnswerBottomBtn.text = "네 번째 질문의 두 번째 답"

                }
                100 -> {
                    result[3] = questionAnswerTopBtn.text.toString()

                    questionProgressTv.text = "5/5"
                    questionContentTv.text = "심플 vs 화려"
                    questionAnswerTopBtn.text = "다섯 번째 질문의 첫 번째 답"
                    questionAnswerBottomBtn.text = "다섯 번째 질문의 두 번째 답"


                    questionAnswerTopBtn.setOnClickListener {
                        result[4] = questionAnswerTopBtn.text.toString()

                        // 결과지로 이동
                        (context as MainActivity).supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm, ResultFragment()).commitAllowingStateLoss()

                    }

                    questionAnswerBottomBtn.setOnClickListener {
                        result[4] = questionAnswerBottomBtn.text.toString()

                        // 결과지로 이동
                        (context as MainActivity).supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm, ResultFragment()).commitAllowingStateLoss()

                    }

                }
                else -> {
                    // 결과지로 이동
                    (context as MainActivity).supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, ResultFragment()).commitAllowingStateLoss()
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

                    questionProgressTv.text = "2/5"
                    questionContentTv.text = "주로 무엇을 하고 노는지?"
                    questionAnswerTopBtn.text = "두 번째 질문의 첫 번째 답"
                    questionAnswerBottomBtn.text = "두 번째 질문의 두 번째 답"

                }
                50 -> {
                    result[1] = questionAnswerBottomBtn.text.toString()

                    questionProgressTv.text = "3/5"
                    questionContentTv.text = "취미가 무엇인가요?"
                    questionAnswerTopBtn.text = "세 번째 질문의 첫 번째 답"
                    questionAnswerBottomBtn.text = "세 번째 질문의 두 번째 답"

                }
                75 -> {
                    result[2] = questionAnswerBottomBtn.text.toString()

                    questionProgressTv.text = "4/5"
                    questionContentTv.text = "미니멀 vs 맥시멀"
                    questionAnswerTopBtn.text = "네 번째 질문의 첫 번째 답"
                    questionAnswerBottomBtn.text = "네 번째 질문의 두 번째 답"

                }
                100 -> {
                    result[3] = questionAnswerBottomBtn.text.toString()

                    questionProgressTv.text = "5/5"
                    questionContentTv.text = "심플 vs 화려"
                    questionAnswerTopBtn.text = "다섯 번째 질문의 첫 번째 답"
                    questionAnswerBottomBtn.text = "다섯 번째 질문의 두 번째 답"

                    questionAnswerBottomBtn.setOnClickListener {
                        result[4] = questionAnswerBottomBtn.text.toString()

                        // 결과지로 이동
                        (context as MainActivity).supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm, ResultFragment()).commitAllowingStateLoss()

                    }

                    questionAnswerTopBtn.setOnClickListener {
                        result[4] = questionAnswerTopBtn.text.toString()

                        // 결과지로 이동
                        (context as MainActivity).supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm, ResultFragment()).commitAllowingStateLoss()

                    }

                }
                else -> {
                    // 결과지로 이동
                    (context as MainActivity).supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, ResultFragment()).commitAllowingStateLoss()
                }
            }
        }


        return view
    }


}