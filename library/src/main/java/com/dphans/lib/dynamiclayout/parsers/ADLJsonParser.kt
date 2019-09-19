package com.dphans.lib.dynamiclayout.parsers

import android.annotation.SuppressLint
import android.util.Log
import com.dphans.lib.dynamiclayout.ADL
import com.dphans.lib.dynamiclayout.behaviors.ADLDocumentParser
import com.dphans.lib.dynamiclayout.models.ADLAttribute
import com.dphans.lib.dynamiclayout.models.ADLDocument
import com.dphans.lib.dynamiclayout.models.ADLWidget
import com.dphans.lib.dynamiclayout.parsers.ADLJsonParser.Companion.MIME_TYPE
import com.dphans.lib.dynamiclayout.utils.ADLDataImporter
import org.json.JSONObject
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
                .readText()
            val document = this@ADLJsonParser.parseDocument(
                json = jsonString
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

    private fun parseDocument(json: String): ADLDocument {
        val document = ADLDocument()
        try {
            val jsonObject = JSONObject(json)
            document.version = jsonObject.getInt("version")

            document.view = this@ADLJsonParser
                .parseWidget(jsonObject.getJSONObject("view"))

            val viewClasses = jsonObject.getJSONArray("view_classes")
            document.view_classes = (0 until viewClasses.length())
                .mapNotNull { viewClasses.getString(it) }

            val viewIds = jsonObject.getJSONArray("view_ids")
            document.view_ids = (0 until viewIds.length())
                .mapNotNull { viewIds.getString(it) }

            val xmlNs = jsonObject.getJSONObject("xmlns")
            document.xmlns = xmlNs.keys().asSequence().toList().mapNotNull {
                if (xmlNs.getString(it).isNullOrBlank()) {
                    return@mapNotNull null
                }
                return@mapNotNull Pair(it, xmlNs.getString(it))
            }.toMap()
        } catch (exception: Exception) {
            Log.println(Log.ASSERT, "ADLJsonParser", exception.message)
        } finally {
            return document
        }
    }

    private fun parseWidget(jsonObject: JSONObject): ADLWidget? {
        val widget = ADLWidget()
        widget._uid = jsonObject.getString("_uid")
        widget.view_class = jsonObject.getString("view_class")

        val attributes = jsonObject.getJSONArray("attributes")
        widget.attributes = (0 until attributes.length()).mapNotNull {
            this@ADLJsonParser.parseAttributes(
                jsonObject = attributes.getJSONObject(it)
            )
        }

        val childrens = jsonObject.getJSONArray("childrens")
        widget.childrens = (0 until childrens.length()).mapNotNull {
            this@ADLJsonParser.parseWidget(
                jsonObject = childrens.getJSONObject(it)
            )
        }
        return widget
    }

    private fun parseAttributes(jsonObject: JSONObject): ADLAttribute? {
        val attribute = ADLAttribute()
        attribute.name = jsonObject.getString("name")
        attribute.namespace = jsonObject.getString("namespace")
        attribute.key = jsonObject.getString("key")
        attribute.value = jsonObject.getString("value")
        return attribute
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
