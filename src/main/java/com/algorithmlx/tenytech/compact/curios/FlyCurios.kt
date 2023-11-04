package com.algorithmlx.tenytech.compact.curios

import com.algorithmlx.tenytech.api.TranslationBuilder
import com.algorithmlx.tenytech.init.Register
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.util.text.ITextComponent
import top.theillusivec4.curios.api.CuriosApi
import top.theillusivec4.curios.api.SlotContext
import top.theillusivec4.curios.api.type.capability.ICurio

class FlyCurios(private val stack: ItemStack): ICurio {
    private var repairTick = 0

    override fun canEquip(identifier: String?, livingEntity: LivingEntity): Boolean =
        !CuriosApi.getCuriosHelper().findEquippedCurio(Register.flyRing.get(), livingEntity).isPresent

    override fun canUnequip(identifier: String?, livingEntity: LivingEntity?): Boolean = true

    override fun canEquipFromUse(slotContext: SlotContext?): Boolean = true

    override fun onUnequip(slotContext: SlotContext, newStack: ItemStack?) {
        val entity = slotContext.wearer

        if (entity is PlayerEntity) {
            if (entity.isCreative || entity.isSpectator) return

            entity.abilities.flying = false
            entity.abilities.mayfly = false
        }
    }

    override fun curioTick(identifier: String, index: Int, player: LivingEntity) {
        if (player is PlayerEntity) {
            if (player.isCreative || player.isSpectator) return // don't break ring, if player on creative or spectator
            if (CuriosApi.getCuriosHelper().findFirstCurio(player, this.stack.item).isPresent) {
                if (this.stack.damageValue < this.stack.maxDamage - 2) {
                    if (player.abilities.flying) {
                        this.stack.hurtAndBreak(1, player) { CuriosApi.getCuriosHelper().onBrokenCurio(identifier, index, player) }
                    }

                    if (!player.abilities.mayfly)
                        player.abilities.mayfly = true
                } else {
                    player.abilities.mayfly = false
                    player.abilities.flying = false
                }

                if (this.stack.isDamaged) {
                    if (!player.abilities.flying) repairTick++
                    else repairTick = 0

                    if (repairTick >= 1200) {
                        var repaired = this.stack.damageValue - 100

                        if (repaired < 0) repaired = 0

                        this.stack.damageValue = repaired

                        repairTick = 0
                    }
                }
            } else {
                player.abilities.mayfly = false
                player.abilities.flying = false
            }
        }
    }

    override fun getTagsTooltip(tagTooltips: MutableList<ITextComponent>): MutableList<ITextComponent> {
        val seconds = this.repairTick / 20
        tagTooltips.add(TranslationBuilder.block("repair_time").arg(seconds).build())
        return super.getTagsTooltip(tagTooltips)
    }
}