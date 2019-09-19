package com.dphans.lib.dynamiclayout.enums

import com.dphans.lib.dynamiclayout.utils.ADLDimentionUtil

/**
 * Created by dphans in 2019
 *
 * Email: dinophan94@gmail.com
 * Github: @dphans
 */


enum class ADLAttributeValueTypes {

    /**
     * Value contains (begin with `@`):
     *
     * @string/stringResource
     * @drawable/drawableResource
     * @array/arrayResource
     * @color/colorResource
     * @font/fontResource
     * @{instanceReference}
     */
    Resource,

    /**
     * Value contains (begin with `?`):
     *
     * ?attr:attrReference
     * ?android:androidReference
     */
    Attribute,

    /**
     * Value contains (end with dp, px, mm, in, sp,...)
     */
    Dimension,

    /**
     * Value contains strings seperators by `|`
     */
    Flags,

    /**
     * Value parseable into integer
     */
    Integer,

    /**
     * Value parable into float
     */
    Float,

    /**
     * Another value types
     */
    String,

    /**
     * Value is null
     */
    Null;

    companion object {

        fun getTypeWithValue(value: kotlin.String?): ADLAttributeValueTypes {
            if (value == null) {
                return Null
            }

            if (value.isBlank()) {
                return String
            }

            return when {
                value.startsWith('@') -> Resource
                value.startsWith('?') -> Attribute
                ADLDimentionUtil.isDimensionFormat(value) -> Dimension
                isNumber(value) -> {
                    if (value.endsWith('f') || value.contains('.')) {
                        return Float
                    }
                    return Integer
                }
                isFlags(value) -> Flags
                else -> String
            }
        }

        private fun isNumber(value: kotlin.String): Boolean {
            return "^([+-]?\\\\d*\\\\.?\\\\d*[f])\$".toRegex().matches(value)
        }

        private fun isFlags(value: kotlin.String): Boolean {
            val comps = value.split('|')
            return comps.count() > 1
        }

    }
}
