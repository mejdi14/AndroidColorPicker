package asm.asmtunis.com.mhcolorpicker.data

import asm.asmtunis.com.mhcolorpicker.helpers.ColorsPosition


fun addUserNewColors(userNewColors: ArrayList<Int>, colorsPosition: ColorsPosition){
    when(colorsPosition){
        ColorsPosition.START->ColorsObject.arrayList.addAll(0,userNewColors)
        ColorsPosition.END->ColorsObject.arrayList.addAll(userNewColors)
    }
}