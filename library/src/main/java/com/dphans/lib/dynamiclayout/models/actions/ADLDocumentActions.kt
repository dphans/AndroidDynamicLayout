package com.dphans.lib.dynamiclayout.models.actions

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.dphans.lib.dynamiclayout.ADL
import com.dphans.lib.dynamiclayout.R
import com.dphans.lib.dynamiclayout.models.ADLDocument
import com.dphans.lib.dynamiclayout.models.ADLWidget
import com.dphans.lib.dynamiclayout.utils.ADLAttributeUtil

/**
 * Created by dphans in 2019
 *
 * Email: dinophan94@gmail.com
 * Github: @dphans
 */


@Suppress("UNUSED_PARAMETER")
fun ADLDocument.getView(context: Context, config: ADL.Builder): View {
    return this@getView.view?.let { rootView ->
        return@let createViewRecursively(
            context = context,
            config = config,
            widget = rootView,
            parent = null
        )
    }
        ?: throw IllegalStateException(
            "Root view not exist (Error: view is null in ADLDocument)!"
        )
}

private fun ADLDocument.createViewRecursively(
    context: Context,
    config: ADL.Builder,
    widget: ADLWidget,
    parent: ADLWidget?
): View {
    val viewInstance = widget.createView(
        context = context
    )
    ADLAttributeUtil.applyAttributeForView(
        view = viewInstance,
        context = context,
        config = config,
        widget = widget,
        parent = parent
    )

    if (viewInstance is ViewGroup) {
        widget.childrens.forEach { childWidget ->
            viewInstance.addView(
                this@createViewRecursively.createViewRecursively(
                    context = context,
                    config = config,
                    widget = childWidget,
                    parent = widget
                )
            )
        }
    }

    // map view id, tag for find views purpose
    viewInstance.setTag(R.string.adl_document_uid_tag_key, widget._uid)
    viewInstance.setTag(R.string.adl_document_view_id_tag_key, viewInstance.id)

    return viewInstance
}
