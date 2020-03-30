package asm.asmtunis.com.androidcolorpicker

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import asm.asmtunis.com.androidcolorpicker.data.getColorsFromPosition


/**
 * A simple [Fragment] subclass.
 */
class ColorsFragment(position: Int) : Fragment() {
     var position : Int?= position
     lateinit var color1 : ImageView
     lateinit var color2 : ImageView
     lateinit var color3 : ImageView
     lateinit var color4 : ImageView
     lateinit var color5 : ImageView
     lateinit var color6 : ImageView
     lateinit var color7 : ImageView
     lateinit var color8 : ImageView
     lateinit var color9 : ImageView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_colors, container, false)

        Log.d("message","here we are")

        initialiseColorHolders(view)
        var fragmentColorsList= getColorsFromPosition(position!!)

        ChangeColor(fragmentColorsList)

        return view
    }

    private fun ChangeColor(fragmentColorsList: ArrayList<Int>) {
        val colorDrawable: GradientDrawable = creatingTheRoundShape()
        changingTheShapeColor(colorDrawable,fragmentColorsList.get(0),color1)
        if (fragmentColorsList.size>1)
        changingTheShapeColor(colorDrawable, fragmentColorsList.get(1), color2)
        if (fragmentColorsList.size>2)
        changingTheShapeColor(colorDrawable, fragmentColorsList.get(2), color3)
        if (fragmentColorsList.size>3)
        changingTheShapeColor(colorDrawable, fragmentColorsList.get(3), color4)
        if (fragmentColorsList.size>4)
        changingTheShapeColor(colorDrawable, fragmentColorsList.get(4), color5)
        if (fragmentColorsList.size>5)
        changingTheShapeColor(colorDrawable, fragmentColorsList.get(5), color6)
        if (fragmentColorsList.size>6)
        changingTheShapeColor(colorDrawable, fragmentColorsList.get(6), color7)
        if (fragmentColorsList.size>7)
        changingTheShapeColor(colorDrawable, fragmentColorsList.get(7), color8)
        if (fragmentColorsList.size>8)
        changingTheShapeColor(colorDrawable, fragmentColorsList.get(8), color9)


    }

    private fun changingTheShapeColor(
        colorDrawable: GradientDrawable,
        color: Int,
        holder: ImageView
    ) {
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
        color1=view.findViewById(R.id.color1)
        color2=view.findViewById(R.id.color2)
        color3=view.findViewById(R.id.color3)
        color4=view.findViewById(R.id.color4)
        color5=view.findViewById(R.id.color5)
        color6=view.findViewById(R.id.color6)
        color7=view.findViewById(R.id.color7)
        color8=view.findViewById(R.id.color8)
        color9=view.findViewById(R.id.color9)
    }


}
