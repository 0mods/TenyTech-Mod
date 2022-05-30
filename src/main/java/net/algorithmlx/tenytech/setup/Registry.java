package net.algorithmlx.tenytech.setup;

import com.blakebr0.mysticalagriculture.item.EssenceItem;

import net.algorithmlx.tenytech.block.QuantumEntanglementBlock;
import net.algorithmlx.tenytech.integrated.ModCropTiers;
import net.algorithmlx.tenytech.item.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Registry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Constant.ModId);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Constant.ModId);

    public static void init() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static final RegistryObject<Block> QUANTUM_ENTANGLEMENT_BLOCK = BLOCKS.register("quantum_entanglement_block", QuantumEntanglementBlock::new);
    public static final RegistryObject<Block> ALGENIUM_BLOCK = BLOCKS.register("algenium_block", 
        ()-> new Block(Block.Properties.of(Material.METAL).strength(10F, 10F).requiresCorrectToolForDrops().harvestTool(ToolType.PICKAXE)));

    public static final RegistryObject<AugmentationFly> AUGMENTATION_FLY = ITEMS.register("augment_fly", AugmentationFly::new);
    public static final RegistryObject<Item> DOUBLE_BLACK_IRON_INGOT = ITEMS.register("double_black_iron_ingot", 
        ()-> new Item(new Item.Properties().tab(ModSetup.TENY_TECH)));
    public static final RegistryObject<Item> DRAGON_BREATH_CHUNK = ITEMS.register("dragon_breath_chunk",
        ()-> new Item(new Item.Properties().tab(ModSetup.TENY_TECH)));

    public static final RegistryObject<EssenceItem> ALGENIUM_ESSENCE = ITEMS.register("algenium_essence", 
        ()-> new EssenceItem(ModCropTiers.CROP_TIER_7, p -> p.tab(ModSetup.TENY_TECH)));
}
