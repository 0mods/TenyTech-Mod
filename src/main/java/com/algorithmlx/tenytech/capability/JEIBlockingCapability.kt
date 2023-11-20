package com.algorithmlx.tenytech.capability

import com.algorithmlx.tenytech.capability.core.IJEIBlocker
import com.algorithmlx.tenytech.capability.impl.JEIBlocker
import net.minecraft.nbt.ByteNBT
import net.minecraft.nbt.INBT
import net.minecraft.util.Direction
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.Capability.IStorage
import net.minecraftforge.common.capabilities.CapabilityInject
import net.minecraftforge.common.capabilities.CapabilityManager

object JEIBlockingCapability {
    @CapabilityInject(IJEIBlocker::class)
    var JEI_BLOCKER: Capability<IJEIBlocker>? = null

    @JvmStatic
    fun register() {
        CapabilityManager.INSTANCE.register(IJEIBlocker::class.java, object : IStorage<IJEIBlocker> {
            override fun writeNBT(
                capability: Capability<IJEIBlocker>?,
                instance: IJEIBlocker,
                side: Direction?
            ): INBT? = ByteNBT.valueOf(instance.getUseJEI())

            override fun readNBT(
                capability: Capability<IJEIBlocker>?,
                instance: IJEIBlocker,
                side: Direction?,
                nbt: INBT
            ) {
                instance.setUseJEI((nbt as ByteNBT).asByte != (0).toByte())
            }
        }, ::JEIBlocker)
    }
}