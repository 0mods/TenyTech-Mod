package net.algorithmlx.tenytech.registry;

import com.blakebr0.cucumber.item.BaseItem;
import com.blakebr0.mysticalagriculture.item.EssenceItem;
import com.blakebr0.mysticalagriculture.registry.AugmentRegistry;
import com.blakebr0.mysticalagriculture.registry.CropRegistry;
import net.algorithmlx.tenytech.TenyTech;
import net.algorithmlx.tenytech.lib.ModCorePlugin;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static net.algorithmlx.tenytech.TenyTech.TT_IG;

public class ModItems {
    public static final List<Supplier<Item>> BLOCK_ENTRIES = new ArrayList<>();
    public static final Map<RegistryObject<Item>, Supplier<Item>> ENTRIES = new LinkedHashMap<>();
    public static final Map<RegistryObject<Item>, Supplier<Item>> GEAR_ENTRIES = new LinkedHashMap<>();

    public static final RegistryObject<Item> ALGENIUM_ESSENCE = register("algenium_essence", () -> new EssenceItem(ModCorePlugin.CROP_TIER_7, p -> p.group(TT_IG)));
    public static final RegistryObject<Item> DRAGON_BREATH_CHUNK = register("dragon_breath_chunk");

    @SubscribeEvent
    public void onRegisterItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();

        BLOCK_ENTRIES.stream().map(Supplier::get).forEach(registry::register);
        ENTRIES.forEach((reg, item) -> {
            registry.register(item.get());
            reg.updateReference(registry);
        });

        CropRegistry.getInstance().onRegisterItems(registry);

        GEAR_ENTRIES.forEach((reg, item) -> {
            registry.register(item.get());
            reg.updateReference(registry);
        });
    }

    private static RegistryObject<Item> register(String name) {
        return register(name, () -> new BaseItem(p -> p.group(TT_IG)));
    }

    private static RegistryObject<Item> register(String name, Supplier<Item> item) {
        ResourceLocation loc = new ResourceLocation(TenyTech.ModId, name);
        RegistryObject<Item> reg = RegistryObject.of(loc, ForgeRegistries.ITEMS);
        ENTRIES.put(reg, () -> item.get().setRegistryName(loc));
        return reg;
    }

    private static RegistryObject<Item> registerGear(String name, Supplier<? extends Item> item) {
        ResourceLocation loc = new ResourceLocation(TenyTech.ModId, name);
        RegistryObject<Item> reg = RegistryObject.of(loc, ForgeRegistries.ITEMS);
        GEAR_ENTRIES.put(reg, () -> item.get().setRegistryName(loc));
        return reg;
    }
}
