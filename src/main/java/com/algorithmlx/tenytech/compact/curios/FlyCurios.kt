package com.algorithmlx.tenytech.compact.curios

import com.algorithmlx.tenytech.item.FlyRing
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import top.theillusivec4.curios.api.CuriosApi
import top.theillusivec4.curios.api.SlotContext
import top.theillusivec4.curios.api.type.capability.ICurio

class FlyCurios(private val stack: ItemStack): ICurio {
    override fun canEquip(identifier: String?, livingEntity: LivingEntity): Boolean =
        !CuriosApi.getCuriosHelper().findFirstCurio(livingEntity, this.stack.item).isPresent

    override fun canUnequip(identifier: String?, livingEntity: LivingEntity): Boolean =
        if (livingEntity is PlayerEntity) !livingEntity.abilities.flying else true

    override fun canEquipFromUse(slotContext: SlotContext?): Boolean = true

    override fun curioTick(identifier: String, index: Int, player: LivingEntity) {
        if (player is PlayerEntity) {
            FlyRing.makeOrBreakFly(
                player,
                this.stack,
                CuriosApi.getCuriosHelper().findFirstCurio(player, this.stack.item).isPresent
            ) {
                CuriosApi.getCuriosHelper().onBrokenCurio(identifier, index, it)
            }
        }
    }
}