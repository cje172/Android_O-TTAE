package com.example.chapter5_5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class HomeBannerFragment(private val imgRes: Int, private val titleRes: String, private val captionRes: String) :
    Fragment() {

    lateinit var homeBannerImageIv: ImageView
    lateinit var homeBannerTitleTv: TextView
    lateinit var homeBannerCaptionTv: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_home_banner, container, false)

        homeBannerImageIv = view.findViewById(R.id.home_banner_image_iv)
        homeBannerTitleTv = view.findViewById(R.id.home_banner_title_tv)
        homeBannerCaptionTv = view.findViewById(R.id.home_banner_caption_tv)

        homeBannerImageIv.setImageResource(imgRes)
        homeBannerTitleTv.text = titleRes
        homeBannerCaptionTv.text = captionRes


        return view
    }
}