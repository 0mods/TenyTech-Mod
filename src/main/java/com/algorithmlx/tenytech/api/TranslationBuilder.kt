package com.algorithmlx.tenytech.api

import net.minecraft.util.text.IFormattableTextComponent
import net.minecraft.util.text.StringTextComponent
import net.minecraft.util.text.TextFormatting
import net.minecraft.util.text.TranslationTextComponent
import net.minecraftforge.fml.ModLoadingContext

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
        this.formats += format
        return this
    }

    fun prepend(string: String): TranslationBuilder {
        this.prepend += string
        return this
    }

    fun build(): IFormattableTextComponent {
        var component: IFormattableTextComponent = TranslationTextComponent(key, arguments)

        if (prepend.isNotEmpty()) component = StringTextComponent(prepend).append(component)

        if (this::formats.isInitialized) component.withStyle()

        return component
    }

    fun string(): String = this.build().string

    companion object {
        fun builder(key: String): TranslationBuilder = TranslationBuilder(key)

        fun builder(prepend: String, key: String): TranslationBuilder = TranslationBuilder(prepend, key)

        fun block(key: String): TranslationBuilder = builder(
            "block.${ModLoadingContext.get().activeContainer.modId}.$key"
        )

        fun item(key: String): TranslationBuilder = builder(
            "item.${ModLoadingContext.get().activeContainer.modId}.$key"
        )

        fun menu(key: String): TranslationBuilder = builder(
            "menu.${ModLoadingContext.get().activeContainer.modId}.$key"
        )

        fun msg(key: String): TranslationBuilder = builder(
            "msg.${ModLoadingContext.get().activeContainer.modId}.$key"
        )
    }
}