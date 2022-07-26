package com.example.chapter5_5

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {

    lateinit var homeBannerVp: ViewPager2
    lateinit var homeBannerTb: TabLayout
    lateinit var homeCategoryContentTb: TabLayout
    lateinit var homeCategoryContentVp: ViewPager2
    lateinit var homeGiftBtn: Button
    lateinit var homeTasteBtn: Button

    var currentPosition = 0
    val handler = Handler(Looper.getMainLooper()) {
        setPage()
        true
    }

    private val homeCategoryTab = arrayListOf("생일", "연인", "부모님", "가벼운 선물", "럭셔리")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)


        homeBannerVp = view.findViewById(R.id.home_banner_vp)
        homeBannerTb = view.findViewById(R.id.home_banner_tb)
        homeCategoryContentTb = view.findViewById(R.id.home_category_content_tb)
        homeCategoryContentVp = view.findViewById(R.id.home_category_content_vp)
        homeGiftBtn = view.findViewById(R.id.home_gift_btn)
        homeTasteBtn = view.findViewById(R.id.home_taste_btn)

        // 비밀선물 버튼 클릭 시 비밀선물 페이지로 이동
        homeGiftBtn.setOnClickListener {
            (context as MainActivity).supportFragmentManager.beginTransaction().replace(R.id.main_frm, GiftFragment()).commitAllowingStateLoss()
        }

        // 취향저격 선물 버튼 클릭 시 취향선물 페이지로 이동
        homeTasteBtn.setOnClickListener {
            (context as MainActivity).supportFragmentManager.beginTransaction().replace(R.id.main_frm, TasteFragment()).commitAllowingStateLoss()
        }


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


        // Home 화면 카테고리 - Tablayout와 ViewPager2 연결
//        val homeCategoryAdapter = HomeCategoryVPAdapter(this)
        val fm = childFragmentManager
        val lifecycle = viewLifecycleOwner.lifecycle
        val homeCategoryAdapter = HomeCategoryVPAdapter(fm, lifecycle)
        homeCategoryContentVp.adapter = homeCategoryAdapter

        TabLayoutMediator(homeCategoryContentTb, homeCategoryContentVp) { tab, position ->
            tab.text = homeCategoryTab[position]
        }.attach()

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