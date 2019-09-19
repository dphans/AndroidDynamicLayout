package com.dphans.lib.dynamiclayout.adapters

import android.content.Context
import android.widget.LinearLayout
import com.dphans.lib.dynamiclayout.behaviors.ADLAttributeAdapter
import com.dphans.lib.dynamiclayout.models.ADLAttribute
import com.dphans.lib.dynamiclayout.models.ADLWidget

/**
 * Created by dphans in 2019
 *
 * Email: dinophan94@gmail.com
 * Github: @dphans
 */


class ADLLinearLayoutAttributeAdapter : ADLAttributeAdapter<LinearLayout> {

    override fun getViewClass(): Class<LinearLayout> {
        return LinearLayout::class.java
    }

    override fun getViewAttributeKeys(): List<String> {
        return listOf(
            "orientation"
        )
    }

    override fun onUpdateAttributeFromString(
        context: Context,
        view: LinearLayout,
        attribute: ADLAttribute,
        key: String,
        parent: ADLWidget?,
        valueString: String
    ) {
        when (key) {
            "orientation" -> {
                view.orientation = if (valueString == "vertical")
                    LinearLayout.VERTICAL
                else
                    LinearLayout.HORIZONTAL
            }
        }
    }
}