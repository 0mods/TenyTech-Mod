package com.algorithmlx.tenytech.item

import com.algorithmlx.tenytech.api.TranslationBuilder
import com.algorithmlx.tenytech.compact.curios.CuriosLoader
import com.algorithmlx.tenytech.init.Register.tab
import com.algorithmlx.tenytech.init.TTClientStartup
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.entity.Entity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.inventory.EquipmentSlotType
import net.minecraft.item.FlintAndSteelItem
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.nbt.CompoundNBT
import net.minecraft.util.text.ITextComponent
import net.minecraft.world.World
import net.minecraftforge.common.capabilities.ICapabilityProvider
import net.minecraftforge.fml.ModList
import net.minecraftforge.fml.loading.FMLLoader

class FlyRing: Item(Properties().tab(tab).fireResistant().stacksTo(1).durability(7200000)) {
    init {
        if (FMLLoader.getDist().isClient) {
            TTClientStartup.flyRingRenderer(this)
        }
    }

    override fun appendHoverText(
        pStack: ItemStack,
        pLevel: World?,
        pTooltip: MutableList<ITextComponent>,
        pFlag: ITooltipFlag
    ) {
        pTooltip.add(TranslationBuilder.msg("gives_a_fly").build())
        pTooltip.add(TranslationBuilder.msg("has_durability").build())
        pTooltip.add(TranslationBuilder.msg("repairs_at_time").build())
    }

    override fun initCapabilities(stack: ItemStack, nbt: CompoundNBT?): ICapabilityProvider? {
        return if (ModList.get().isLoaded("curios")) CuriosLoader.flyRing(stack) else super.initCapabilities(stack, nbt)
    }
}