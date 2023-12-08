package com.algorithmlx.tenytech.item

import com.algorithmlx.tenytech.api.builder.TranslationBuilder
import com.algorithmlx.tenytech.api.hours
import com.algorithmlx.tenytech.compact.curios.CuriosLoader
import com.algorithmlx.tenytech.init.Register.tab
import com.algorithmlx.tenytech.init.TTClientStartup
import com.algorithmlx.tenytech.network.TTPackets
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.entity.Entity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.nbt.CompoundNBT
import net.minecraft.util.text.ITextComponent
import net.minecraft.util.text.TextFormatting
import net.minecraft.world.World
import net.minecraftforge.common.capabilities.ICapabilityProvider
import net.minecraftforge.fml.ModList
import net.minecraftforge.fml.loading.FMLLoader
import net.minecraftforge.fml.network.PacketDistributor
import top.theillusivec4.curios.common.network.server.SPacketBreak
import java.util.function.Consumer

class FlyRing(properties: Properties, val isDamageableRing: Boolean = true, ringDamage: Int = 10.hours): Item(properties) {
    var repairTick = 0

    constructor(): this(Properties().tab(tab).fireResistant().stacksTo(1))

    init {
        if (isDamageableRing) {
            properties.durability(ringDamage)
            if (FMLLoader.getDist().isClient)
                TTClientStartup.flyRingRenderer(this)
        }
    }

    override fun appendHoverText(
        pStack: ItemStack,
        pLevel: World?,
        pTooltip: MutableList<ITextComponent>,
        pFlag: ITooltipFlag
    ) {
        if (Screen.hasShiftDown()) {
            pTooltip.add(TranslationBuilder.msg("gives_a_fly").build)

            if (this.isDamageableRing) {
                pTooltip.add(TranslationBuilder.msg("has_durability").arg((this.defaultInstance.maxDamage / (20 * 60 * 60)).toInt()).build)
                pTooltip.add(TranslationBuilder.msg("repairs_at_time").build)
            }
        } else pTooltip.add(TranslationBuilder.msg("shift").format(TextFormatting.GRAY, TextFormatting.ITALIC).build)
    }

    override fun initCapabilities(stack: ItemStack, nbt: CompoundNBT?): ICapabilityProvider? {
        return if (ModList.get().isLoaded("curios")) CuriosLoader.flyRing(stack) else super.initCapabilities(stack, nbt)
    }

    override fun inventoryTick(
        pStack: ItemStack,
        pLevel: World,
        pEntity: Entity,
        pItemSlot: Int,
        pIsSelected: Boolean
    ) {
        if (ModList.get().isLoaded("curios")) return
        if (pEntity is PlayerEntity) {
            makeOrBreakFly(pEntity, pEntity.level, pStack, pEntity.inventory.contains(pStack)) {}
        }
    }

    companion object {
        @JvmStatic
        fun makeOrBreakFly(player: PlayerEntity, level: World, stack: ItemStack, itemIsPresent: Boolean, consumer: Consumer<PlayerEntity>) {
            if (player.isCreative && player.isSpectator) return
            if (itemIsPresent) {
                val item = stack.item

                if (item is FlyRing) {
                    if (item.isDamageableRing) {
                        if (stack.damageValue < stack.maxDamage - 1) {
                            if (!level.isClientSide && player.abilities.flying)
                                stack.hurtAndBreak(1, player, consumer)

                            makeOrBreakFly(player)
                        } else breakFly(player)

                        if (stack.isDamaged) {
                            if (!player.abilities.flying) item.repairTick++
                            else item.repairTick = 0

                            if (item.repairTick >= 1200) {
                                var repaired = stack.damageValue - 1000

                                if (repaired < 0) repaired = 0

                                if (!level.isClientSide)
                                    stack.damageValue = repaired

                                item.repairTick = 0
                            }
                        }
                    } else makeOrBreakFly(player)
                }
            } else {
                breakFly(player)
            }
        }

        @JvmStatic
        fun makeOrBreakFly(player: PlayerEntity) {
            if (!player.isCreative && !player.isSpectator) {
                player.abilities.mayfly = true
                player.onUpdateAbilities()
            }
        }

        @JvmStatic
        fun breakFly(player: PlayerEntity) {
            if (!player.isCreative && !player.isSpectator) {
                player.abilities.flying = false
                player.abilities.mayfly = false
                player.onUpdateAbilities()
            }
        }
    }
}