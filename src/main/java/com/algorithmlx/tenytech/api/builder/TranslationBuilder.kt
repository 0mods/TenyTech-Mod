package com.algorithmlx.tenytech.api.builder

import com.algorithmlx.tenytech.ModId
import net.minecraft.util.text.IFormattableTextComponent
import net.minecraft.util.text.StringTextComponent
import net.minecraft.util.text.TextFormatting
import net.minecraft.util.text.TranslationTextComponent

class TranslationBuilder private constructor(private var prepend: String = "", private val key: String) {
    private var arguments: Array<Any> = arrayOf()
    private lateinit var formats: Array<TextFormatting>

    private constructor(key: String): this("", key)

    fun arg(arg: Any): TranslationBuilder {
        this.arguments += arg
        return this
    }

    fun args(vararg args: Any): TranslationBuilder {
        for (arg in args) this.arguments += arg

        return this
    }

    fun format(vararg format: TextFormatting): TranslationBuilder {
        this.formats = arrayOf(*format)
        return this
    }

    fun prepend(string: String): TranslationBuilder {
        this.prepend += string
        return this
    }

    @get:JvmName("build")
    val build: IFormattableTextComponent
        get() {
            var component: IFormattableTextComponent = TranslationTextComponent(key, arguments)

            if (prepend.isNotEmpty()) component = StringTextComponent(prepend).append(component)

            if (this::formats.isInitialized) component.withStyle()

            return component
        }

    fun string(): String = this.build.string

    companion object {
        fun builder(key: String): TranslationBuilder = TranslationBuilder(key)

        fun builder(prepend: String, key: String): TranslationBuilder = TranslationBuilder(prepend, key)

        fun block(key: String, modId: String = ModId): TranslationBuilder = builder(
            "block.$modId.$key"
        )

        fun item(key: String, modId: String = ModId): TranslationBuilder = builder(
            "item.$modId.$key"
        )

        fun menu(key: String, modId: String = ModId): TranslationBuilder = builder(
            "menu.$modId.$key"
        )

        fun msg(key: String, modId: String = ModId): TranslationBuilder = builder(
            "msg.$modId.$key"
        )
    }
}