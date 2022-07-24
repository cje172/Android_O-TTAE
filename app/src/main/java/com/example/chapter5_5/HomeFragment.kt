package com.example.chapter5_5

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {

    lateinit var homeBannerVp: ViewPager2
    lateinit var homeBannerTb: TabLayout

    var currentPosition = 0
    val handler = Handler(Looper.getMainLooper()) {
        setPage()
        true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)


        homeBannerVp = view.findViewById(R.id.home_banner_vp)
        homeBannerTb = view.findViewById(R.id.home_banner_tb)


        // Home 화면 상단 배너 - Tablayout와 ViewPager2 연결
        val homeBannerAdapter = HomeBannerVPAdapter(this)
        homeBannerAdapter.addFragment(
            HomeBannerFragment(
                R.drawable.home_banner_img1,
                "빛을 디자인하는 조명",
                "일광전구_스며드는 빛의 매력"
            )
        )
        homeBannerAdapter.addFragment(
            HomeBannerFragment(
                R.drawable.home_banner_img2,
                "임시 제목",
                "임시 수식어"
            )
        )
        homeBannerAdapter.addFragment(
            HomeBannerFragment(
                R.drawable.home_banner_img3,
                "임시 제목",
                "임시 수식어"
            )
        )

        homeBannerVp.adapter = homeBannerAdapter
        homeBannerVp.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        TabLayoutMediator(homeBannerTb, homeBannerVp) { tab, position ->
        }.attach()

        // Home 화면 상단 배너 - ViewPager2 넘기는 Thread
        val thread = Thread(PagerRunnable())
        thread.start()


        return view
    }

    // Home 화면 상단 배너 - 페이지 변경
    fun setPage() {
        if (currentPosition == 3) currentPosition = 0
        homeBannerVp.setCurrentItem(currentPosition, true)
        currentPosition += 1
    }

    // Home 화면 상단 배너 - 3초마다 페이지 넘기기
    inner class PagerRunnable : Runnable {
        override fun run() {
            while (true) {
                Thread.sleep(3000)
                handler.sendEmptyMessage(0)
            }
        }
    }
}