package com.dphans.lib.dynamiclayout.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.dphans.lib.dynamiclayout.behaviors.ADLAttributeAdapter
import com.dphans.lib.dynamiclayout.enums.ADLLayoutGravityTypes
import com.dphans.lib.dynamiclayout.enums.ADLLayoutParamsTypes
import com.dphans.lib.dynamiclayout.enums.ADLLayoutSizeTypes
import com.dphans.lib.dynamiclayout.models.ADLAttribute
import com.dphans.lib.dynamiclayout.models.ADLWidget
import com.dphans.lib.dynamiclayout.models.actions.getWidgetClass
import com.dphans.lib.dynamiclayout.utils.ADLResourceUtil
import com.dphans.lib.dynamiclayout.utils.ADLUtils

/**
 * Created by dphans in 2019
 *
 * Email: dinophan94@gmail.com
 * Github: @dphans
 */


class ADLViewAttrbiteAdapter : ADLAttributeAdapter<View> {

    override fun getViewClass(): Class<View> {
        return View::class.java
    }

    override fun onUpdateAttributeFromResource(
        context: Context,
        view: View,
        attribute: ADLAttribute,
        key: String,
        resourceName: String,
        parent: ADLWidget?
    ) {
        when (key) {
            "id" -> {
                ADLResourceUtil.getIdIdByName(
                    context = context,
                    name = resourceName
                )?.let { view.id = it }
            }
            "layout_margin" -> {
                this@ADLViewAttrbiteAdapter.updateViewMarginResource(
                    view = view,
                    left = resourceName,
                    top = resourceName,
                    right = resourceName,
                    bottom = resourceName,
                    start = resourceName,
                    end = resourceName
                )
            }
            "layout_marginStart" -> {
                this@ADLViewAttrbiteAdapter.updateViewMarginResource(
                    view = view,
                    start = resourceName
                )
            }
            "layout_marginEnd" -> {
                this@ADLViewAttrbiteAdapter.updateViewMarginResource(
                    view = view,
                    end = resourceName
                )
            }
            "layout_marginTop" -> {
                this@ADLViewAttrbiteAdapter.updateViewMarginResource(
                    view = view,
                    top = resourceName
                )
            }
            "layout_marginBottom" -> {
                this@ADLViewAttrbiteAdapter.updateViewMarginResource(
                    view = view,
                    bottom = resourceName
                )
            }
            "layout_marginLeft" -> {
                this@ADLViewAttrbiteAdapter.updateViewMarginResource(
                    view = view,
                    left = resourceName
                )
            }
            "layout_marginRight" -> {
                this@ADLViewAttrbiteAdapter.updateViewMarginResource(
                    view = view,
                    right = resourceName
                )
            }
            "padding" -> {
                this@ADLViewAttrbiteAdapter.updateViewPaddingsResource(
                    view = view,
                    left = resourceName,
                    top = resourceName,
                    right = resourceName,
                    bottom = resourceName
                )
            }
            "paddingStart" -> {
                this@ADLViewAttrbiteAdapter.updateViewPaddingsResource(
                    view = view,
                    left = resourceName
                )
            }
            "paddingEnd" -> {
                this@ADLViewAttrbiteAdapter.updateViewPaddingsResource(
                    view = view,
                    right = resourceName
                )
            }
            "paddingTop" -> {
                this@ADLViewAttrbiteAdapter.updateViewPaddingsResource(
                    view = view,
                    top = resourceName
                )
            }
            "paddingBottom" -> {
                this@ADLViewAttrbiteAdapter.updateViewPaddingsResource(
                    view = view,
                    bottom = resourceName
                )
            }
            "paddingLeft" -> {
                this@ADLViewAttrbiteAdapter.updateViewPaddingsResource(
                    view = view,
                    left = resourceName
                )
            }
            "paddingRight" -> {
                this@ADLViewAttrbiteAdapter.updateViewPaddingsResource(
                    view = view,
                    right = resourceName
                )
            }
        }
    }

    override fun onUpdateAttributeFromAttribute(
        context: Context,
        view: View,
        attribute: ADLAttribute,
        key: String,
        parent: ADLWidget?
    ) {

    }

    override fun onUpdateAttributeFromDimension(
        context: Context,
        view: View,
        attribute: ADLAttribute,
        key: String,
        parent: ADLWidget?,
        convertedPixelValue: Int
    ) {
        when (key) {
            "layout_width" -> {
                view.layoutParams = this@ADLViewAttrbiteAdapter.getLayoutParams(
                    view = view,
                    parent = parent,
                    width = convertedPixelValue,
                    height = null
                )
            }
            "layout_height" -> {
                view.layoutParams = this@ADLViewAttrbiteAdapter.getLayoutParams(
                    view = view,
                    parent = parent,
                    width = null,
                    height = convertedPixelValue
                )
            }
            "layout_margin" -> {
                this@ADLViewAttrbiteAdapter.updateViewMarginPixel(
                    view = view,
                    left = convertedPixelValue,
                    top = convertedPixelValue,
                    right = convertedPixelValue,
                    bottom = convertedPixelValue,
                    start = convertedPixelValue,
                    end = convertedPixelValue
                )
            }
            "layout_marginStart" -> {
                this@ADLViewAttrbiteAdapter.updateViewMarginPixel(
                    view = view,
                    start = convertedPixelValue
                )
            }
            "layout_marginEnd" -> {
                this@ADLViewAttrbiteAdapter.updateViewMarginPixel(
                    view = view,
                    end = convertedPixelValue
                )
            }
            "layout_marginTop" -> {
                this@ADLViewAttrbiteAdapter.updateViewMarginPixel(
                    view = view,
                    top = convertedPixelValue
                )
            }
            "layout_marginBottom" -> {
                this@ADLViewAttrbiteAdapter.updateViewMarginPixel(
                    view = view,
                    bottom = convertedPixelValue
                )
            }
            "layout_marginLeft" -> {
                this@ADLViewAttrbiteAdapter.updateViewMarginPixel(
                    view = view,
                    left = convertedPixelValue
                )
            }
            "layout_marginRight" -> {
                this@ADLViewAttrbiteAdapter.updateViewMarginPixel(
                    view = view,
                    right = convertedPixelValue
                )
            }
            "padding" -> {
                view.updatePadding(
                    left = convertedPixelValue,
                    top = convertedPixelValue,
                    right = convertedPixelValue,
                    bottom = convertedPixelValue
                )
            }
            "paddingStart" -> {
                view.updatePadding(
                    left = convertedPixelValue
                )
            }
            "paddingEnd" -> {
                view.updatePadding(
                    right = convertedPixelValue
                )
            }
            "paddingTop" -> {
                view.updatePadding(
                    top = convertedPixelValue
                )
            }
            "paddingBottom" -> {
                view.updatePadding(
                    bottom = convertedPixelValue
                )
            }
            "paddingLeft" -> {
                view.updatePadding(
                    left = convertedPixelValue
                )
            }
            "paddingRight" -> {
                view.updatePadding(
                    right = convertedPixelValue
                )
            }
        }
    }

    override fun onUpdateAttributeFromFlags(
        context: Context,
        view: View,
        attribute: ADLAttribute,
        key: String,
        parent: ADLWidget?,
        flags: List<String>
    ) {
        when (key) {
            "layout_gravity" -> {
                view.layoutParams = this@ADLViewAttrbiteAdapter
                    .getLayoutParams(
                        view = view,
                        parent = parent,
                        gravity = ADLLayoutGravityTypes.getLayoutGravityFlags(
                            value = attribute
                        )
                    )
            }
        }
    }

    override fun onUpdateAttributeFromInteger(
        context: Context,
        view: View,
        attribute: ADLAttribute,
        key: String,
        parent: ADLWidget?,
        valueInteger: Int
    ) {

    }

    override fun onUpdateAttributeFromFloat(
        context: Context,
        view: View,
        attribute: ADLAttribute,
        key: String,
        parent: ADLWidget?,
        valueFloat: Float
    ) {

    }

    override fun onUpdateAttributeFromString(
        context: Context,
        view: View,
        attribute: ADLAttribute,
        key: String,
        parent: ADLWidget?,
        valueString: String
    ) {
        when (attribute.key) {
            "layout_width" -> {
                val widthFlag = ADLLayoutSizeTypes
                    .getSizeFlagWithValue(value = attribute.value)
                view.layoutParams = this@ADLViewAttrbiteAdapter.getLayoutParams(
                    view = view,
                    parent = parent,
                    width = widthFlag,
                    height = null
                )
            }
            "layout_height" -> {
                val height = ADLLayoutSizeTypes
                    .getSizeFlagWithValue(value = attribute.value)
                view.layoutParams = this@ADLViewAttrbiteAdapter.getLayoutParams(
                    view = view,
                    parent = parent,
                    width = null,
                    height = height
                )
            }
            "layout_gravity" -> {
                view.layoutParams = this@ADLViewAttrbiteAdapter
                    .getLayoutParams(
                        view = view,
                        parent = parent,
                        gravity = ADLLayoutGravityTypes.getLayoutGravityFlags(
                            value = attribute
                        )
                    )
            }
        }
    }

    override fun onUpdateAttributeFromNull(
        context: Context,
        view: View,
        attribute: ADLAttribute,
        key: String,
        parent: ADLWidget?
    ) {

    }

    override fun getViewAttributeKeys(): List<String> {
        return listOf(
            "id",
            "layout_width",
            "layout_height",
            "layout_gravity",
            "layout_marginTop",
            "layout_marginBottom",
            "layout_marginStart",
            "layout_marginEnd",
            "layout_marginHorizontal",
            "layout_marginVertical",
            "layout_margin",
            "layout_marginLeft",
            "layout_marginRight",
            "paddingTop",
            "paddingBottom",
            "paddingStart",
            "paddingEnd",
            "padding",
            "paddingLeft",
            "paddingRight"
        )
    }

    private fun getLayoutParams(
        view: View,
        parent: ADLWidget?,
        gravity: Int? = null,
        width: Int? = null,
        height: Int? = null
    ): ViewGroup.LayoutParams {
        val parentLayoutParams = parent?.getWidgetClass()
            ?.let { parentClass ->
                return@let ADLLayoutParamsTypes.getLayout(parentClass)
            }
            ?: WindowManager.LayoutParams::class.java
        val params = view.layoutParams ?: parentLayoutParams
            .getDeclaredConstructor(Int::class.java, Int::class.java)
            .newInstance(
                width ?: view.layoutParams?.width ?: WindowManager.LayoutParams.WRAP_CONTENT,
                height ?: view.layoutParams?.height ?: WindowManager.LayoutParams.WRAP_CONTENT
            )
        params.width = width ?: view.layoutParams?.width
                ?: WindowManager.LayoutParams.WRAP_CONTENT
        params.height = height ?: view.layoutParams?.height
                ?: WindowManager.LayoutParams.WRAP_CONTENT
        gravity?.let { gravityFlags ->
            ADLUtils.passException {
                params::class.java.getDeclaredField("gravity")
                    .set(params, gravityFlags)
            }
        }
        return params
    }

    private fun updateViewMarginPixel(
        view: View,
        top: Int? = null,
        right: Int? = null,
        bottom: Int? = null,
        left: Int? = null,
        start: Int? = null,
        end: Int? = null
    ) {
        val layoutParams = ((view.layoutParams ?: ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )) as? ViewGroup.MarginLayoutParams)
            ?: return
        layoutParams.setMargins(
            left ?: layoutParams.leftMargin,
            top ?: layoutParams.topMargin,
            right ?: layoutParams.rightMargin,
            bottom ?: layoutParams.bottomMargin
        )
        layoutParams.marginStart = start ?: layoutParams.marginStart
        layoutParams.marginEnd = end ?: layoutParams.marginEnd
        view.layoutParams = layoutParams
    }

    private fun updateViewMarginResource(
        view: View,
        top: String? = null,
        right: String? = null,
        bottom: String? = null,
        left: String? = null,
        start: String? = null,
        end: String? = null
    ) {
        val marginTop = top?.let {
            return@let ADLResourceUtil.getPixelFromDimenIdByName(
                name = it,
                context = view.context
            )
        }
        val marginRight = right?.let {
            return@let ADLResourceUtil.getPixelFromDimenIdByName(
                name = it,
                context = view.context
            )
        }
        val marginBottom = bottom?.let {
            return@let ADLResourceUtil.getPixelFromDimenIdByName(
                name = it,
                context = view.context
            )
        }
        val marginLeft = left?.let {
            return@let ADLResourceUtil.getPixelFromDimenIdByName(
                name = it,
                context = view.context
            )
        }
        val marginStart = start?.let {
            return@let ADLResourceUtil.getPixelFromDimenIdByName(
                name = it,
                context = view.context
            )
        }
        val marginEnd = end?.let {
            return@let ADLResourceUtil.getPixelFromDimenIdByName(
                name = it,
                context = view.context
            )
        }
        this@ADLViewAttrbiteAdapter.updateViewMarginPixel(
            view = view,
            top = marginTop,
            right = marginRight,
            bottom = marginBottom,
            left = marginLeft,
            start = marginStart,
            end = marginEnd
        )
    }

    private fun View.updatePadding(
        left: Int = this@updatePadding.paddingLeft,
        top: Int = this@updatePadding.paddingTop,
        right: Int = this@updatePadding.paddingRight,
        bottom: Int = this@updatePadding.paddingBottom
    ) {
        this@updatePadding.setPadding(
            left,
            top,
            right,
            bottom
        )
    }

    private fun updateViewPaddingsResource(
        view: View,
        top: String? = null,
        right: String? = null,
        bottom: String? = null,
        left: String? = null
    ) {
        val paddingTop = top?.let {
            return@let ADLResourceUtil.getPixelFromDimenIdByName(
                name = it,
                context = view.context
            )
        }
        val paddingRight = right?.let {
            return@let ADLResourceUtil.getPixelFromDimenIdByName(
                name = it,
                context = view.context
            )
        }
        val paddingBottom = bottom?.let {
            return@let ADLResourceUtil.getPixelFromDimenIdByName(
                name = it,
                context = view.context
            )
        }
        val paddingLeft = left?.let {
            return@let ADLResourceUtil.getPixelFromDimenIdByName(
                name = it,
                context = view.context
            )
        }
        view.updatePadding(
            top = paddingTop ?: view.paddingTop,
            right = paddingRight ?: view.paddingRight,
            bottom = paddingBottom ?: view.paddingBottom,
            left = paddingLeft ?: view.paddingLeft
        )
    }

}
