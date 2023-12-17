package com.algorithmlx.tenytech.network

import com.algorithmlx.tenytech.ModId
import com.algorithmlx.tenytech.api.network.IPacket
import com.algorithmlx.tenytech.network.client.S2CMapBlockerPacket as S2CMap
import com.algorithmlx.tenytech.network.server.C2SMapBlockerPacket as C2SMap
import net.minecraft.entity.player.ServerPlayerEntity
import net.minecraft.network.PacketBuffer as Buf
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.network.NetworkEvent
import net.minecraftforge.fml.network.NetworkDirection as PacketOn
import net.minecraftforge.fml.network.NetworkRegistry
import net.minecraftforge.fml.network.PacketDistributor.PacketTarget
import net.minecraftforge.fml.network.simple.SimpleChannel
import java.util.function.BiConsumer
import java.util.function.Function
import java.util.function.Supplier
import kotlin.reflect.KClass

object TTPackets {
    private const val VER = "1"
    private var identifier = 0

    private lateinit var network: SimpleChannel

    fun startMessaging() {
        val localNetwork = NetworkRegistry.ChannelBuilder.named(ResourceLocation(ModId, "main"))
            .networkProtocolVersion(::VER)
            .clientAcceptedVersions(VER::equals)
            .serverAcceptedVersions(VER::equals)
            .simpleChannel()

        this.network = localNetwork

        register(C2SMap::class, C2SMap::encode, ::C2SMap, C2SMap::handle)
        register(S2CMap::class, S2CMap::encode, ::S2CMap, S2CMap::handle)

//        localNetwork.messageBuilder(C2SMap::class.java, identifier, PacketOn.PLAY_TO_SERVER)
//            .consumer(C2SMap::handle)
//            .encoder(C2SMap::encode)
//            .decoder(::C2SMap)
//        .add()

//        localNetwork.messageBuilder(S2CMap::class.java, identifier, PacketOn.PLAY_TO_CLIENT)
//            .consumer(S2CMap::handle)
//            .encoder(S2CMap::encode)
//            .decoder(::S2CMap)
//        .add()
    }

    private fun <T: IPacket> register(clazz: KClass<T>, encoder: BiConsumer<T, Buf>, decoder: Function<Buf, T>, consumer: BiConsumer<T, Supplier<NetworkEvent.Context>>) {
        network.registerMessage(identifier++, clazz.java, encoder, decoder, consumer)
    }

    @JvmStatic
    fun <MSG> ServerPlayerEntity.sendToPlayer(obj: MSG) {
        network.sendTo(obj, this.connection.connection, PacketOn.PLAY_TO_CLIENT)
    }

    @JvmStatic
    fun <MSG> sendToServer(obj: MSG) {
        network.sendToServer(obj)
    }

    @JvmStatic
    fun <MSG> send(target: PacketTarget, message: MSG) {
        network.send(target, message)
    }
}