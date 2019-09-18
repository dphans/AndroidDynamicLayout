package com.dphans.lib.dynamic_layout.models

import com.dphans.lib.dynamic_layout.behaviors.ADLClassJsonable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by dphans in 2019
 *
 * Email: dinophan94@gmail.com
 * Github: @dphans
 */


@Suppress("unused", "PropertyName")
open class ADLWidget : ADLClassJsonable {

    @SerializedName("_uid")
    @Expose(serialize = true, deserialize = true)
    var _uid: String? = null

    @SerializedName("view_class")
    @Expose(serialize = true, deserialize = true)
    var view_class: String? = null

    @SerializedName("attributes")
    @Expose(serialize = true, deserialize = true)
    var attributes: List<ADLAttribute> = emptyList()

    @SerializedName("childrens")
    @Expose(serialize = true, deserialize = true)
    var childrens: List<ADLWidget> = emptyList()


    override fun toString(): String {
        return this@ADLWidget.toJSON()
    }

}
