package asm.asmtunis.com.mhcolorpicker.dialog

import android.app.Dialog
import android.content.Context
import android.view.View
import android.view.Window
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.viewpager2.adapter.FragmentStateAdapter
import asm.asmtunis.com.mhcolorpicker.R
import asm.asmtunis.com.mhcolorpicker.data.addUserNewColors
import asm.asmtunis.com.mhcolorpicker.data.getColorsPagesNumber
import asm.asmtunis.com.mhcolorpicker.data.initColors
import asm.asmtunis.com.mhcolorpicker.data.initUserOwnColors
import asm.asmtunis.com.mhcolorpicker.fragment.ColorsFragment
import asm.asmtunis.com.mhcolorpicker.helpers.ColorsPosition
import asm.asmtunis.com.mhcolorpicker.listener.ColorListener
import asm.asmtunis.com.mhcolorpicker.viewmodel.ColorViewModel
import kotlinx.android.synthetic.main.colors_dialog_layout.*


class MHColorsDialog(context: Context) : Dialog(context) {
    var colorListener: ColorListener? = null
    var mainFrame: ConstraintLayout
    var myContext: Context = context
    private var viewModel: ColorViewModel

    init {
        setCancelable(true)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.colors_dialog_layout)
        mainFrame = findViewById(R.id.main_frame)
        makeDialogTransparent()
        initDialogAnimations()
        initDialogLayout()
        initColors()
        initPager()

        viewModel = initViewModel()
        observeLiveData()

    }

    private fun makeDialogTransparent() {
        this.getWindow()?.setBackgroundDrawableResource(android.R.color.transparent)
    }

    private fun initDialogAnimations() {
        this.getWindow()?.getAttributes()?.windowAnimations = R.style.DialogAnimationUpToDown
    }

    private fun initDialogLayout() {
        window?.setLayout(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
    }

    private fun observeLiveData() {
        viewModel.currentColor.observe(myContext as LifecycleOwner, Observer<String> { color ->
            this.dismiss()
            colorListener?.onColorSelected(color.toInt(), Integer.toHexString(color.toInt()))
        })
    }

    private fun initViewModel() =
        ViewModelProvider(myContext as ViewModelStoreOwner).get(ColorViewModel::class.java)

    private fun initPager() {
        val pagerAdapter = ScreenSlidePagerAdapter(myContext as FragmentActivity)
        pager.adapter = pagerAdapter
        default_indicator.setViewPager(pager)
        white_indicator.setViewPager(pager)
    }

    fun withBlackTheme(): MHColorsDialog {
        mainFrame.setBackgroundResource(R.drawable.dialog_night_shape)
        switchToTheWhiteIndicator()
        return this
    }

    fun withMyOwnColors(myColors: ArrayList<Int>): MHColorsDialog {
        initUserOwnColors(myColors)
        return this
    }

    fun addColors(myColors: ArrayList<Int>, colorsPosition: ColorsPosition): MHColorsDialog {
        addUserNewColors(myColors, colorsPosition)
        return this
    }


    private fun switchToTheWhiteIndicator() {
        default_indicator.visibility = View.INVISIBLE
        white_indicator.visibility = View.VISIBLE
    }

    private inner class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = getColorsPagesNumber()
        override fun createFragment(position: Int): Fragment {
            return ColorsFragment(
                position
            )
        }
    }


    fun setColorListener(listener: (color: Int, colorHex: String) -> Unit): MHColorsDialog {

        this.colorListener = object : ColorListener {
            override fun onColorSelected(color: Int, colorHex: String) {
                listener(color, colorHex)
            }
        }
        return this
    }
}