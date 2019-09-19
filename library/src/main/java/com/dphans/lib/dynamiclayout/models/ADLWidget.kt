package com.dphans.lib.dynamiclayout.models

import java.io.Serializable

/**
 * Created by dphans in 2019
 *
 * Email: dinophan94@gmail.com
 * Github: @dphans
 */


@Suppress("unused", "PropertyName")
open class ADLWidget : Serializable {

    var _uid: String? = null

    var view_class: String? = null

    var attributes: List<ADLAttribute> = emptyList()

    var childrens: List<ADLWidget> = emptyList()

}
