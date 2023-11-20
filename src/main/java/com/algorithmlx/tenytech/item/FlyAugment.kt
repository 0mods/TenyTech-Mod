package com.algorithmlx.tenytech.item

import com.algorithmlx.tenytech.api.builder.TranslationBuilder
import com.algorithmlx.tenytech.init.Register.tab
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.text.ITextComponent
import net.minecraft.world.World

class FlyAugment: Item(Properties().tab(tab)) {
    override fun appendHoverText(
        pStack: ItemStack,
        pLevel: World?,
        pTooltip: MutableList<ITextComponent>,
        pFlag: ITooltipFlag
    ) {
        pTooltip.add(TranslationBuilder.msg("augment_fly").build)
    }
}