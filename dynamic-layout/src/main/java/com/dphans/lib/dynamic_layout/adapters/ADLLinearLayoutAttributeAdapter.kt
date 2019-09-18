package com.dphans.lib.dynamic_layout.adapters

import android.content.Context
import android.widget.LinearLayout
import com.dphans.lib.dynamic_layout.behaviors.ADLAttributeAdapter
import com.dphans.lib.dynamic_layout.models.ADLAttribute
import com.dphans.lib.dynamic_layout.models.ADLWidget

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

    override fun onUpdateAttributeFromResource(
        context: Context,
        view: LinearLayout,
        attribute: ADLAttribute,
        key: String,
        resourceName: String,
        parent: ADLWidget?
    ) {

    }

    override fun onUpdateAttributeFromAttribute(
        context: Context,
        view: LinearLayout,
        attribute: ADLAttribute,
        key: String,
        parent: ADLWidget?
    ) {

    }

    override fun onUpdateAttributeFromDimension(
        context: Context,
        view: LinearLayout,
        attribute: ADLAttribute,
        key: String,
        parent: ADLWidget?,
        convertedPixelValue: Int
    ) {

    }

    override fun onUpdateAttributeFromFlags(
        context: Context,
        view: LinearLayout,
        attribute: ADLAttribute,
        key: String,
        parent: ADLWidget?,
        flags: List<String>
    ) {

    }

    override fun onUpdateAttributeFromInteger(
        context: Context,
        view: LinearLayout,
        attribute: ADLAttribute,
        key: String,
        parent: ADLWidget?,
        valueInteger: Int
    ) {

    }

    override fun onUpdateAttributeFromFloat(
        context: Context,
        view: LinearLayout,
        attribute: ADLAttribute,
        key: String,
        parent: ADLWidget?,
        valueFloat: Float
    ) {

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

    override fun onUpdateAttributeFromNull(
        context: Context,
        view: LinearLayout,
        attribute: ADLAttribute,
        key: String,
        parent: ADLWidget?
    ) {

    }
}