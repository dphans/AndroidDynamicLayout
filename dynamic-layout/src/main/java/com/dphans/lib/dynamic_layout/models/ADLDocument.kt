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
open class ADLDocument : ADLClassJsonable {

    @SerializedName("version")
    @Expose(serialize = true, deserialize = true)
    var version: Int = 1

    @SerializedName("view")
    @Expose(serialize = true, deserialize = true)
    var view: ADLWidget? = null

    @SerializedName("view_classes")
    @Expose(serialize = true, deserialize = true)
    var view_classes: List<String> = emptyList()

    @SerializedName("view_ids")
    @Expose(serialize = true, deserialize = true)
    var view_ids: List<String> = emptyList()

    @SerializedName("xmlns")
    @Expose(serialize = true, deserialize = true)
    var xmlns: Map<String, String> = emptyMap()


    override fun toString(): String {
        return this@ADLDocument.toJSON()
    }

}
