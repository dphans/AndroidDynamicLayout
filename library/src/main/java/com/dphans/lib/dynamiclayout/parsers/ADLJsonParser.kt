package com.dphans.lib.dynamiclayout.parsers

import android.annotation.SuppressLint
import android.util.Log
import com.dphans.lib.dynamiclayout.ADL
import com.dphans.lib.dynamiclayout.behaviors.ADLDocumentParser
import com.dphans.lib.dynamiclayout.models.ADLDocument
import com.dphans.lib.dynamiclayout.parsers.ADLJsonParser.Companion.MIME_TYPE
import com.dphans.lib.dynamiclayout.utils.ADLDataImporter
import com.google.gson.GsonBuilder
import java.io.File
import java.io.InputStream

/**
 * Created by dphans in 2019
 *
 * Email: dinophan94@gmail.com
 * Github: @dphans
 */


class ADLJsonParser : ADLDocumentParser() {

    override fun getMimeType(): String {
        return MIME_TYPE
    }

    override fun getParserVersion(): Int {
        return 1
    }

    @SuppressLint("BinaryOperationInTimber")
    override fun parse(inputStream: InputStream): ADLDocument? {
        return try {
            val jsonString = inputStream
                .reader(charset = Charsets.UTF_8)
            val gsonBuilder = GsonBuilder()
            val document = gsonBuilder.create().fromJson(
                jsonString.readText(),
                ADLDocument::class.java
            )

            if (document.version != this@ADLJsonParser.getParserVersion()) {
                val isUpper = document.version > this@ADLJsonParser.getParserVersion()
                val compareText = if (isUpper) "upper" else "lower"
                Log.w(
                    "ADLJsonParser",
                    "Content view has been created with $compareText version of parser, " +
                            "some features may be not work correctly! " +
                            "(document: ${document.version}, " +
                            "parser: ${this@ADLJsonParser.getParserVersion()})"
                )
            }

            document
        } catch (exception: Exception) {
            exception.printStackTrace()
            null
        }
    }


    companion object {
        const val MIME_TYPE = "application/json"
    }

}

/**
 * JSON layout file need to parsing into View hierarchy.
 * The content of the JSON files must follow the correct format.
 * Please see the documentation for JSON formatting.
 *
 * Choose one of sources: assets, raw, file, path or InputStream.
 *
 * @param assets load file from file assets
 * @param raw load from raw resource by it id
 * @param file load from specific file (for external storage purposes)
 * @param path absolute path to source file (local file only)
 * @param inputStream load directy contents from input stream
 */
@Suppress("unused")
fun ADL.Builder.setLayoutJson(
    assets: String? = null,
    raw: Int? = null,
    file: File? = null,
    path: String? = null,
    inputStream: InputStream? = null
): ADL.Builder {
    assets?.let {
        this@setLayoutJson.mInputStream = ADLDataImporter.fromAsset(
            context = this@setLayoutJson.context,
            assetsPath = assets
        )
        this@setLayoutJson.mMimeType = MIME_TYPE
        return this@setLayoutJson
    }

    raw?.let {
        this@setLayoutJson.mInputStream = ADLDataImporter.fromRaw(
            context = this@setLayoutJson.context,
            rawId = it
        )
        this@setLayoutJson.mMimeType = MIME_TYPE
        return this@setLayoutJson
    }

    file?.let {
        this@setLayoutJson.mInputStream = ADLDataImporter.fromFile(
            file = it
        )
        this@setLayoutJson.mMimeType = MIME_TYPE
        return this@setLayoutJson
    }

    path?.let {
        this@setLayoutJson.mInputStream = ADLDataImporter.fromPath(
            filePath = it
        )
        this@setLayoutJson.mMimeType = MIME_TYPE
        return this@setLayoutJson
    }

    inputStream?.let {
        this@setLayoutJson.mInputStream = inputStream
        this@setLayoutJson.mMimeType = MIME_TYPE
        return this@setLayoutJson
    }

    return this@setLayoutJson
}
