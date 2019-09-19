package com.dphans.lib.dynamiclayout.enums

import android.view.View
import android.view.ViewGroup
import android.widget.*

/**
 * Created by dphans in 2019
 *
 * Email: dinophan94@gmail.com
 * Github: @dphans
 */


enum class ADLLayoutParamsTypes(val layoutParamsClass: Class<out ViewGroup.LayoutParams>) {
    Linear(layoutParamsClass = LinearLayout.LayoutParams::class.java),
    Relative(layoutParamsClass = RelativeLayout.LayoutParams::class.java),
    Table(layoutParamsClass = TableLayout.LayoutParams::class.java),
    Grid(layoutParamsClass = GridLayout.LayoutParams::class.java),
    Frame(layoutParamsClass = FrameLayout.LayoutParams::class.java),
    Other(layoutParamsClass = ViewGroup.LayoutParams::class.java);


    companion object {

        private fun getType(clazz: Class<View>?): ADLLayoutParamsTypes {
            if (clazz == null) {
                return Other
            }

            return when {
                LinearLayout::class.java.isAssignableFrom(clazz) -> Linear
                RelativeLayout::class.java.isAssignableFrom(clazz) -> Relative
                TableLayout::class.java.isAssignableFrom(clazz) -> Table
                GridLayout::class.java.isAssignableFrom(clazz) -> Grid
                FrameLayout::class.java.isAssignableFrom(clazz) -> Frame
                else -> Other
            }
        }

        fun getLayout(clazz: Class<View>): Class<out ViewGroup.LayoutParams> {
            return getType(clazz = clazz).layoutParamsClass
        }

    }

}
