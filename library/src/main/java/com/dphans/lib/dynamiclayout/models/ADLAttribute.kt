package com.dphans.lib.dynamiclayout.models

import java.io.Serializable

/**
 * Created by dphans in 2019
 *
 * Email: dinophan94@gmail.com
 * Github: @dphans
 */


@Suppress("unused", "PropertyName")
open class ADLAttribute : Serializable {

    var name: String? = null

    var namespace: String? = null

    var key: String? = null

    var value: String? = null

}
