package com.algorithmlx.tenytech.compact.agriculture

import com.algorithmlx.tenytech.ModId
import com.algorithmlx.tenytech.compact.agriculture.TTCropTiers.sevenTier
import com.algorithmlx.tenytech.init.TTConfig.enableCruxedDragonEgg
import com.algorithmlx.tenytech.init.TTConfig.enableCruxedNetherStar
import com.algorithmlx.tenytech.init.TTConfig.enableCruxedNitro
import com.algorithmlx.tenytech.init.TTConfig.enableDragonBreath
import com.algorithmlx.tenytech.init.TTConfig.enableMAIntegration
import com.algorithmlx.tenytech.init.TTConfig.enableSevenTierCrops
import com.blakebr0.mysticalagriculture.api.IMysticalAgriculturePlugin
import com.blakebr0.mysticalagriculture.api.MysticalAgriculturePlugin
import com.blakebr0.mysticalagriculture.api.crop.Crop
import com.blakebr0.mysticalagriculture.api.crop.CropTier
import com.blakebr0.mysticalagriculture.api.crop.CropType
import com.blakebr0.mysticalagriculture.api.lib.LazyIngredient
import com.blakebr0.mysticalagriculture.api.lib.PluginConfig
import com.blakebr0.mysticalagriculture.api.registry.ICropRegistry
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.ModList

@MysticalAgriculturePlugin
class MAPlugin: IMysticalAgriculturePlugin {
    companion object {
        private val listOfCrops = mutableListOf<Crop>()

        val cruxedNetherStar = if (enableMAIntegration.get() && enableSevenTierCrops.get() && enableCruxedNetherStar.get())
            crop("cruxed_nether_star", sevenTier)
        else null

        val cruxedDragonEgg = if (enableMAIntegration.get() && enableSevenTierCrops.get() && enableCruxedDragonEgg.get())
            crop("cruxed_dragon_egg", sevenTier)
        else null

        val cruxedNitroCrystal = if (enableMAIntegration.get() && enableSevenTierCrops.get() && enableCruxedNitro.get() && ModList.get().isLoaded("powah"))
            crop("nitro_crystal", sevenTier)
        else null

        val dragonBreath = if (enableMAIntegration.get() && enableSevenTierCrops.get() && enableDragonBreath.get())
            crop("dragon_breath", sevenTier)
        else null

        private fun crop(name: String, cropTier: CropTier): Crop {
            val crop = Crop(ResourceLocation(ModId, name), cropTier, CropType.RESOURCE, LazyIngredient.EMPTY)
            listOfCrops.add(crop)
            return crop
        }
    }

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
}