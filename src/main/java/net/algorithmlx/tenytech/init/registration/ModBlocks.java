package net.algorithmlx.tenytech.init.registration;

import com.blakebr0.cucumber.block.BaseBlock;
import com.blakebr0.cucumber.item.BaseBlockItem;
import com.blakebr0.mysticalagriculture.registry.CropRegistry;
import net.algorithmlx.tenytech.TenyTech;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public class ModBlocks {
    public static final Map<RegistryObject<Block>, Supplier<Block>> ENTRIES = new LinkedHashMap<>();

    public static final RegistryObject<Block> QofEB = register("quantum_entanglement_block", () -> new BaseBlock(Material.METAL, SoundType.STONE, 4.0F, 6.0F, ToolType.PICKAXE));

    @SubscribeEvent
    public void onRegisterBlocks(RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> registry = event.getRegistry();

        ENTRIES.forEach((reg, block) -> {
            registry.register(block.get());
            reg.updateReference(registry);
        });

        CropRegistry.getInstance().setAllowRegistration(true);
        CropRegistry.getInstance().onRegisterBlocks(registry);
        CropRegistry.getInstance().setAllowRegistration(false);
    }

    private static RegistryObject<Block> register(String name, Supplier<Block> block) {
        return register(name, block, b -> () -> new BaseBlockItem(b.get(), p -> p.tab(TenyTech.TT_IG)));
    }

    private static RegistryObject<Block> register(String name, Supplier<Block> block, Function<RegistryObject<Block>, Supplier<? extends BlockItem>> item) {
        ResourceLocation loc = new ResourceLocation(TenyTech.ModId, name);
        RegistryObject<Block> reg = RegistryObject.of(loc, ForgeRegistries.BLOCKS);
        ENTRIES.put(reg, () -> block.get().setRegistryName(loc));
        ModItems.BLOCK_ENTRIES.add(() -> item.apply(reg).get().setRegistryName(loc));
        return reg;
    }

    public static RegistryObject<Block> registerNoItem(String name, Supplier<Block> block) {
        ResourceLocation loc = new ResourceLocation(TenyTech.ModId, name);
        RegistryObject<Block> reg = RegistryObject.of(loc, ForgeRegistries.BLOCKS);
        ENTRIES.put(reg, () -> block.get().setRegistryName(loc));
        return reg;
    }

}