package com.algorithmlx.tenytech.network

import com.algorithmlx.tenytech.ModId
import net.minecraft.entity.player.ServerPlayerEntity
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.network.NetworkDirection
import net.minecraftforge.fml.network.NetworkRegistry
import net.minecraftforge.fml.network.PacketDistributor.PacketTarget
import net.minecraftforge.fml.network.simple.SimpleChannel

object TTPackets {
    private val version = "1.0"
    private var identifier = 0
        get() = field++

    private lateinit var network: SimpleChannel

    fun start() {
        val localNetwork = NetworkRegistry.newSimpleChannel(
            ResourceLocation(ModId, "main"),
            ::version,
            version::equals,
            version::equals
        )

        this.network = localNetwork

    }

    fun <MSG> ServerPlayerEntity.sendToPlayer(obj: MSG) {
        network.sendTo(obj, this.connection.connection, NetworkDirection.PLAY_TO_CLIENT)
    }

    fun <MSG> sendToServer(obj: MSG) {
        network.sendToServer(obj)
    }

    fun <MSG> send(target: PacketTarget, message: MSG) {
        network.send(target, message)
    }
}