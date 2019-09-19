package com.dphans.lib.dynamiclayout.utils

/**
 * Created by dphans in 2019
 *
 * Email: dinophan94@gmail.com
 * Github: @dphans
 */


object ADLUtils {

    /**
     * Pass any exception in block code
     */
    inline fun <reified T> passException(block: () -> T?): T? {
        return try {
            block.invoke()
        } catch (exception: Exception) {
            exception.printStackTrace()
            null
        }
    }

}
