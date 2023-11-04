package com.algorithmlx.tenytech.init

import com.algorithmlx.tenytech.compact.curios.CuriosLoader
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext
import net.minecraftforge.fml.loading.FMLLoader

object TTStartup {
    @JvmStatic
    fun init() {
        val forgeBus = MinecraftForge.EVENT_BUS
        val modBus = FMLJavaModLoadingContext.get().modEventBus

        Register.init()

        modBus.addListener(CuriosLoader::registerMessages)
    }
}