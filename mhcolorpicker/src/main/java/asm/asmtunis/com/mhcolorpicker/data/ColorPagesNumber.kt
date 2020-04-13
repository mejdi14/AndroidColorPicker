package asm.asmtunis.com.mhcolorpicker.data

fun getColorsPagesNumber(): Int{
    var colorsList=ColorsObject.arrayList.chunked(9)
    return colorsList.size
}