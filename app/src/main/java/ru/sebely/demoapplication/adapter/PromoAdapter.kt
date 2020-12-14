package ru.sebely.demoapplication.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import ru.sebely.demoapplication.PromoFragment
import ru.sebely.demoapplication.model.PromoItem

class PromoAdapter(val items : List<PromoItem>, fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        val item = items[position % items.size]
        return PromoFragment.newInstance(item.id, item.backgroundUrl)
    }

    override fun getCount(): Int {
        if (items.isEmpty()) {
            return  0
        }

        return items.size + 1
    }

}