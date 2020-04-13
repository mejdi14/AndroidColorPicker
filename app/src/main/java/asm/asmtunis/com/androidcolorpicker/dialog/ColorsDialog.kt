package asm.asmtunis.com.androidcolorpicker.dialog

import android.app.Dialog
import android.content.Context
import android.util.Log
import android.view.View
import android.view.Window
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.*
import androidx.viewpager2.adapter.FragmentStateAdapter
import asm.asmtunis.com.androidcolorpicker.MainActivity
import asm.asmtunis.com.androidcolorpicker.R
import asm.asmtunis.com.androidcolorpicker.data.ColorsObject
import asm.asmtunis.com.androidcolorpicker.data.getColorsPagesNumber
import asm.asmtunis.com.androidcolorpicker.data.initColors
import asm.asmtunis.com.androidcolorpicker.fragment.ColorsFragment
import asm.asmtunis.com.androidcolorpicker.listener.ColorListener
import asm.asmtunis.com.androidcolorpicker.viewmodel.ColorViewModel
import kotlinx.android.synthetic.main.colors_dialog_layout.*


class ColorsDialog(context: Context) : Dialog(context) {
    var colorListener: ColorListener? = null
    var mainFrame: ConstraintLayout
    var myContext: Context = context
    private  var viewModel: ColorViewModel

    init {
        setCancelable(true)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.colors_dialog_layout)
        mainFrame = findViewById(R.id.main_frame)
        this.getWindow()?.setBackgroundDrawableResource(android.R.color.transparent)
        this.getWindow()?.getAttributes()?.windowAnimations = R.style.DialogAnimation
        window?.setLayout(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        initPager()
        mainFrame.setOnClickListener{
           // colorListener?.onColorSelected(1, "hello")
        }
        initColors()
        var list= ArrayList<Int>()
        list.add(5)
        list.add(3)
        ColorsObject.arrayList.addAll(0,list)
        Log.d("object1",ColorsObject.arrayList.get(0).toString())
        Log.d("object2",ColorsObject.arrayList.get(1).toString())
        Log.d("object3",ColorsObject.arrayList.get(2).toString())

        viewModel = ViewModelProvider(myContext as MainActivity).get(ColorViewModel::class.java)


        viewModel.currentColor.observe(myContext as MainActivity , Observer<String> { item ->
            colorListener?.onColorSelected(1, item)
        })

    }

    private fun initPager() {
        val pagerAdapter = ScreenSlidePagerAdapter(myContext as FragmentActivity)
        pager.adapter = pagerAdapter
        default_indicator.setViewPager(pager)
        white_indicator.setViewPager(pager)
    }

    fun withBlackTheme(): ColorsDialog {
        mainFrame.setBackgroundResource(R.drawable.dialog_night_shape)
        switchToTheWhiteIndicator()
        return this
    }

    private fun switchToTheWhiteIndicator() {
        default_indicator.visibility=View.INVISIBLE
        white_indicator.visibility=View.VISIBLE
    }

    private inner class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = getColorsPagesNumber()
        override fun createFragment(position: Int): Fragment {
            return ColorsFragment(
                position
            )
        }
    }

    fun setColorListener(listener: (color: Int, colorHex: String) -> Unit): ColorsDialog {
        this.colorListener = object : ColorListener {
            override fun onColorSelected(color: Int, colorHex: String) {
                listener(color, colorHex)
            }
        }
        return this
    }
}