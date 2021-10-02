package net.algorithmlx.tenytech.registry;

import net.algorithmlx.tenytech.items.*;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static net.algorithmlx.tenytech.TenyTech.ModId;

public class Registry {
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ModId);

    public static void init() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static final RegistryObject<AugmentationFly> AUGMENTATION_FLY = ITEMS.register("augment_fly", AugmentationFly::new);
}
