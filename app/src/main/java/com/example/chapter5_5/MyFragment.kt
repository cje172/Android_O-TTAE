package com.example.chapter5_5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import androidx.viewpager2.widget.ViewPager2



class MyFragment : Fragment() {
    lateinit var Santa: FrameLayout


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_my, container, false)



        Santa = view.findViewById(R.id.santa)




        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my, container, false)
    }

//    private fun transXAni() {
//        btn_transX_left.setOnClickListener {
//            btn_transX_left.animate().translationX(-400f * dp).setDuration(duration).withEndAction {
//                btn_transX_left.translationX = 0f
//            }.start()
//        }
//        btn_transX_right.setOnClickListener {
//            btn_transX_right.animate().translationX(400f * dp).setDuration(duration).withEndAction {
//                btn_transX_right.translationX = 0f
//            }.start()
//
//        }
//    }

}