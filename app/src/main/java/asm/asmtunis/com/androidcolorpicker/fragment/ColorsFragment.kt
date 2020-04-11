package asm.asmtunis.com.androidcolorpicker.fragment

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import asm.asmtunis.com.androidcolorpicker.R
import asm.asmtunis.com.androidcolorpicker.data.getColorsFromPosition
import asm.asmtunis.com.androidcolorpicker.listener.ColorListener

/**
 * A simple [Fragment] subclass.
 */
class ColorsFragment(position: Int) : Fragment() {
    var position: Int? = position
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
    var listener: (()->Unit)? = null

   /* fun setOnHeadlineSelectedListener(callback: OnHeadlineSelectedListener) {
        this.callback = callback
    }*/
    var colorListener: ColorListener? = null

   // internal var callback: colorListener

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



        color1.setOnClickListener{
          //  colorListener?.onColorSelected(5, "hhh")
            Log.d("listener","hello")
            listener?.invoke()
        }

        return view
    }

    private fun linkColorsWithView() {
        colorsHashMap.put(color1.id,fragmentColorsList.get(0))
        colorsHashMap= hashMapOf(color1.id to fragmentColorsList.get(0),
            color2.id to fragmentColorsList.get(1),
            color3.id to fragmentColorsList.get(2),
            color4.id to fragmentColorsList.get(3),
            color5.id to fragmentColorsList.get(4),
            color6.id to fragmentColorsList.get(5),
            color7.id to fragmentColorsList.get(6),
            color8.id to fragmentColorsList.get(7),
            color9.id to fragmentColorsList.get(8)
        )
    }

    private fun initialiseColorsList(): ArrayList<Int> {
        var fragmentColorsList = getColorsFromPosition(position!!)
        return fragmentColorsList
    }

    private fun ChangeColor(fragmentColorsList: ArrayList<Int>) {
        changingTheShapeColor( fragmentColorsList.get(0), color1)
        if (fragmentColorsList.size > 1)
            changingTheShapeColor( fragmentColorsList.get(1), color2)
        if (fragmentColorsList.size > 2)
            changingTheShapeColor( fragmentColorsList.get(2), color3)
        if (fragmentColorsList.size > 3)
            changingTheShapeColor( fragmentColorsList.get(3), color4)
        if (fragmentColorsList.size > 4)
            changingTheShapeColor( fragmentColorsList.get(4), color5)
        if (fragmentColorsList.size > 5)
            changingTheShapeColor( fragmentColorsList.get(5), color6)
        if (fragmentColorsList.size > 6)
            changingTheShapeColor( fragmentColorsList.get(6), color7)
        if (fragmentColorsList.size > 7)
            changingTheShapeColor( fragmentColorsList.get(7), color8)
        if (fragmentColorsList.size > 8)
            changingTheShapeColor( fragmentColorsList.get(8), color9)
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

    interface OnHeadlineSelectedListener {
        fun onArticleSelected(position: Int)
    }

}
