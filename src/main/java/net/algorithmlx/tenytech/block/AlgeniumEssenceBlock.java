package net.algorithmlx.tenytech.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class AlgeniumEssenceBlock extends Block {
    public AlgeniumEssenceBlock() {
        super(Properties.of(Material.METAL).strength(10F, 10F).requiresCorrectToolForDrops().harvestTool(ToolType.PICKAXE));
    }
}
