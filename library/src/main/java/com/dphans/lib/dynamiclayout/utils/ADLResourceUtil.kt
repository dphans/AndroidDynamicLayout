package com.dphans.lib.dynamiclayout.utils

import android.content.Context

/**
 * Created by dphans in 2019
 *
 * Email: dinophan94@gmail.com
 * Github: @dphans
 */


object ADLResourceUtil {

    fun getIdIdByName(context: Context, name: String): Int? {
        return getResourceId(
            context = context,
            name = name,
            type = "id"
        )
    }

    fun getDrawableIdByName(context: Context, name: String): Int? {
        return getResourceId(
            context = context,
            name = name,
            type = "drawable"
        )
    }

    fun getColorIdByName(context: Context, name: String): Int? {
        return getResourceId(
            context = context,
            name = name,
            type = "color"
        )
    }

    fun getStringIdByName(context: Context, name: String): Int? {
        return getResourceId(
            context = context,
            name = name,
            type = "string"
        )
    }

    fun getFontIdByName(context: Context, name: String): Int? {
        return getResourceId(
            context = context,
            name = name,
            type = "font"
        )
    }

    fun getDimenIdByName(context: Context, name: String): Int? {
        return getResourceId(
            context = context,
            name = name,
            type = "dimen"
        )
    }

    fun getStyleIdByName(context: Context, name: String): Int? {
        return getResourceId(
            context = context,
            name = name,
            type = "font"
        )
    }

    fun getAttrIdByName(context: Context, name: String): Int? {
        return getResourceId(
            context = context,
            name = name,
            type = "attr"
        )
    }

    fun getPixelFromDimenIdByName(context: Context, name: String): Int? {
        val resourceId = this@ADLResourceUtil.getDimenIdByName(
            context = context,
            name = name
        ) ?: return null
        return ADLUtils.passException {
            return@passException context.resources
                .getDimensionPixelOffset(resourceId)
        }
    }

    private fun getResourceId(context: Context, name: String, type: String): Int? {
        return ADLUtils.passException {
            val resourceId = context.resources
                .getIdentifier(name, type, context.applicationContext.packageName)
            if (resourceId == 0) {
                return@passException null
            }
            return@passException resourceId
        }
    }

}
