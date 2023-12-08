package com.algorithmlx.tenytech.api

import net.minecraft.client.Minecraft
import net.minecraft.server.MinecraftServer
import net.minecraftforge.fml.server.ServerLifecycleHooks

val Number.seconds
    get() = (this as Int) * 20

val Number.minutes
    get() = (this as Int) * 1200

val Number.hours
    get() = (this as Int) * 72000

val mcClient: Minecraft
    get() = Minecraft.getInstance()

val mcServer: MinecraftServer
    get() = ServerLifecycleHooks.getCurrentServer()