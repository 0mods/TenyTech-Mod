package net.algorithmlx.tenytech.item;

import net.algorithmlx.tenytech.TenyTech;
import net.algorithmlx.tenytech.init.ModSetup;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

import static net.algorithmlx.tenytech.TenyTech.ModId;


public class AugmentationFly extends Item {

    public AugmentationFly() {
        super(new Item.Properties().tab(ModSetup.TENY_TECH));
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslationTextComponent("msg."+ModId+".augment_fly"));
    }

}
