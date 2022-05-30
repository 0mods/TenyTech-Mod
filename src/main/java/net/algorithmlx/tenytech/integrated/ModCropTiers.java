package net.algorithmlx.tenytech.integrated;

import com.blakebr0.mysticalagriculture.api.crop.CropTier;

import net.algorithmlx.tenytech.setup.Constant;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

public class ModCropTiers {
    public static final CropTier CROP_TIER_7 = cTierReg(7, 0x40005E, TextFormatting.BLUE);

    public static CropTier cTierReg(Integer tier, Integer color, TextFormatting formatting) {
        return new CropTier(new ResourceLocation(Constant.ModId, tier.toString()), tier, color, formatting);
    }
}
