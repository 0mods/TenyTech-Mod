package com.algorithmlx.tenytech.init

import com.algorithmlx.tenytech.ModId
import com.algorithmlx.tenytech.api.BlockBuilder
import com.algorithmlx.tenytech.api.TranslationBuilder
import com.algorithmlx.tenytech.compact.agriculture.item.MAIntegratedObjects
import com.algorithmlx.tenytech.item.*
import net.minecraft.block.AbstractBlock
import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraftforge.common.ToolType
import net.minecraftforge.fml.ModList
import net.minecraftforge.fml.RegistryObject
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries

object Register {
    val items = DeferredRegister.create(ForgeRegistries.ITEMS, ModId)
    val blocks = DeferredRegister.create(ForgeRegistries.BLOCKS, ModId)

    val tab = object : ItemGroup("$ModId.tab") {
        override fun makeIcon(): ItemStack = ItemStack(qeb.get())
    }

    val flyRing: RegistryObject<FlyRing> = items.register("fly_ring", ::FlyRing)

    val qeb: RegistryObject<Block> = block(
        "quantum_entanglement_block",
        BlockBuilder.get()
            .tooltip(TranslationBuilder.msg("qeb").build())
        ::build
    )

    val algeniumBlock = block(
        "algenium_block",
        BlockBuilder.get()
            .properties(
                AbstractBlock.Properties
                    .of(Material.METAL)
                    .strength(10f, 10f)
                    .requiresCorrectToolForDrops()
                    .harvestTool(ToolType.PICKAXE)
            )::build
    )

    val augmentOfFly = items.register("augment_fly", ::FlyAugment)
    val doubleBlackIron = items.register("double_black_iron_ingot") {
        Item(Item.Properties().tab(tab))
    }
    val dragonBreathChunk = items.register("dragon_breath_chunk") {
        Item(Item.Properties().tab(tab))
    }
    val algeniumEssence = items.register("algenium_essence") {
        if (ModList.get().isLoaded("mysticalagriculture")) {
            MAIntegratedObjects.algeniumEssence
        } else Item(Item.Properties().tab(tab))
    }

    @JvmStatic
    fun init() {
        val bus = FMLJavaModLoadingContext.get().modEventBus
        items.register(bus)
        blocks.register(bus)
    }

    private fun <T: Block> block(id: String, block: () -> T): RegistryObject<T> = this.block(id, block) {
        BlockItem(it, Item.Properties().tab(tab))
    }

    private fun <T: Block> block(id: String, block: () -> T, item: (T) -> Item): RegistryObject<T> {
        val reg = this.blocks.register(id, block)
        this.items.register(id) { item.invoke(reg.get()) }
        return reg
    }
}