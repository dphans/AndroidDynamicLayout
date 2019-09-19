package com.dphans.lib.dynamiclayout.models

import com.dphans.lib.dynamiclayout.behaviors.ADLClassJsonable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by dphans in 2019
 *
 * Email: dinophan94@gmail.com
 * Github: @dphans
 */


@Suppress("unused", "PropertyName")
open class ADLAttribute : ADLClassJsonable {

    @SerializedName("name")
    @Expose(serialize = true, deserialize = true)
    var name: String? = null

    @SerializedName("namespace")
    @Expose(serialize = true, deserialize = true)
    var namespace: String? = null

    @SerializedName("key")
    @Expose(serialize = true, deserialize = true)
    var key: String? = null

    @SerializedName("value")
    @Expose(serialize = true, deserialize = true)
    var value: String? = null


    override fun toString(): String {
        return this@ADLAttribute.toJSON()
    }

}
