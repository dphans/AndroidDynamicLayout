package com.dphans.lib.dynamic_layout.behaviors

import com.google.gson.GsonBuilder
import java.io.Serializable

/**
 * Created by dphans in 2019
 *
 * Email: dinophan94@gmail.com
 * Github: @dphans
 */


interface ADLClassJsonable : Serializable {

    fun toJSON(pretify: Boolean = true): String {
        val builder = GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
        if (pretify) {
            builder.setPrettyPrinting()
        }
        return builder
            .create()
            .toJson(this@ADLClassJsonable)
    }

}
