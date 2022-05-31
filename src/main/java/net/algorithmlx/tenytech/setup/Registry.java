package net.algorithmlx.tenytech.setup;

import com.blakebr0.mysticalagriculture.item.EssenceItem;

import net.algorithmlx.tenytech.block.QuantumEntanglementBlock;
import net.algorithmlx.tenytech.integrated.ModCropTiers;
import net.algorithmlx.tenytech.item.*;
import net.algorithmlx.tenytech.liquid.LiquidPlasticAlloy;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Registry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Constant.ModId);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Constant.ModId);
    public static final DeferredRegister<Fluid> FLUID = DeferredRegister.create(ForgeRegistries.FLUIDS, Constant.ModId);

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

    //fluid
    public static final RegistryObject<FlowingFluid> LIQUID_PLASTIC_ALLOY_FLOWING = FLUID.register("liquid_plastic_alloy_flowing", 
        ()-> new LiquidPlasticAlloy.Source());
    public static final RegistryObject<Block> LIQUID_PLASTIC_ALLOY_BLOCK = BLOCKS.register("liquid_plastic_alloy", 
        ()-> new FlowingFluidBlock(Registry.LIQUID_PLASTIC_ALLOY_FLOWING, AbstractBlock.Properties.copy(Blocks.LAVA)));
    public static final RegistryObject<FlowingFluid> LIQUID_PLASTIC_ALLOY_SOURCE = FLUID.register("liquid_plastic_alloy", 
        ()-> new LiquidPlasticAlloy.Source());
    public static final RegistryObject<Item> LIQUID_PLASTIC_ALLOY_BUCKET = ITEMS.register("liquid_plastic_alloy_bucket", 
        ()-> new BucketItem(Registry.LIQUID_PLASTIC_ALLOY_SOURCE, 
            new Item.Properties().tab(ModSetup.TENY_TECH).craftRemainder(Items.BUCKET).stacksTo(1)));
}
