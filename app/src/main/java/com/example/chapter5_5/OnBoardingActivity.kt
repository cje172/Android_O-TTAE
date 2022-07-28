package com.example.chapter5_5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import java.util.ArrayList

class OnBoardingActivity : AppCompatActivity() {

    lateinit var onBoardingVp: ViewPager2
    lateinit var onBoardingTb: TabLayout
    lateinit var onBoardingStartBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Chapter55)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)

        onBoardingVp = findViewById(R.id.on_boarding_vp)
        onBoardingTb = findViewById(R.id.on_boarding_tb)
        onBoardingStartBtn = findViewById(R.id.on_boarding_start_btn)


        // ViewPager2 어댑터 연결
        val onBoardingFm = supportFragmentManager
        val onBoardingLifecycle = lifecycle
        val onBoardingVPAdapter = OnBoardingVPAdapter(onBoardingFm, onBoardingLifecycle)

        onBoardingVp.adapter = onBoardingVPAdapter
        onBoardingVp.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        TabLayoutMediator(onBoardingTb, onBoardingVp) { tab, position ->
        }.attach()

        onBoardingVPAdapter.addFragment(
            OnBoardingFragment(
                "질문지를 통해 선물",
                "추천받기",
                R.drawable.on_boarding_first_img
            )
        )
        onBoardingVPAdapter.addFragment(
            OnBoardingFragment(
                "추천선물 리스트",
                "모아보기",
                R.drawable.on_boarding_first_img
            )
        )
        onBoardingVPAdapter.addFragment(
            OnBoardingFragment(
                "마음을 전할 때",
                "이거 어때?",
                R.drawable.on_boarding_last_img
            )
        )

        onBoardingVp.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (onBoardingVp.currentItem == 2) {
                    onBoardingStartBtn.visibility = View.VISIBLE
                } else {
                    onBoardingStartBtn.visibility = View.INVISIBLE
                }
            }
        })


        // 시작하기 버튼 클릭 시, 로그인 화면으로 이동
        onBoardingStartBtn.setOnClickListener {
            // 로그인 화면으로 이동 수정 예정
//            startActivity(Intent(this, MainActivity::class.java))
            startActivity(Intent(this, RegisterPage::class.java))
//            finish()
        }

    }


    private inner class OnBoardingVPAdapter(
        fragmentManager: FragmentManager,
        getLifecycle: Lifecycle
    ) :
        FragmentStateAdapter(fragmentManager, getLifecycle) {

        private val fragmentList: ArrayList<Fragment> = ArrayList()

        override fun getItemCount(): Int = fragmentList.size

        override fun createFragment(position: Int): Fragment = fragmentList[position]

        fun addFragment(fragment: Fragment) {
            fragmentList.add(fragment)
            notifyItemInserted(fragmentList.size - 1)
        }
    }
}