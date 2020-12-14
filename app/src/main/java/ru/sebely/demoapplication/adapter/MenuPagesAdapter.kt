package ru.sebely.demoapplication.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.sebely.demoapplication.MenuFragment
import ru.sebely.demoapplication.model.MenuCategory


class MenuPagesAdapter(val menuCategoryList: List<MenuCategory>, fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        if (menuCategoryList.isEmpty()) {
            return 0
        }

        return Int.MAX_VALUE / 2 // menuCategoryList.size
    }

    override fun createFragment(position: Int): Fragment {
        val modifiedPosition = position % menuCategoryList.size

        val names = menuCategoryList.map { it -> it.categoryName }
        val items = menuCategoryList[modifiedPosition]
        return MenuFragment.newInstance(modifiedPosition, names, items)
    }
}