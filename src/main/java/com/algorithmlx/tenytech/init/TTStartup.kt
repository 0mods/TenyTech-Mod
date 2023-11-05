package com.algorithmlx.tenytech.init

import com.algorithmlx.tenytech.compact.curios.CuriosLoader
import com.algorithmlx.tenytech.compact.curios.CuriosLoader.hasCurioItem
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.event.TickEvent.PlayerTickEvent
import net.minecraftforge.fml.ModList
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext

object TTStartup {
    private var repairTick = 0

    @JvmStatic
    fun init() {
        val forgeBus = MinecraftForge.EVENT_BUS
        val modBus = FMLJavaModLoadingContext.get().modEventBus

        Register.init()

        if (ModList.get().isLoaded("curios")) modBus.addListener(CuriosLoader::registerMessages)
        forgeBus.addListener(this::playerTickEvent)
    }

    private fun playerTickEvent(evt: PlayerTickEvent) {
        /*val player = evt.player
        val level = player.level
        val stack = Register.flyRing.get().defaultInstance

        if (!level.isClientSide) {
            if (player.isCreative || player.isSpectator) return

            if (ModList.get().isLoaded("curios")) {
                if (player.inventory.contains(stack) || player.hasCurioItem(stack)) {
                    player.abilities.flying = false
                    player.abilities.mayfly = false
                }
                return
            }

            if (player.inventory.contains(stack)) {
                if (stack.damageValue < stack.maxDamage - 1) {
                    player.abilities.mayfly = true

                    if (player.abilities.flying)
                        stack.damageValue += 1
                } else {
                    player.abilities.mayfly = false
                    player.abilities.flying = false
                }

                if (stack.isDamaged) {
                    if (!player.abilities.flying) repairTick++
                    else repairTick = 0

                    if (repairTick >= 1200) {
                        var repaired = stack.damageValue - 100

                        if (repaired < 0) repaired = 0

                        stack.damageValue = repaired

                        repairTick = 0
                    }
                }
            }
        }*/
    }
}