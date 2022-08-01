package com.example.chapter5_5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class OnBoardingFragment(
    private val titleRes: String,
    private val boldTitleRes: String,
    private val imgRes: Int
) : Fragment() {

    lateinit var onBoardingTitleTv: TextView
    lateinit var onBoardingBoldTitleTv: TextView
    lateinit var onBoardingImageIv: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_on_boarding, container, false)

        onBoardingTitleTv = view.findViewById(R.id.on_boarding_title_tv)
        onBoardingBoldTitleTv = view.findViewById(R.id.on_boarding_bold_title_tv)
        onBoardingImageIv = view.findViewById(R.id.on_boarding_image_iv)

        onBoardingTitleTv.text = titleRes
        onBoardingBoldTitleTv.text = boldTitleRes
        onBoardingImageIv.setImageResource(imgRes)

        return view
    }
}