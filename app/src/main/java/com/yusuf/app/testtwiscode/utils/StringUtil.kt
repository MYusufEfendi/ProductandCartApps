package com.yusuf.app.testtwiscode.utils

import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*
import javax.inject.Inject

class StringUtil @Inject constructor() {

    fun emptyStringToZero(st:String):Int{
        return if(st=="") 0 else st.toInt()
    }
    fun emptyStringToDouble(st:String):Double{
        return if(st=="") 0.0 else st.toDouble()
    }

    private val numberFormatInt by lazy {
        NumberFormat.getCurrencyInstance(Locale("id")).apply {
            maximumFractionDigits = 0
            (this as? DecimalFormat)?.decimalFormatSymbols =
                (this as? DecimalFormat)?.decimalFormatSymbols?.apply { currencySymbol = "" }
        }
    }

    fun formatCurrency(input: Int): String {
        return numberFormatInt.format(input)
    }
}