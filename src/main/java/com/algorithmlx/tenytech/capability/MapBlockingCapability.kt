package com.algorithmlx.tenytech.capability

import com.algorithmlx.tenytech.capability.core.IMapBlocker
import com.algorithmlx.tenytech.capability.impl.MapBlocker
import net.minecraft.nbt.ByteNBT
import net.minecraft.nbt.INBT
import net.minecraft.util.Direction
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.Capability.IStorage
import net.minecraftforge.common.capabilities.CapabilityInject
import net.minecraftforge.common.capabilities.CapabilityManager

object MapBlockingCapability {
    @CapabilityInject(IMapBlocker::class)
    var MAP_BLOCKER: Capability<IMapBlocker>? = null

    @JvmStatic
    fun register() {
        CapabilityManager.INSTANCE.register(IMapBlocker::class.java, object : IStorage<IMapBlocker> {
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
}