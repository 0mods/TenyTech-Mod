package com.algorithmlx.tenytech.init

import com.algorithmlx.tenytech.api.helper.RecipeHelper
import com.algorithmlx.tenytech.capability.JEIBlockingCapability
import com.algorithmlx.tenytech.capability.MapBlockingCapability
import com.algorithmlx.tenytech.compact.curios.CuriosLoader
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.event.TickEvent.PlayerTickEvent
import net.minecraftforge.fml.ModList
import net.minecraftforge.fml.ModLoadingContext
import net.minecraftforge.fml.config.ModConfig
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext

object TTStartup {
    @JvmStatic
    fun init() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, TTConfig.spec, "tenytech/common.toml")
        val forgeBus = MinecraftForge.EVENT_BUS
        val modBus = FMLJavaModLoadingContext.get().modEventBus

        Register.init()

        if (ModList.get().isLoaded("curios")) modBus.addListener(CuriosLoader::registerMessages)
        forgeBus.addListener(this::playerTickEvent)
        forgeBus.addListener(this::commonStartup)
    }

    private fun playerTickEvent(evt: PlayerTickEvent) {
//        val player = evt.player
//        val level = player.level
//        val item = Register.flyRing.get()
//
//        if (!level.isClientSide) {
//            if (ModList.get().isLoaded("curios")) {
//                val curioItem = player.curioItem(item)
//
//                if (!curioItem.isPresent) {
//                    player.abilities.flying = false
//                    player.abilities.mayfly = false
//                }
//            } else {
//                if (!player.inventory.contains(item.defaultInstance)) {
//                    player.abilities.mayfly = false
//                }
//            }
//        }
    }

    private fun commonStartup(evt: FMLCommonSetupEvent) {
        JEIBlockingCapability.register()
        MapBlockingCapability.register()
        MinecraftForge.EVENT_BUS.register(RecipeHelper)
    }
}