package net.algorithmlx.tenytech.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;
import java.util.List;

import static net.algorithmlx.tenytech.TenyTech.ModId;

public class QuantumEntanglementBlock extends Block {
    public QuantumEntanglementBlock() {
        super(Properties.of(Material.STONE).strength(5f, 5f).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().lightLevel(s -> 1));
    }

    @Override
    public void appendHoverText(ItemStack p_190948_1_, @Nullable IBlockReader p_190948_2_, List<ITextComponent> p_190948_3_, ITooltipFlag p_190948_4_) {
        p_190948_3_.add(new TranslationTextComponent("msg."+ModId+".quantum_entanglement_block"));
        super.appendHoverText(p_190948_1_, p_190948_2_, p_190948_3_, p_190948_4_);
    }
}
