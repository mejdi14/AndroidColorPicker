package asm.asmtunis.com.mhcolorpicker.fragment

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import asm.asmtunis.com.mhcolorpicker.R
import asm.asmtunis.com.mhcolorpicker.data.getColorsFromPosition
import asm.asmtunis.com.mhcolorpicker.viewmodel.ColorViewModel

/**
 * A simple [Fragment] subclass.
 */
class ColorsFragment(position: Int) : Fragment(), View.OnClickListener {
    lateinit var color1: ImageView
    lateinit var color2: ImageView
    lateinit var color3: ImageView
    lateinit var color4: ImageView
    lateinit var color5: ImageView
    lateinit var color6: ImageView
    lateinit var color7: ImageView
    lateinit var color8: ImageView
    lateinit var color9: ImageView

    lateinit var fragmentColorsList: ArrayList<Int>
    private lateinit var viewModel: ColorViewModel
    var position: Int? = position
    var colorsHashMap: HashMap<Int, Int?> = HashMap<Int, Int?>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_colors, container, false)
        initialiseColorHolders(view)
        fragmentColorsList = initialiseColorsList()
        ChangeColor(fragmentColorsList)
        linkColorsWithView()
        linkColorsWithListener()
        initViewModel()


        return view
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(activity as ViewModelStoreOwner).get(ColorViewModel::class.java)
    }

    private fun linkColorsWithListener() {
        color1.setOnClickListener(this)
        color2.setOnClickListener(this)
        color3.setOnClickListener(this)
        color4.setOnClickListener(this)
        color5.setOnClickListener(this)
        color6.setOnClickListener(this)
        color7.setOnClickListener(this)
        color8.setOnClickListener(this)
        color9.setOnClickListener(this)
    }

    private fun linkColorsWithView() {
        colorsHashMap.put(color1.id, fragmentColorsList.get(0))
        if (fragmentColorsList.size > 1)
            colorsHashMap.put(color2.id, fragmentColorsList.get(1))
        if (fragmentColorsList.size > 2)
            colorsHashMap.put(color3.id, fragmentColorsList.get(2))
        if (fragmentColorsList.size > 3)
            colorsHashMap.put(color4.id, fragmentColorsList.get(3))
        if (fragmentColorsList.size > 4)
            colorsHashMap.put(color5.id, fragmentColorsList.get(4))
        if (fragmentColorsList.size > 5)
            colorsHashMap.put(color6.id, fragmentColorsList.get(5))
        if (fragmentColorsList.size > 6)
            colorsHashMap.put(color7.id, fragmentColorsList.get(6))
        if (fragmentColorsList.size > 7)
            colorsHashMap.put(color8.id, fragmentColorsList.get(7))
        if (fragmentColorsList.size > 8)
            colorsHashMap.put(color9.id, fragmentColorsList.get(8))

    }

    private fun initialiseColorsList(): ArrayList<Int> {
        var fragmentColorsList = getColorsFromPosition(position!!)
        return fragmentColorsList
    }

    private fun ChangeColor(fragmentColorsList: ArrayList<Int>) {
        changingTheShapeColor(fragmentColorsList.get(0), color1)
        if (fragmentColorsList.size > 1)
            changingTheShapeColor(fragmentColorsList.get(1), color2)
        if (fragmentColorsList.size > 2)
            changingTheShapeColor(fragmentColorsList.get(2), color3)
        if (fragmentColorsList.size > 3)
            changingTheShapeColor(fragmentColorsList.get(3), color4)
        if (fragmentColorsList.size > 4)
            changingTheShapeColor(fragmentColorsList.get(4), color5)
        if (fragmentColorsList.size > 5)
            changingTheShapeColor(fragmentColorsList.get(5), color6)
        if (fragmentColorsList.size > 6)
            changingTheShapeColor(fragmentColorsList.get(6), color7)
        if (fragmentColorsList.size > 7)
            changingTheShapeColor(fragmentColorsList.get(7), color8)
        if (fragmentColorsList.size > 8)
            changingTheShapeColor(fragmentColorsList.get(8), color9)
    }

    private fun changingTheShapeColor(
        color: Int,
        holder: ImageView
    ) {
        val colorDrawable: GradientDrawable = creatingTheRoundShape()
        colorDrawable.setColor(color)
        holder.setImageDrawable(colorDrawable)
    }

    private fun creatingTheRoundShape(): GradientDrawable {
        val colorDrawable: GradientDrawable
        colorDrawable = GradientDrawable()
        colorDrawable.shape = GradientDrawable.OVAL
        return colorDrawable
    }

    private fun initialiseColorHolders(view: View) {
        color1 = view.findViewById(R.id.color1)
        color2 = view.findViewById(R.id.color2)
        color3 = view.findViewById(R.id.color3)
        color4 = view.findViewById(R.id.color4)
        color5 = view.findViewById(R.id.color5)
        color6 = view.findViewById(R.id.color6)
        color7 = view.findViewById(R.id.color7)
        color8 = view.findViewById(R.id.color8)
        color9 = view.findViewById(R.id.color9)
    }

    override fun onClick(p0: View?) {
        viewModel.currentColor.setValue(colorsHashMap.get(p0?.id).toString())
    }


}
