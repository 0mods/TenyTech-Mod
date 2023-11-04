package com.algorithmlx.tenytech.api

import net.minecraft.block.AbstractBlock.Properties
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.material.Material
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.ItemUseContext
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.ActionResultType
import net.minecraft.util.Hand
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.BlockRayTraceResult
import net.minecraft.util.text.ITextComponent
import net.minecraft.world.IBlockReader
import net.minecraft.world.World

open class BlockBuilder private constructor() {
    private lateinit var useCtx: ItemUseContext.() -> ActionResultType
    private var properties: Properties = Properties.of(Material.STONE)
    private lateinit var tooltips: (pStack: ItemStack, pLevel: IBlockReader?, pTooltip: MutableList<ITextComponent>, pFlag: ITooltipFlag) -> Unit
    private lateinit var blockEntity: (BlockState, IBlockReader) -> TileEntity
    private lateinit var displayName: ITextComponent

    fun tooltip(tooltip: (pStack: ItemStack, pLevel: IBlockReader?, pTooltip: MutableList<ITextComponent>, pFlag: ITooltipFlag) -> Unit): BlockBuilder {
        this.tooltips = tooltip
        return this
    }

    fun tooltip(vararg tooltip: ITextComponent): BlockBuilder = this.tooltip { _, _, texts, _ -> texts.addAll(tooltip) }

    fun name(name: ITextComponent): BlockBuilder {
        this.displayName = name
        return this
    }

    fun use(onUse: ItemUseContext.() -> ActionResultType): BlockBuilder {
        this.useCtx = onUse
        return this
    }

    fun properties(properties: Properties): BlockBuilder {
        this.properties = properties
        return this
    }

    fun entity(blockEntity: (BlockState, IBlockReader) -> TileEntity): BlockBuilder {
        this.blockEntity = blockEntity
        return this
    }

    fun build(): Block = object : Block(this.properties) {
        @Deprecated("Deprecated in Java")
        override fun use(
            pState: BlockState,
            pLevel: World,
            pPos: BlockPos,
            pPlayer: PlayerEntity,
            pHand: Hand,
            pHit: BlockRayTraceResult
        ): ActionResultType {
            val itemUseContext = ItemUseContext(pLevel, pPlayer, pHand, pPlayer.getItemInHand(pHand), pHit)
            return if (::useCtx.isInitialized) useCtx.invoke(itemUseContext) else super.use(pState, pLevel, pPos, pPlayer, pHand, pHit)
        }

        override fun getDescriptionId(): String = if (::displayName.isInitialized) displayName.toString() else super.getDescriptionId()

        override fun appendHoverText(
            pStack: ItemStack,
            pLevel: IBlockReader?,
            pTooltip: MutableList<ITextComponent>,
            pFlag: ITooltipFlag
        ) {
            if (::tooltips.isInitialized)
                tooltips.invoke(pStack, pLevel, pTooltip, pFlag)
        }

        override fun hasTileEntity(state: BlockState?): Boolean = ::blockEntity.isInitialized

        override fun createTileEntity(state: BlockState, world: IBlockReader): TileEntity? =
            if (::blockEntity.isInitialized) blockEntity.invoke(state, world) else null
    }

    companion object {
        @JvmStatic
        fun get(): BlockBuilder = BlockBuilder()
    }
}