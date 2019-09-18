package com.dphans.lib.dynamic_layout.enums

import android.view.WindowManager
import java.util.*

/**
 * Created by dphans in 2019
 *
 * Email: dinophan94@gmail.com
 * Github: @dphans
 */


enum class ADLLayoutSizeTypes(val flag: Int) {
    WRAP_CONTENT(WindowManager.LayoutParams.WRAP_CONTENT),
    MATCH_PARENT(WindowManager.LayoutParams.MATCH_PARENT);


    companion object {

        fun getSizeFlagWithValue(value: String?): Int {
            values().firstOrNull { valueEnum ->
                valueEnum.name == value?.toUpperCase(Locale.US)
            }?.let { valueEnum ->
                return valueEnum.flag
            }
            throw IllegalStateException(
                "Value '$value' is not match any size constrains, " +
                        "please provides some value like " +
                        values().joinToString(",") { "'${it.name.toLowerCase(Locale.US)}'" }
            )
        }

    }
}
