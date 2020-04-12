package asm.asmtunis.com.androidcolorpicker.data.dialog

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.opengl.Visibility
import android.os.Bundle
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
import asm.asmtunis.com.androidcolorpicker.data.getColorsPagesNumber
import asm.asmtunis.com.androidcolorpicker.fragment.ColorsFragment
import asm.asmtunis.com.androidcolorpicker.listener.ColorListener
import asm.asmtunis.com.androidcolorpicker.viewmodel.ColorViewModel
import kotlinx.android.synthetic.main.colors_dialog_layout.*
import java.security.acl.Owner


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