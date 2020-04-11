package asm.asmtunis.com.androidcolorpicker

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import asm.asmtunis.com.androidcolorpicker.data.dialog.ColorsDialog
import asm.asmtunis.com.androidcolorpicker.data.getColorsPagesNumber
import asm.asmtunis.com.androidcolorpicker.fragment.ColorsFragment
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {
    lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        context = this

        initPager()

        var colorsDialog=ColorsDialog(this)

        colorsDialog.withBlackTheme()
            .setColorListener { color1, color2 ->
                Log.d("colors",color1.toString())
            }
            .show()

    }

    private fun initPager() {
        val pagerAdapter = ScreenSlidePagerAdapter(this)
        pager.adapter = pagerAdapter
        indicator.setViewPager(pager)
    }





    private inner class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = getColorsPagesNumber()

        override fun createFragment(position: Int): Fragment {
            return ColorsFragment(
                position
            )
            }
        }
}