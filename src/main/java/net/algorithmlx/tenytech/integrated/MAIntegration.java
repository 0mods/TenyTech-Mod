package net.algorithmlx.tenytech.integrated;

import com.blakebr0.mysticalagradditions.config.ModConfigs;
import com.blakebr0.mysticalagradditions.init.ModBlocks;
import com.blakebr0.mysticalagriculture.api.IMysticalAgriculturePlugin;
import com.blakebr0.mysticalagriculture.api.MysticalAgriculturePlugin;
import com.blakebr0.mysticalagriculture.api.crop.Crop;
import com.blakebr0.mysticalagriculture.api.crop.CropTier;
import com.blakebr0.mysticalagriculture.api.crop.CropType;
import com.blakebr0.mysticalagriculture.api.lib.PluginConfig;
import com.blakebr0.mysticalagriculture.api.registry.ICropRegistry;

import net.algorithmlx.tenytech.setup.Constant;
import net.algorithmlx.tenytech.setup.Registry;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.util.ResourceLocation;

@MysticalAgriculturePlugin
public final class MAIntegration implements IMysticalAgriculturePlugin {
    public static final Crop CRUXED_NETHER_STAR = cropReg("cruxed_nether_star", ModCropTiers.CROP_TIER_7);
    public static final Crop CRUXED_DRAGON_EGG = cropReg("cruxed_dragon_egg", ModCropTiers.CROP_TIER_7);
    public static final Crop CRUXED_NITRO_CRYSTAL = cropReg("nitro_crystal", ModCropTiers.CROP_TIER_7);
    public static final Crop DRAGON_BREATH = cropReg("dragon_breath", ModCropTiers.CROP_TIER_7);
    @Override
    public void configure(PluginConfig config) {
        config.setModId(Constant.ModId);
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
        registry.register(DRAGON_BREATH);
    }

    public static void onCommonSetup() {
        ModCropTiers.CROP_TIER_7.setFarmland(() -> (FarmlandBlock) ModBlocks.INSANIUM_FARMLAND.get())
                .setEssence(Registry.ALGENIUM_ESSENCE)
                .setFertilizable(ModConfigs.FERTILIZABLE_CROPS.get())
                .setSecondarySeedDrop(false);
    }


    private static Crop cropReg(String name, CropTier tier) {
        return new Crop(new ResourceLocation(Constant.ModId, name), tier, CropType.RESOURCE, null);
    }
}
