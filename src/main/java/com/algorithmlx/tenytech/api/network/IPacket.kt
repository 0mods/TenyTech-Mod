package com.algorithmlx.tenytech.api.network

import net.minecraft.network.PacketBuffer
import net.minecraftforge.fml.network.NetworkEvent
import java.util.function.Supplier

interface IPacket {
    fun encode(buf: PacketBuffer)

    fun handle(ctx: Supplier<NetworkEvent.Context>)
}