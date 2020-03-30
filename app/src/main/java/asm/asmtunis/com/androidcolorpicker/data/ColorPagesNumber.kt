package asm.asmtunis.com.androidcolorpicker.data

fun getColorsPagesNumber(): Int{
    var colorsList=getColors().chunked(9)
    return colorsList.size
}