package com.dphans.lib.dynamic_layout.adapters

import android.content.Context
import android.widget.TextView
import com.dphans.lib.dynamic_layout.behaviors.ADLAttributeAdapter
import com.dphans.lib.dynamic_layout.models.ADLAttribute
import com.dphans.lib.dynamic_layout.models.ADLWidget
import com.dphans.lib.dynamic_layout.utils.ADLResourceUtil

/**
 * Created by dphans in 2019
 *
 * Email: dinophan94@gmail.com
 * Github: @dphans
 */


class ADLTextViewAttributeAdapter : ADLAttributeAdapter<TextView> {

    override fun getViewClass(): Class<TextView> {
        return TextView::class.java
    }

    override fun getViewAttributeKeys(): List<String> {
        return listOf(
            "text"
        )
    }

    override fun onUpdateAttributeFromResource(
        context: Context,
        view: TextView,
        attribute: ADLAttribute,
        key: String,
        resourceName: String,
        parent: ADLWidget?
    ) {
        when (attribute.key) {
            "text" -> {
                ADLResourceUtil.getStringIdByName(
                    context = context,
                    name = resourceName
                )?.let { stringResId -> view.setText(stringResId) }
            }
        }
    }

    override fun onUpdateAttributeFromAttribute(
        context: Context,
        view: TextView,
        attribute: ADLAttribute,
        key: String,
        parent: ADLWidget?
    ) {

    }

    override fun onUpdateAttributeFromDimension(
        context: Context,
        view: TextView,
        attribute: ADLAttribute,
        key: String,
        parent: ADLWidget?,
        convertedPixelValue: Int
    ) {

    }

    override fun onUpdateAttributeFromFlags(
        context: Context,
        view: TextView,
        attribute: ADLAttribute,
        key: String,
        parent: ADLWidget?,
        flags: List<String>
    ) {

    }

    override fun onUpdateAttributeFromInteger(
        context: Context,
        view: TextView,
        attribute: ADLAttribute,
        key: String,
        parent: ADLWidget?,
        valueInteger: Int
    ) {

    }

    override fun onUpdateAttributeFromFloat(
        context: Context,
        view: TextView,
        attribute: ADLAttribute,
        key: String,
        parent: ADLWidget?,
        valueFloat: Float
    ) {

    }

    override fun onUpdateAttributeFromString(
        context: Context,
        view: TextView,
        attribute: ADLAttribute,
        key: String,
        parent: ADLWidget?,
        valueString: String
    ) {
        when (attribute.key) {
            "text" -> {
                view.text = valueString
            }
        }
    }

    override fun onUpdateAttributeFromNull(
        context: Context,
        view: TextView,
        attribute: ADLAttribute,
        key: String,
        parent: ADLWidget?
    ) {

    }
}
