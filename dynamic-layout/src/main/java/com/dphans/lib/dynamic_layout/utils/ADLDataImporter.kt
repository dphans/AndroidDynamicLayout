package com.dphans.lib.dynamic_layout.utils

import android.content.Context
import java.io.File
import java.io.FileInputStream
import java.io.InputStream

/**
 * Created by dphans in 2019
 *
 * Email: dinophan94@gmail.com
 * Github: @dphans
 */


internal class ADLDataImporter {

    companion object {

        fun fromAsset(context: Context, assetsPath: String): InputStream {
            return context.applicationContext.assets.open(assetsPath)
        }

        fun fromRaw(context: Context, rawId: Int): InputStream {
            return context.applicationContext.resources.openRawResource(rawId)
        }

        fun fromFile(file: File): InputStream {
            if (file.isFile && file.exists()) {
                return FileInputStream(file)
            }
            throw IllegalStateException("File ${file.absoluteFile} not exist!")
        }

        fun fromPath(filePath: String): InputStream {
            return this@Companion.fromFile(File(filePath))
        }

    }

}
