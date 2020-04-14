package asm.asmtunis.com.androidcolorpicker

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import asm.asmtunis.com.mhcolorpicker.dialog.MHColorsDialog
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        context = this

        button.setOnClickListener {

         /*   var arrayList = ArrayList<Int>()
            arrayList.add(Color.parseColor("#8bc34a"))
            arrayList.add(Color.parseColor("#8bc34a"))
            arrayList.add(Color.parseColor("#8bc34a"))*/
            MHColorsDialog(this)
                .setColorListener { color, colorHex ->
                    Log.d("hex", colorHex)
                    Log.d("int", color.toString())
                }
                .show()
        }

    }

}