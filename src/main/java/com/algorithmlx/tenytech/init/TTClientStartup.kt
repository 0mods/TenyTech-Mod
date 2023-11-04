package com.algorithmlx.tenytech.init

import com.algorithmlx.tenytech.ModId
import net.minecraft.item.Item
import net.minecraft.item.ItemModelsProperties
import net.minecraft.util.ResourceLocation

object TTClientStartup {
    fun flyRingRenderer(item: Item) {
        ItemModelsProperties.register(
            item,
            ResourceLocation(ModId, "damage")
        ) { pStack, _, _ ->
            if (pStack.isDamaged) {
                val fullDur = 7200000
                val state1 = fullDur % 20
                val state2 = fullDur % 50
                val state3 = fullDur % 80
                val damageValue = pStack.damageValue

                if (damageValue < state1)
                    return@register 0F
                else if (damageValue in state1 downTo state2)
                    return@register 1F
                else if (damageValue in state2 downTo state3) return@register 2F
                else return@register 3F
            } else return@register 0F
        }
    }
}