package net.algorithmlx.tenytech.api;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;

public class TabMethod extends ItemGroup {
    private final ItemStack itemIcon;

    private TabMethod(String label, ItemStack icon, ResourceLocation bgTexture) {
        super(label);
        this.itemIcon = icon;

        if (bgTexture != null)
            this.setBackgroundImage(bgTexture);
        else
            this.setBackgroundImage(new ResourceLocation("minecraft", "textures/gui/container/creative_inventory/tab_items.png"));
    }

    //Why not ItemLike? :(
    private TabMethod(String name, IItemProvider icon, ResourceLocation bgTexture) {
        this(name, new ItemStack(icon), bgTexture);
    }

    @Override
    public ItemStack makeIcon() {
        return itemIcon;
    }

    public static TabMethod create(String modId, String name0, ItemStack icon, ResourceLocation bgTexture) {
        return new TabMethod(modId + "." + name0, icon, bgTexture);
    }

    public static TabMethod create(String modId, String name0, IItemProvider icon, ResourceLocation bgTexture) {
        return new TabMethod(modId + "." + name0, icon, bgTexture);
    }

    public static TabMethod create(String modId, String name0, ItemStack icon) {
        return new TabMethod(modId + "." + name0, icon, null);
    }

    public static TabMethod create(String modId, String name0, IItemProvider icon) {
        return new TabMethod(modId + "." + name0, icon, null);
    }

    public static TabMethod create(String name, ItemStack icon, ResourceLocation bgTexture) {
        return new TabMethod(name, icon, bgTexture);
    }

    public static TabMethod create(String name, IItemProvider icon, ResourceLocation bgTexture) {
        return new TabMethod(name, icon, bgTexture);
    }

    public static TabMethod create(String name, ItemStack icon) {
        return new TabMethod(name, icon, null);
    }

    public static TabMethod create(String name, IItemProvider icon) {
        return new TabMethod(name, icon, null);
    }
}
