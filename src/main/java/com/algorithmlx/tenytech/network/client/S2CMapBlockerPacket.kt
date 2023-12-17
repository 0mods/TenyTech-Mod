package com.algorithmlx.tenytech.network.client

import com.algorithmlx.tenytech.api.network.IPacket
import com.algorithmlx.tenytech.client.ClientMapBlockPacket
import net.minecraft.network.PacketBuffer
import net.minecraftforge.fml.network.NetworkEvent
import java.util.function.Supplier

class S2CMapBlockerPacket(val canUse: Boolean): IPacket {
    constructor(byteBuf: PacketBuffer) : this(byteBuf.readBoolean())

    override fun encode(buf: PacketBuffer) {
        buf.writeBoolean(canUse)
    }

    override fun handle(ctx: Supplier<NetworkEvent.Context>) {
        val context = ctx.get()
        context.enqueueWork { ClientMapBlockPacket.canUse = this.canUse }
    }
}