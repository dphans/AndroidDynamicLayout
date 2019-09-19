package com.dphans.lib.dynamiclayout.enums

import android.view.Gravity
import com.dphans.lib.dynamiclayout.models.ADLAttribute
import com.dphans.lib.dynamiclayout.models.actions.getType
import java.util.*

/**
 * Created by dphans in 2019
 *
 * Email: dinophan94@gmail.com
 * Github: @dphans
 */


enum class ADLLayoutGravityTypes(val layoutFlags: List<String>, val classFlag: Int) {
    None(layoutFlags = listOf(), classFlag = Gravity.NO_GRAVITY),
    Start(layoutFlags = listOf("start", "left"), classFlag = Gravity.START),
    End(layoutFlags = listOf("end", "right"), classFlag = Gravity.END),
    Top(layoutFlags = listOf("top"), classFlag = Gravity.TOP),
    Bottom(layoutFlags = listOf("bottom"), classFlag = Gravity.BOTTOM),
    Center(layoutFlags = listOf("center"), classFlag = Gravity.CENTER),
    CenterVertical(layoutFlags = listOf("center_vertical"), classFlag = Gravity.CENTER_VERTICAL),
    CenterHorizontal(
        layoutFlags = listOf("center_horizontal"),
        classFlag = Gravity.CENTER_HORIZONTAL
    );


    companion object {

        fun getLayoutGravityFlags(value: ADLAttribute): Int {
            return when (value.getType()) {
                ADLAttributeValueTypes.String -> {
                    values()
                        .firstOrNull { it.layoutFlags.contains(value.value) }
                        ?.classFlag
                        ?: None.classFlag
                }
                ADLAttributeValueTypes.Flags -> {
                    val flags = value.value?.split('|') ?: emptyList()
                    var calculatedValue: Int? = null

                    flags.forEach { layoutGravityFlag ->
                        val gravity = values()
                            .firstOrNull {
                                it.layoutFlags.contains(
                                    layoutGravityFlag.toLowerCase(
                                        Locale.US
                                    )
                                )
                            }
                        if (gravity != null) {
                            calculatedValue = if (calculatedValue == null) {
                                gravity.classFlag
                            } else {
                                calculatedValue!! or gravity.classFlag
                            }
                        }
                    }
                    calculatedValue ?: None.classFlag
                }
                else -> None.classFlag
            }
        }

    }

}
