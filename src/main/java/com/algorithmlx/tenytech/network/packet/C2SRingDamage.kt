package com.algorithmlx.tenytech.network.packet

import com.algorithmlx.tenytech.init.Register
import net.minecraft.network.PacketBuffer
import net.minecraftforge.fml.network.NetworkEvent
import top.theillusivec4.curios.api.CuriosApi
import java.util.function.Supplier
import kotlin.properties.Delegates

class C2SRingDamage {
    private var id by Delegates.notNull<String>()
    private var index by Delegates.notNull<Int>()

    constructor(id: String, index: Int) {
        this.id = id
        this.index = index
    }

    constructor(buf: PacketBuffer) {
        this.id = buf.readUtf()
        this.index = buf.readInt()
    }

    fun encode(buf: PacketBuffer) {
        buf.writeUtf(this.id)
        buf.writeInt(this.index)
    }

    fun handle(ctxSup: Supplier<NetworkEvent.Context>) {
        val ctx = ctxSup.get()

        ctx.enqueueWork {
            val player = ctx.sender ?: return@enqueueWork
            val stack = CuriosApi.getCuriosHelper().findFirstCurio(player, Register.flyRing.get())

            if (stack.isPresent) {
                if (player.abilities.flying)
                    stack.get().stack.hurtAndBreak(1, player) { CuriosApi.getCuriosHelper().onBrokenCurio(this.id, this.index, player) }
            }
        }
    }

    companion object {
        @JvmStatic
        fun decode(buf: PacketBuffer): C2SRingDamage = C2SRingDamage(buf)
    }
}