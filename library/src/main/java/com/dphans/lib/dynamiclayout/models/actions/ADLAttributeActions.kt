package com.dphans.lib.dynamiclayout.models.actions

import com.dphans.lib.dynamiclayout.enums.ADLAttributeValueTypes
import com.dphans.lib.dynamiclayout.models.ADLAttribute

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
