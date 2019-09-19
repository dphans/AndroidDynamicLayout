package com.dphans.lib.dynamiclayout.models

import java.io.Serializable

/**
 * Created by dphans in 2019
 *
 * Email: dinophan94@gmail.com
 * Github: @dphans
 */


@Suppress("unused", "PropertyName")
open class ADLDocument : Serializable {

    var version: Int = 1

    var view: ADLWidget? = null

    var view_classes: List<String> = emptyList()

    var view_ids: List<String> = emptyList()

    var xmlns: Map<String, String> = emptyMap()

}
