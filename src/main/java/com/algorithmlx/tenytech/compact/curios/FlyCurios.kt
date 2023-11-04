package com.algorithmlx.tenytech.compact.curios

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import top.theillusivec4.curios.api.CuriosApi
import top.theillusivec4.curios.api.SlotContext
import top.theillusivec4.curios.api.type.capability.ICurio

class FlyCurios(private val stack: ItemStack): ICurio {
    private var isEquipped = false
    private var repairTick = 0

    override fun onEquip(slotContext: SlotContext, prevStack: ItemStack?) {
        isEquipped = true
    }

    override fun onUnequip(slotContext: SlotContext?, newStack: ItemStack?) {
        this.isEquipped = false
    }

    override fun canEquip(identifier: String?, livingEntity: LivingEntity?): Boolean = true

    override fun canUnequip(identifier: String?, livingEntity: LivingEntity?): Boolean = true

    override fun canEquipFromUse(slotContext: SlotContext?): Boolean = true

    override fun curioTick(identifier: String, index: Int, player: LivingEntity) {
        if (player is PlayerEntity) {
            if (isEquipped) {
                if (this.stack.damageValue > 2) {
                    player.abilities.mayfly = true

                    if (player.abilities.flying)
                        this.stack.hurtAndBreak(1, player) { _ -> CuriosApi.getCuriosHelper().onBrokenCurio(identifier, index, player) }
                } else {
                    player.abilities.mayfly = false
                    player.abilities.flying = false
                }

                if (this.stack.isDamaged) {
                    if (!player.abilities.flying) repairTick++

                    if (repairTick >= 1200) {
                        var repaired = this.stack.damageValue + 100

                        if (repaired > this.stack.maxDamage)
                            repaired = this.stack.maxDamage - this.stack.damageValue

                        this.stack.damageValue = repaired

                        repairTick = 0
                    }
                }
            }
        }
    }
}