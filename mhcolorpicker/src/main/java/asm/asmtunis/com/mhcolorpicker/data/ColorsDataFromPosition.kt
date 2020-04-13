package asm.asmtunis.com.mhcolorpicker.data

fun getColorsFromPosition(position: Int):ArrayList<Int>{
    var colorsList= ColorsObject.arrayList
    var newList=colorsList.chunked(9)
    return newList.get(position) as ArrayList<Int>
}