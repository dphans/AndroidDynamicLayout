package com.dphans.lib.dynamiclayout.behaviors

import android.content.Context
import android.view.View
import com.dphans.lib.dynamiclayout.ADL
import com.dphans.lib.dynamiclayout.enums.ADLAttributeValueTypes
import com.dphans.lib.dynamiclayout.models.ADLAttribute
import com.dphans.lib.dynamiclayout.models.ADLWidget
import com.dphans.lib.dynamiclayout.models.actions.getType
import com.dphans.lib.dynamiclayout.utils.ADLDimentionUtil

/**
 * Created by dphans in 2019
 *
 * Email: dinophan94@gmail.com
 * Github: @dphans
 */


interface ADLAttributeAdapter<V : View> {

    fun getViewClass(): Class<V>

    fun getViewAttributeKeys(): List<String>

    fun onUpdateAttributeFromResource(
        context: Context,
        view: V,
        attribute: ADLAttribute,
        key: String,
        resourceName: String,
        parent: ADLWidget?
    ) = Unit

    fun onUpdateAttributeFromAttribute(
        context: Context,
        view: V,
        attribute: ADLAttribute,
        key: String,
        parent: ADLWidget?
    ) = Unit

    fun onUpdateAttributeFromDimension(
        context: Context,
        view: V,
        attribute: ADLAttribute,
        key: String,
        parent: ADLWidget?,
        convertedPixelValue: Int
    ) = Unit

    fun onUpdateAttributeFromFlags(
        context: Context,
        view: V,
        attribute: ADLAttribute,
        key: String,
        parent: ADLWidget?,
        flags: List<String>
    ) = Unit

    fun onUpdateAttributeFromInteger(
        context: Context,
        view: V,
        attribute: ADLAttribute,
        key: String,
        parent: ADLWidget?,
        valueInteger: Int
    ) = Unit

    fun onUpdateAttributeFromFloat(
        context: Context,
        view: V,
        attribute: ADLAttribute,
        key: String,
        parent: ADLWidget?,
        valueFloat: Float
    ) = Unit

    fun onUpdateAttributeFromString(
        context: Context,
        view: V,
        attribute: ADLAttribute,
        key: String,
        parent: ADLWidget?,
        valueString: String
    ) = Unit

    fun onUpdateAttributeFromNull(
        context: Context,
        view: V,
        attribute: ADLAttribute,
        key: String,
        parent: ADLWidget?
    ) = Unit

    fun applyAttribute(
        context: Context,
        view: V,
        widget: ADLWidget,
        parent: ADLWidget?,
        config: ADL.Builder
    ) {
        val viewAttributes = this@ADLAttributeAdapter
            .getViewAttributeKeys()

        val availableAttributes = widget.attributes
            .filter { attribute -> viewAttributes.contains(attribute.key) }

        availableAttributes.forEach { attribute ->
            when (attribute.getType()) {
                ADLAttributeValueTypes.Resource -> {
                    this@ADLAttributeAdapter.onUpdateAttributeFromResource(
                        context = context,
                        view = view,
                        attribute = attribute,
                        key = attribute.key ?: "",
                        resourceName = attribute.value ?: "",
                        parent = parent
                    )
                }
                ADLAttributeValueTypes.Attribute -> {
                    this@ADLAttributeAdapter.onUpdateAttributeFromAttribute(
                        context = context,
                        view = view,
                        attribute = attribute,
                        key = attribute.key ?: "",
                        parent = parent
                    )
                }
                ADLAttributeValueTypes.Dimension -> {
                    this@ADLAttributeAdapter.onUpdateAttributeFromDimension(
                        context = context,
                        view = view,
                        attribute = attribute,
                        key = attribute.key ?: "",
                        parent = parent,
                        convertedPixelValue = ADLDimentionUtil.stringToDimensionPixelSize(
                            attribute.value,
                            context.resources.displayMetrics
                        )
                    )
                }
                ADLAttributeValueTypes.Flags -> {
                    this@ADLAttributeAdapter.onUpdateAttributeFromFlags(
                        context = context,
                        view = view,
                        attribute = attribute,
                        key = attribute.key ?: "",
                        parent = parent,
                        flags = attribute.value?.split('|') ?: emptyList()
                    )
                }
                ADLAttributeValueTypes.Integer -> {
                    this@ADLAttributeAdapter.onUpdateAttributeFromInteger(
                        context = context,
                        view = view,
                        attribute = attribute,
                        key = attribute.key ?: "",
                        parent = parent,
                        valueInteger = attribute.value?.toIntOrNull() ?: 0
                    )
                }
                ADLAttributeValueTypes.Float -> {
                    this@ADLAttributeAdapter.onUpdateAttributeFromFloat(
                        context = context,
                        view = view,
                        attribute = attribute,
                        key = attribute.key ?: "",
                        parent = parent,
                        valueFloat = attribute.value?.toFloatOrNull() ?: 0.0f
                    )
                }
                ADLAttributeValueTypes.String -> {
                    this@ADLAttributeAdapter.onUpdateAttributeFromString(
                        context = context,
                        view = view,
                        attribute = attribute,
                        key = attribute.key ?: "",
                        parent = parent,
                        valueString = attribute.value ?: ""
                    )
                }
                ADLAttributeValueTypes.Null -> {
                    this@ADLAttributeAdapter.onUpdateAttributeFromNull(
                        context = context,
                        view = view,
                        parent = parent,
                        attribute = attribute,
                        key = attribute.key ?: ""
                    )
                }
            }
        }
    }

}
