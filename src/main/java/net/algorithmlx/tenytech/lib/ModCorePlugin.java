package net.algorithmlx.tenytech.lib;

import com.blakebr0.mysticalagradditions.config.ModConfigs;
import com.blakebr0.mysticalagradditions.init.ModBlocks;
import com.blakebr0.mysticalagriculture.api.IMysticalAgriculturePlugin;
import com.blakebr0.mysticalagriculture.api.MysticalAgriculturePlugin;
import com.blakebr0.mysticalagriculture.api.crop.Crop;
import com.blakebr0.mysticalagriculture.api.crop.CropTier;
import com.blakebr0.mysticalagriculture.api.crop.CropType;
import com.blakebr0.mysticalagriculture.api.crop.ICrop;
import com.blakebr0.mysticalagriculture.api.lib.LazyIngredient;
import com.blakebr0.mysticalagriculture.api.lib.PluginConfig;
import com.blakebr0.mysticalagriculture.api.registry.ICropRegistry;
import net.algorithmlx.tenytech.init.registration.ModItems;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.ModList;

import java.util.Arrays;

import static com.blakebr0.mysticalagradditions.lib.ModCorePlugin.CROP_TIER_6;
import static net.algorithmlx.tenytech.TenyTech.ModId;

@MysticalAgriculturePlugin
public final class ModCorePlugin implements IMysticalAgriculturePlugin {
    private static final boolean DEBUG = false;

    public static final CropTier CROP_TIER_7 = new CropTier(new ResourceLocation(ModId, "7"), 7, 0x40005E, TextFormatting.BLUE);

    public static final Crop CRUXED_NETHER_STAR = new Crop(new ResourceLocation(ModId, "cruxed_nether_star"), CROP_TIER_7, CropType.RESOURCE, LazyIngredient.item("minecraft:nether_star"));
    public static final Crop CRUXED_DRAGON_EGG = new Crop(new ResourceLocation(ModId, "cruxed_dragon_egg"), CROP_TIER_7, CropType.RESOURCE, LazyIngredient.item("mysticalagradditions:dragon_scale"));
    public static final Crop CRUXED_NITRO_CRYSTAL = new Crop(new ResourceLocation(ModId, "cruxed_nitro_crystal"), CROP_TIER_7, CropType.RESOURCE, LazyIngredient.item("minecraft:stone"));
    public static final Crop DRAGON_BREADTH = new Crop(new ResourceLocation(ModId, "dragon_breath"), CROP_TIER_6, CropType.RESOURCE, LazyIngredient.item("minecraft:dragon_breath"));
    @Override
    public void configure(PluginConfig config) {
        config.setModId(ModId);
        config.disableDynamicSeedCraftingRecipes();
        config.disableDynamicSeedInfusionRecipes();
        config.disableDynamicSeedReprocessingRecipes();
    }
//xd
    @Override
    public void onRegisterCrops(ICropRegistry registry) {
        registry.register(CRUXED_NETHER_STAR);
        registry.register(CRUXED_DRAGON_EGG);
        registry.register(CRUXED_NITRO_CRYSTAL);
        registry.register(DRAGON_BREADTH);
    }

    public static void onCommonSetup() {
        CROP_TIER_7.setFarmland(() -> (FarmlandBlock) ModBlocks.INSANIUM_FARMLAND.get())
                .setEssence(ModItems.ALGENIUM_ESSENCE)
                .setFertilizable(ModConfigs.FERTILIZABLE_CROPS.get())
                .setSecondarySeedDrop(false);
    }

    private static ICrop withRequiredMods(ICrop crop, String... mods) {
        if (DEBUG) return crop;

        boolean enabled = Arrays.stream(mods).anyMatch(ModList.get()::isLoaded);
        return crop.setEnabled(enabled);
    }
}
