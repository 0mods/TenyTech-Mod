package net.algorithmlx.tenytech.item;

import net.algorithmlx.tenytech.setup.Constant;
import net.algorithmlx.tenytech.setup.ModSetup;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class AugmentationFly extends Item {
    public AugmentationFly() {
        super(new Item.Properties().tab(ModSetup.TENY_TECH));
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslationTextComponent("msg."+Constant.ModId+".augment_fly"));
    }
}
