package com.algorithmlx.tenytech.init

import com.algorithmlx.tenytech.LOGGER
import com.algorithmlx.tenytech.ModId
import com.algorithmlx.tenytech.api.forgeBus as fb
import com.algorithmlx.tenytech.api.helper.RecipeHelper
import com.algorithmlx.tenytech.api.isLoaded
import com.algorithmlx.tenytech.api.modBus as mb
import com.algorithmlx.tenytech.capability.JEIBlockingCapability
import com.algorithmlx.tenytech.capability.MapBlockingCapability
import com.algorithmlx.tenytech.compact.curios.CuriosLoader
import com.algorithmlx.tenytech.compact.jm.JMapController
import com.algorithmlx.tenytech.network.TTPackets
import com.algorithmlx.tenytech.network.TTPackets.sendToPlayer
import com.algorithmlx.tenytech.network.client.S2CMapBlockerPacket
import net.minecraft.entity.Entity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.ServerPlayerEntity
import net.minecraft.util.ResourceLocation
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.event.AttachCapabilitiesEvent
import net.minecraftforge.event.TickEvent.PlayerTickEvent
import net.minecraftforge.event.entity.EntityJoinWorldEvent
import net.minecraftforge.event.entity.player.PlayerEvent
import net.minecraftforge.fml.ModLoadingContext
import net.minecraftforge.fml.config.ModConfig
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent

object TTStartup {
    @JvmStatic
    fun init() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, TTConfig.spec, "tenytech/common.toml")

        TTPackets.startMessaging()
        Register.init()

        if ("curios".isLoaded()) mb.addListener(CuriosLoader::registerMessages)
        if ("journeymap".isLoaded()) JMapController.init()

        fb.addListener(this::playerTickEvent)
        mb.addListener(this::commonStartup)
        fb.addListener(this::playerConnectToWorld)
        fb.addListener(this::playerClone)
        fb.addGenericListener(Entity::class.java, this::attachCap)
    }

    private fun playerTickEvent(evt: PlayerTickEvent) {
        val player = evt.player
        val level = player.level

        if (!level.isClientSide) {
            if (player is ServerPlayerEntity) {
                player.getCapability(MapBlockingCapability.MAP_BLOCKER!!)
                    .ifPresent { player.sendToPlayer(S2CMapBlockerPacket(it.getUseMap())) }
            }
        }
    }

    private fun playerConnectToWorld(evt: EntityJoinWorldEvent) {
        val player = evt.entity
        val level = evt.world

        if (!level.isClientSide) {
            if (player is ServerPlayerEntity) {
                player.getCapability(MapBlockingCapability.MAP_BLOCKER!!)
                    .ifPresent { player.sendToPlayer(S2CMapBlockerPacket(it.getUseMap())) }
            }
        }
    }

    private fun playerClone(evt: PlayerEvent.Clone) {
        if (evt.isWasDeath) {
            evt.original.getCapability(MapBlockingCapability.MAP_BLOCKER!!).ifPresent { old ->
                evt.original.getCapability(MapBlockingCapability.MAP_BLOCKER!!).ifPresent {
                    it.copyFrom(old)
                }
            }
        }
    }

    private fun commonStartup(evt: FMLCommonSetupEvent) {
        JEIBlockingCapability.register()
        MapBlockingCapability.register()
        MinecraftForge.EVENT_BUS.register(RecipeHelper)
    }

    private fun attachCap(evt: AttachCapabilitiesEvent<Entity>) {
        if (evt.`object` is PlayerEntity) {
            if (!(evt.`object`.getCapability(MapBlockingCapability.MAP_BLOCKER!!).isPresent)) {
                evt.addCapability(ResourceLocation(ModId, "map_block"), MapBlockingCapability.Provider())
                LOGGER.debug("Capability {} is attached to player!", evt.`object`)
            }
        }
    }
}
