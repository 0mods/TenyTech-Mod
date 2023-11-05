package com.algorithmlx.tenytech.compact.curios

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.util.Direction
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.ICapabilityProvider
import net.minecraftforge.common.util.LazyOptional
import net.minecraftforge.common.util.NonNullSupplier
import net.minecraftforge.fml.InterModComms
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent
import top.theillusivec4.curios.Curios
import top.theillusivec4.curios.api.CuriosApi
import top.theillusivec4.curios.api.CuriosCapability
import top.theillusivec4.curios.api.SlotTypeMessage
import top.theillusivec4.curios.api.SlotTypePreset
import top.theillusivec4.curios.api.type.capability.ICurio

object CuriosLoader {
    fun PlayerEntity.hasCurioItem(stack: ItemStack): Boolean = CuriosApi.getCuriosHelper().findFirstCurio(this, stack.item).isPresent
    fun flyRing(stack: ItemStack) = inLine { FlyCurios(stack) }

    fun registerMessages(event: InterModEnqueueEvent) {
        InterModComms.sendTo(Curios.MODID, SlotTypeMessage.REGISTER_TYPE, SlotTypePreset.RING.messageBuilder::build)
    }

    private fun curiosCapabilityProvider(lazy: LazyOptional<ICurio>): ICapabilityProvider = object : ICapabilityProvider {
        override fun <T> getCapability(cap: Capability<T>, side: Direction?): LazyOptional<T> =
            CuriosCapability.ITEM.orEmpty(cap, lazy)
    }

    private fun <T: ICurio> inlineSupplier(supplier: NonNullSupplier<T>) = LazyOptional.of(supplier)

    private fun inLine(sup: NonNullSupplier<ICurio>) = curiosCapabilityProvider(inlineSupplier(sup))
}