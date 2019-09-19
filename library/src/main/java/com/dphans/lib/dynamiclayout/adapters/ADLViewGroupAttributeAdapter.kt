package com.dphans.lib.dynamiclayout.adapters

import android.content.Context
import android.view.ViewGroup
import com.dphans.lib.dynamiclayout.behaviors.ADLAttributeAdapter
import com.dphans.lib.dynamiclayout.models.ADLAttribute
import com.dphans.lib.dynamiclayout.models.ADLWidget

/**
 * Created by dphans in 2019
 *
 * Email: dinophan94@gmail.com
 * Github: @dphans
 */


class ADLViewGroupAttributeAdapter : ADLAttributeAdapter<ViewGroup> {

    override fun getViewClass(): Class<ViewGroup> {
        return ViewGroup::class.java
    }

    override fun getViewAttributeKeys(): List<String> {
        return listOf(
            "clipChildren",
            "clipToPadding"
        )
    }

    override fun onUpdateAttributeFromString(
        context: Context,
        view: ViewGroup,
        attribute: ADLAttribute,
        key: String,
        parent: ADLWidget?,
        valueString: String
    ) {
        when (key) {
            "clipChildren" -> {
                view.clipChildren = valueString == "true"
            }
            "clipToPadding" -> {
                view.clipToPadding = valueString == "true"
            }
        }
    }

}
