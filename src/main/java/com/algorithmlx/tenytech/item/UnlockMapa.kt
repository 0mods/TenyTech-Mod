package com.algorithmlx.tenytech.item

import com.algorithmlx.tenytech.api.builder.TranslationBuilder
import com.algorithmlx.tenytech.client.ClientMapBlockPacket
import com.algorithmlx.tenytech.network.TTPackets
import com.algorithmlx.tenytech.network.server.C2SMapBlockerPacket
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.world.World

class UnlockMapa: Item(Properties()) {
    override fun use(pLevel: World, pPlayer: PlayerEntity, pHand: Hand): ActionResult<ItemStack> {
        var result = super.use(pLevel, pPlayer, pHand)
        val stack = pPlayer.getItemInHand(pHand)

        if (pPlayer.getItemInHand(pHand).item == this) {
            if (!ClientMapBlockPacket.canUse) {
                TTPackets.sendToServer(C2SMapBlockerPacket(true))
                pPlayer.displayClientMessage(TranslationBuilder.msg("map_unlocked").build, true)
                result = ActionResult.success(pPlayer.getItemInHand(pHand))
                stack.shrink(1)
            } else {
                pPlayer.displayClientMessage(TranslationBuilder.msg("map_unlock_failed").build, true)
            }
        }

        return result
    }
}