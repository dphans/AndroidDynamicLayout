package com.dphans.lib.dynamic_layout.models.actions

import com.dphans.lib.dynamic_layout.enums.ADLAttributeValueTypes
import com.dphans.lib.dynamic_layout.models.ADLAttribute

/**
 * Created by dphans in 2019
 *
 * Email: dinophan94@gmail.com
 * Github: @dphans
 */


fun ADLAttribute.getType(): ADLAttributeValueTypes {

    return ADLAttributeValueTypes.getTypeWithValue(
        value = this@getType.value
    )

}
