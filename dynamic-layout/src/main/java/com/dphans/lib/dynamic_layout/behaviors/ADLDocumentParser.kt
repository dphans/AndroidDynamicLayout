package com.dphans.lib.dynamic_layout.behaviors

import com.dphans.lib.dynamic_layout.models.ADLDocument
import java.io.InputStream

/**
 * Created by dphans in 2019
 *
 * Email: dinophan94@gmail.com
 * Github: @dphans
 */


abstract class ADLDocumentParser {

    abstract fun getMimeType(): String

    abstract fun getParserVersion(): Int

    abstract fun parse(inputStream: InputStream): ADLDocument?

}
