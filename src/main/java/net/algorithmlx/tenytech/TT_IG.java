package net.algorithmlx.tenytech;

import net.algorithmlx.tenytech.registry.Registry;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class TT_IG extends ItemGroup {
    public TT_IG(String name)
    {
        super(name);
    }

    @Nonnull
    public ItemStack createIcon()
    {
        return new ItemStack(Registry.AUGMENTATION_FLY.get());
    }

    @Override
    public boolean hasSearchBar()
    {
        return false;
    }
}
