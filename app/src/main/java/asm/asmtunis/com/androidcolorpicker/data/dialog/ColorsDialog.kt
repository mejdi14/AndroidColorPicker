package asm.asmtunis.com.androidcolorpicker.data.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import asm.asmtunis.com.androidcolorpicker.ColorsFragment
import asm.asmtunis.com.androidcolorpicker.R
import asm.asmtunis.com.androidcolorpicker.data.getColorsPagesNumber
import kotlinx.android.synthetic.main.colors_dialog_layout.*


class ColorsDialog(context: Context) : Dialog(context) {
     var myContext: Context=context
    init {
        setCancelable(false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.colors_dialog_layout)
        initPager()

    }


    private fun initPager() {
        val pagerAdapter = ScreenSlidePagerAdapter(myContext as FragmentActivity)
        pager.adapter = pagerAdapter
        indicator.setViewPager(pager)
    }





    private inner class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = getColorsPagesNumber()

        override fun createFragment(position: Int): Fragment {
            return ColorsFragment(position)
        }
    }
}