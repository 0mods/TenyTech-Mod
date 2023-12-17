package com.algorithmlx.tenytech.capability

import com.algorithmlx.tenytech.LOGGER
import com.algorithmlx.tenytech.capability.core.IMapBlocker
import com.algorithmlx.tenytech.capability.impl.MapBlocker
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.nbt.ByteNBT
import net.minecraft.nbt.INBT
import net.minecraft.util.Direction
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.Capability.IStorage
import net.minecraftforge.common.capabilities.CapabilityInject
import net.minecraftforge.common.capabilities.CapabilityManager
import net.minecraftforge.common.capabilities.ICapabilitySerializable
import net.minecraftforge.common.util.LazyOptional

object MapBlockingCapability {
    @JvmField
    @CapabilityInject(IMapBlocker::class)
    var MAP_BLOCKER: Capability<IMapBlocker>?

    @JvmStatic
    fun register() {
        CapabilityManager.INSTANCE.register(IMapBlocker::class.java, object : IStorage<IMapBlocker> {
            init {
                LOGGER.debug("MAP_BLOCK Registered!")
            }

            override fun writeNBT(
                capability: Capability<IMapBlocker>?,
                instance: IMapBlocker,
                side: Direction?
            ): INBT? = ByteNBT.valueOf(instance.getUseMap())

            override fun readNBT(
                capability: Capability<IMapBlocker>?,
                instance: IMapBlocker,
                side: Direction?,
                nbt: INBT
            ) {
                instance.setUseMap((nbt as ByteNBT).asByte != (0).toByte())
            }
        }, ::MapBlocker)
    }

    init {
        MAP_BLOCKER = null
    }

    class Provider: ICapabilitySerializable<INBT> {
        val lazOpt: LazyOptional<IMapBlocker>
        val cap: IMapBlocker

        init {
            LOGGER.debug("MAP_BLOCK Provider loaded!")

            cap = MapBlocker()
            lazOpt = LazyOptional.of(::cap)
        }

        override fun <T : Any?> getCapability(cap: Capability<T>, side: Direction?): LazyOptional<T> =
            MAP_BLOCKER!!.orEmpty(cap, this.lazOpt)

        override fun serializeNBT(): INBT? = MAP_BLOCKER!!.writeNBT(this.cap, null)

        override fun deserializeNBT(nbt: INBT?) {
            MAP_BLOCKER!!.readNBT(this.cap, null, nbt)
        }
    }
}