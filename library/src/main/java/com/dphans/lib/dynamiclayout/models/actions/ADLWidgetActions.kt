package com.dphans.lib.dynamiclayout.models.actions

import android.content.Context
import android.view.View
import com.dphans.lib.dynamiclayout.models.ADLWidget

/**
 * Created by dphans in 2019
 *
 * Email: dinophan94@gmail.com
 * Github: @dphans
 */


fun ADLWidget.createView(
    context: Context
): View {
    val viewClassName = this@createView.view_class
        ?: throw IllegalStateException("Widget is missing view_class!")
    if (viewClassName.isEmpty()) {
        throw IllegalStateException("Widget is missing view_class!")
    }

    val viewClazz = try {
        Class.forName(viewClassName)
    } catch (exception: ClassNotFoundException) {
        throw ClassNotFoundException("Widget with view class $viewClassName is not exist!")
    }

    if (!View::class.java.isAssignableFrom(viewClazz)) {
        throw Exception("Class $viewClassName is not a widget!")
    }

    return viewClazz.getDeclaredConstructor(Context::class.java)
        .newInstance(context) as View
}

fun ADLWidget.getWidgetClass(): Class<View>? {
    this@getWidgetClass.view_class?.let { viewClass ->
        if (viewClass.isEmpty()) {
            return null
        }

        try {
            val clazz = Class.forName(viewClass)
            @Suppress("UNCHECKED_CAST")
            if (View::class.java.isAssignableFrom(clazz)) {
                return clazz as? Class<View>
            }
            return null
        } catch (exception: ClassNotFoundException) {
            return null
        }
    }

    return null
}
