package com.algorithmlx.tenytech.api

import net.minecraft.client.Minecraft
import net.minecraft.server.MinecraftServer
import net.minecraft.util.ResourceLocation
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.fml.ModList
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext
import net.minecraftforge.fml.server.ServerLifecycleHooks

val Number.seconds
    get() = (this as Int) * 20

val Number.minutes
    get() = (this as Int) * 1200

val Number.hours
    get() = (this as Int) * 72000

val forgeBus: IEventBus
    get() = MinecraftForge.EVENT_BUS

val modBus: IEventBus
    get() = FMLJavaModLoadingContext.get().modEventBus

fun String.isLoaded(): Boolean = ModList.get().isLoaded(this)

fun String.toRL() = ResourceLocation(this)

val String.toRL
    get() = this.toRL()

val mcClient: Minecraft
    get() = Minecraft.getInstance()

val mcServer: MinecraftServer
    get() = ServerLifecycleHooks.getCurrentServer()