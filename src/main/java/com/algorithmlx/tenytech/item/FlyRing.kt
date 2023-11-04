package com.algorithmlx.tenytech.item

import com.algorithmlx.tenytech.api.TranslationBuilder
import com.algorithmlx.tenytech.compact.curios.CuriosLoader
import com.algorithmlx.tenytech.init.Register.tab
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.entity.Entity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.nbt.CompoundNBT
import net.minecraft.util.text.ITextComponent
import net.minecraft.world.World
import net.minecraftforge.common.capabilities.ICapabilityProvider
import net.minecraftforge.fml.ModList

class FlyRing: Item(Properties().tab(tab).fireResistant().stacksTo(1).durability(7200000)) {
    private var repairTick = 0

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

    override fun inventoryTick(
        pStack: ItemStack,
        pLevel: World,
        player: Entity,
        pItemSlot: Int,
        pIsSelected: Boolean
    ) {
        if (player is PlayerEntity) {
            if (player.inventory.contains(this.defaultInstance)) {
                if (this.defaultInstance.damageValue > 2) {
                    player.abilities.mayfly = true

                    if (player.abilities.flying)
                        this.defaultInstance.hurtAndBreak(1, player) {}
                } else {
                    player.abilities.mayfly = false
                    player.abilities.flying = false
                }

                if (this.defaultInstance.isDamaged) {
                    if (!player.abilities.flying) repairTick++

                    if (repairTick >= 1200) {
                        var repaired = this.defaultInstance.damageValue + 100

                        if (repaired > this.defaultInstance.maxDamage)
                            repaired = this.defaultInstance.maxDamage - this.defaultInstance.damageValue

                        this.defaultInstance.damageValue = repaired

                        repairTick = 0
                    }
                }
            }
        }
    }

    override fun initCapabilities(stack: ItemStack, nbt: CompoundNBT?): ICapabilityProvider? {
        return if (ModList.get().isLoaded("curios")) CuriosLoader.flyRing(stack) else super.initCapabilities(stack, nbt)
    }
}