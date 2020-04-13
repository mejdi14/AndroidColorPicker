package asm.asmtunis.com.androidcolorpicker.listener


interface ColorListener {
    /**
     *
     * Call when user select color
     *
     * @param color Color Resource
     * @param colorHex Hex String of Color Resource
     */
    fun onColorSelected(color: Int, colorHex: String)
}
