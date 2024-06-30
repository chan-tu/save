package com.example.mbtitest

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragmentActivity: FragmentActivity):FragmentStateAdapter(fragmentActivity) {
    //'FragmentStateAdapter'를 상속

    override fun getItemCount(): Int {
        return 4
        //질문 페이지가 4개이기 때문에 리턴값을 4로 설정
    }

    override fun createFragment(position: Int): Fragment {
        return QuestionFragment.newInstance(position)
    }
}