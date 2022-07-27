package rs.raf.projekat2uros_nikolic_2619rn.presentation.view.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import rs.raf.projekat2uros_nikolic_2619rn.presentation.view.fragments.BeleskeFragment
import rs.raf.projekat2uros_nikolic_2619rn.presentation.view.fragments.ListFragment
import rs.raf.projekat2uros_nikolic_2619rn.presentation.view.fragments.StatistikaFragment

class MainPagerAdapter(
    fragmentManager: FragmentManager,
    private val context: Context?
) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        private const val ITEM_COUNT = 3
        const val FRAGMENT_1 = 0
        const val FRAGMENT_2 = 1
        const val FRAGMENT_3 = 2
    }

    override fun getItem(position: Int): Fragment {
        return when(position) {
            FRAGMENT_1 -> ListFragment()
            FRAGMENT_2 -> BeleskeFragment()
            else -> StatistikaFragment()
        }
    }

    override fun getCount(): Int {
        return ITEM_COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            FRAGMENT_1 -> "Raspored"
            FRAGMENT_2 -> "Beleske"
            else -> "Statistika"
        }
    }

}