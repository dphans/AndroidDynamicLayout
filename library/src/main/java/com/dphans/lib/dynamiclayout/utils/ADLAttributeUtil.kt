package com.dphans.lib.dynamiclayout.utils

import android.content.Context
import android.view.View
import com.dphans.lib.dynamiclayout.ADL
import com.dphans.lib.dynamiclayout.behaviors.ADLAttributeAdapter
import com.dphans.lib.dynamiclayout.models.ADLWidget

/**
 * Created by dphans in 2019
 *
 * Email: dinophan94@gmail.com
 * Github: @dphans
 */


class ADLAttributeUtil {

    companion object {

        fun applyAttributeForView(
            view: View,
            context: Context,
            config: ADL.Builder,
            widget: ADLWidget,
            parent: ADLWidget?
        ) {
            config.mAdapters.forEach { attributeAdapter ->
                if (attributeAdapter.getViewClass().isInstance(view)) {
                    @Suppress("UNCHECKED_CAST")
                    (attributeAdapter as? ADLAttributeAdapter<View>)
                        ?.applyAttribute(
                            view = view,
                            context = context,
                            config = config,
                            widget = widget,
                            parent = parent
                        )
                }
            }
        }

    }

}
