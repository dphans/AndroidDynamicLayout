package com.dphans.lib.dynamic_layout

import android.content.Context
import android.view.View
import com.dphans.lib.dynamic_layout.adapters.ADLLinearLayoutAttributeAdapter
import com.dphans.lib.dynamic_layout.adapters.ADLTextViewAttributeAdapter
import com.dphans.lib.dynamic_layout.adapters.ADLViewAttrbiteAdapter
import com.dphans.lib.dynamic_layout.adapters.ADLViewGroupAttributeAdapter
import com.dphans.lib.dynamic_layout.behaviors.ADLAttributeAdapter
import com.dphans.lib.dynamic_layout.behaviors.ADLDocumentParser
import com.dphans.lib.dynamic_layout.models.actions.getView
import com.dphans.lib.dynamic_layout.parsers.ADLJsonParser
import java.io.InputStream

/**
 * Created by dphans in 2019
 *
 * Email: dinophan94@gmail.com
 * Github: @dphans
 */


@Suppress("MemberVisibilityCanBePrivate")
class ADL {

    companion object {

        private val mBuiltInParserClasses = listOf<Class<out ADLDocumentParser>>(
            ADLJsonParser::class.java
        )

        private val mBuiltInAttributeAdapters = listOf<ADLAttributeAdapter<*>>(
            ADLViewAttrbiteAdapter(),
            ADLViewGroupAttributeAdapter(),
            ADLLinearLayoutAttributeAdapter(),
            ADLTextViewAttributeAdapter()
        )

    }


    @Suppress("unused")
    class Builder(internal val context: Context) {

        internal var mInputStream: InputStream? = null
        internal var mMimeType: String? = null
        internal val mParsers: ArrayList<Class<out ADLDocumentParser>> = ArrayList()
        internal val mAdapters: ArrayList<ADLAttributeAdapter<out View>> = ArrayList()


        init {
            this@Builder.mParsers.clear()
            this@Builder.mParsers.addAll(mBuiltInParserClasses)
            this@Builder.mAdapters.clear()
            this@Builder.mAdapters.addAll(mBuiltInAttributeAdapters)
        }

        /**
         * Define your custom source file parsers
         *
         * If you need to parse layout with your custom file extension other than Json (built-in parser),
         * you can create custom parser and define it here.
         *
         * The parser classes must be inherit from
         * @see com.dphans.lib.dynamic_layout.behaviors.ADLDocumentParser
         *
         * Example: If you need to custom xml file with another structure (with mime-type: application/xhtml+xml)
         * Define class extend from ADLDocumentParser, for example:
         *
         * <code>
         * class MyCustomXmlParser: ADLDocumentParser {
         *
         *      fun getMimeType() = "application/xhtml+xml"
         *
         *      fun getParserVersion() = 1
         *
         *      fun parse(inputStream: InputStream): ADLDocument? {
         *          // parse your data here...
         *      }
         *
         * }
         * </code>
         *
         * Then define it with this method:
         * <code>
         * ADL.Builder.includeParsers(MyCustomXmlParser::class.java)
         * </code>
         */
        fun includeParsers(vararg parsers: Class<ADLDocumentParser>) {
            parsers.forEach { clazz ->
                if (!this@Builder.mParsers.contains(clazz)) {
                    this@Builder.mParsers.add(clazz)
                }
            }
        }

        /**
         * Remove built-in source file parsers (fully customizations, not recommended)
         *
         * Note: This will also remove all built-in json parsers
         * @see com.dphans.lib.dynamic_layout.parsers for default parsers
         *
         * Supported parsers:
         * @see com.dphans.lib.dynamic_layout.parsers.setLayoutJson will also not working
         */
        fun noBuiltInParsers() {
            this@Builder.mParsers.removeAll { clazz ->
                return@removeAll mBuiltInParserClasses.contains(clazz)
            }
        }

        /**
         * Define custom attributes parsers for custom widgets
         */
        fun includeAttributeAdapters(vararg adapters: ADLAttributeAdapter<*>) {
            adapters.forEach { instance ->
                if (!this@Builder.mAdapters.contains(instance)) {
                    this@Builder.mAdapters.add(instance)
                }
            }
        }

        /**
         * Remove built-in attribute adapters (fully customizations, not recommended)
         *
         * Note: This will also remove all default attribute parsers for base android widgets
         */
        fun noBuiltInAttributeAdapters() {
            this@Builder.mAdapters.removeAll { instance ->
                return@removeAll mBuiltInAttributeAdapters.contains(instance)
            }
        }

        /**
         * Creating view with defining middleware.
         */
        fun build(func: Builder.() -> Unit): View {
            func.invoke(this@Builder)
            return this@Builder.createView()
        }

        /**
         * Create view with defined builder
         */
        fun createView(): View {
            val inputStream = this@Builder.mInputStream
                ?: throw IllegalStateException(
                    "Nothing to create view (Error: InputStream is null)."
                )
            val mimeType = this@Builder.mMimeType
                ?: throw IllegalStateException(
                    "Invalid mime type (Error: MimeType is null)."
                )
            val documentParser = this@Builder.mParsers.firstOrNull { clazz ->
                clazz.newInstance().getMimeType() == mimeType
            }
                ?: throw IllegalStateException(
                    "No parser found for content with mime type: " +
                            "'$mimeType' (Error: Parser not found)."
                )

            val document = documentParser.newInstance().parse(
                inputStream = inputStream
            )
                ?: throw IllegalStateException(
                    "Error while parsing document, please recheck file type or your custom parser!"
                )

            document.view_classes.forEach { viewClass ->
                try {
                    val clazz = Class.forName(viewClass)
                    if (!View::class.java.isAssignableFrom(clazz)) {
                        throw IllegalStateException(
                            "Class $viewClass must be a widget!"
                        )
                    }
                } catch (exception: ClassNotFoundException) {
                    throw ClassNotFoundException(
                        "Class with name $viewClass does not exist in app!"
                    )
                } catch (exception: Exception) {
                    throw exception
                }
            }

            return document.getView(
                context = this@Builder.context,
                config = this@Builder
            )
        }

    }

}
