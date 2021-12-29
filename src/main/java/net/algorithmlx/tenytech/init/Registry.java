package net.algorithmlx.tenytech.init;

import com.blakebr0.mysticalagriculture.api.crop.Crop;
import com.blakebr0.mysticalagriculture.api.crop.CropType;
import com.blakebr0.mysticalagriculture.api.lib.LazyIngredient;
import com.blakebr0.mysticalagriculture.item.EssenceItem;
import net.algorithmlx.tenytech.TenyTech;
import net.algorithmlx.tenytech.block.AlgeniumEssenceBlock;
import net.algorithmlx.tenytech.block.QuantumEntanglementBlock;
import net.algorithmlx.tenytech.item.*;
import net.algorithmlx.tenytech.lib.ModCorePlugin;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static net.algorithmlx.tenytech.TenyTech.ModId;

public class Registry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ModId);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ModId);

    public static void init() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static final RegistryObject<QuantumEntanglementBlock> QUANTUM_ENTANGLEMENT_BLOCK = BLOCKS.register("quantum_entanglement_block", QuantumEntanglementBlock::new);
    public static final RegistryObject<AlgeniumEssenceBlock> ALGENIUM_BLOCK = BLOCKS.register("algenium_block", AlgeniumEssenceBlock::new);

    public static final RegistryObject<AugmentationFly> AUGMENTATION_FLY = ITEMS.register("augment_fly", AugmentationFly::new);
    public static final RegistryObject<DoubleBlackIronIngot> DOUBLE_BLACK_IRON_INGOT = ITEMS.register("double_black_iron_ingot", DoubleBlackIronIngot::new);
    public static final RegistryObject<DragonBreathChunk> DRAGON_BREATH_CHUNK = ITEMS.register("dragon_breath_chunk", DragonBreathChunk::new);

    public static final RegistryObject<AlgeniumEssence> ALGENIUM_ESSENCE = ITEMS.register("algenium_essence", AlgeniumEssence::new);
}
