package asm.asmtunis.com.androidcolorpicker.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ColorViewModel : ViewModel() {

    val currentColor: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
}