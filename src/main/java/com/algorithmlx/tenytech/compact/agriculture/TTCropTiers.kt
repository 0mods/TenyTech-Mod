package com.algorithmlx.tenytech.compact.agriculture

import com.algorithmlx.tenytech.ModId
import com.blakebr0.mysticalagriculture.api.crop.CropTier
import net.minecraft.util.ResourceLocation
import net.minecraft.util.text.TextFormatting

object TTCropTiers {
    val sevenTier = create(7, 0x40005E, TextFormatting.BLUE)

    fun create(tier: Int, color: Int, format: TextFormatting): CropTier {
        return CropTier(ResourceLocation(ModId, tier.toString()), tier, color, format)
    }
}