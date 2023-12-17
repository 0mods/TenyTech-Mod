package com.algorithmlx.tenytech.compact.jm

import com.algorithmlx.tenytech.api.builder.TranslationBuilder
import com.algorithmlx.tenytech.api.forgeBus
import com.algorithmlx.tenytech.api.modBus
import com.algorithmlx.tenytech.client.ClientMapBlockPacket
import journeymap.client.ui.fullscreen.Fullscreen
import journeymap.client.ui.waypoint.WaypointEditor
import journeymap.client.ui.waypoint.WaypointManager
import net.minecraft.client.Minecraft
import net.minecraftforge.client.event.GuiOpenEvent
import net.minecraftforge.event.TickEvent
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent

object JMapController {
    private lateinit var permsHandler: JMapPermsHandler

    fun init() {
        modBus.register(::initEvt)
        forgeBus.register(::onOpen)
        forgeBus.register(::onTick)
    }

    fun initEvt(evt: FMLCommonSetupEvent) {
        permsHandler = JMapPermsHandler()
    }

    fun onOpen(evt: GuiOpenEvent) {
        val player = Minecraft.getInstance().player ?: return
        if (!ClientMapBlockPacket.canUse && (evt.gui is WaypointEditor || evt.gui is WaypointManager || evt.gui is Fullscreen)) {
            player.displayClientMessage(TranslationBuilder.msg("map_is_blocked").build, true)
            evt.isCanceled = true
        }
    }

    fun onTick(evt: TickEvent.PlayerTickEvent) {
        if (evt.player.level.isClientSide && evt.player.level.gameTime % 5 == 0L) {
            if (!ClientMapBlockPacket.canUse) permsHandler.disableAll()
        }
    }
}