package asm.asmtunis.com.androidcolorpicker.data

fun getColorsFromPosition(position: Int):ArrayList<Int>{
    var colorsList= getColors()
    var newList=colorsList.chunked(9)
    return newList.get(position) as ArrayList<Int>
}