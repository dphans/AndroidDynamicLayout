package com.dphans.lib.dynamiclayout.adapters

import android.content.Context
import android.widget.TextView
import com.dphans.lib.dynamiclayout.behaviors.ADLAttributeAdapter
import com.dphans.lib.dynamiclayout.models.ADLAttribute
import com.dphans.lib.dynamiclayout.models.ADLWidget
import com.dphans.lib.dynamiclayout.utils.ADLResourceUtil

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

}
