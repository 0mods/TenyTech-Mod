package com.algorithmlx.tenytech.compact.agriculture

import com.algorithmlx.tenytech.ModId
import com.algorithmlx.tenytech.compact.agriculture.TTCropTiers.sevenTier
import com.blakebr0.mysticalagriculture.api.IMysticalAgriculturePlugin
import com.blakebr0.mysticalagriculture.api.MysticalAgriculturePlugin
import com.blakebr0.mysticalagriculture.api.crop.Crop
import com.blakebr0.mysticalagriculture.api.crop.CropTier
import com.blakebr0.mysticalagriculture.api.crop.CropType
import com.blakebr0.mysticalagriculture.api.lib.LazyIngredient
import com.blakebr0.mysticalagriculture.api.lib.PluginConfig
import com.blakebr0.mysticalagriculture.api.registry.ICropRegistry
import net.minecraft.util.ResourceLocation

@MysticalAgriculturePlugin
object MAPlugin: IMysticalAgriculturePlugin {
    private val listOfCrops = mutableListOf<Crop>()

    val cruxedNetherStar = crop("cruxed_nether_star", sevenTier, LazyIngredient.EMPTY)
    val cruxedDragonEgg = crop("cruxed_dragon_egg", sevenTier, LazyIngredient.EMPTY)
    val cruxedNitroCrystal = crop("nitro_crystal", sevenTier, LazyIngredient.EMPTY)
    val dragonBreath = crop("dragon_breath", sevenTier, LazyIngredient.EMPTY)

    override fun configure(config: PluginConfig) {
        config.modId = ModId
        config.disableDynamicSeedInfusionRecipes()
        config.disableDynamicSeedCraftingRecipes()
        config.disableDynamicSeedReprocessingRecipes()
    }

    override fun onRegisterCrops(registry: ICropRegistry) {
        for (crop in listOfCrops) {
            registry.register(crop)
        }
    }

    private fun crop(name: String, cropTier: CropTier, material: LazyIngredient): Crop {
        val crop = Crop(ResourceLocation(ModId, name), cropTier, CropType.RESOURCE, material)
        listOfCrops.add(crop)
        return crop
    }
}