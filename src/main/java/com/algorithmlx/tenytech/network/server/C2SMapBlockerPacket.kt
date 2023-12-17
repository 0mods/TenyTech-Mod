package com.algorithmlx.tenytech.network.server

import com.algorithmlx.tenytech.api.network.IPacket
import com.algorithmlx.tenytech.capability.JEIBlockingCapability
import net.minecraft.network.PacketBuffer
import net.minecraftforge.fml.network.NetworkEvent
import java.util.function.Supplier

class C2SMapBlockerPacket(val canUse: Boolean): IPacket {
    constructor(byteBuf: PacketBuffer) : this(byteBuf.readBoolean())

    override fun encode(buf: PacketBuffer) {
        buf.writeBoolean(this.canUse)
    }

    override fun handle(ctx: Supplier<NetworkEvent.Context>) {
        val context = ctx.get()

        context.enqueueWork {
            val serverPlayer = context.sender ?: return@enqueueWork
            serverPlayer.getCapability(JEIBlockingCapability.JEI_BLOCKER!!).ifPresent {
                it.setUseJEI(canUse)
            }
        }
    }
}