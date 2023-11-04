package com.algorithmlx.tenytech.compact.agriculture.item

import com.algorithmlx.tenytech.compact.agriculture.TTCropTiers
import com.algorithmlx.tenytech.init.Register.tab
import com.blakebr0.mysticalagriculture.block.InfusedFarmlandBlock
import com.blakebr0.mysticalagriculture.item.EssenceItem

object MAIntegratedObjects {
    val algeniumEssence = EssenceItem(TTCropTiers.sevenTier) { p -> p.tab(tab) }

    val algeniumFarmland = InfusedFarmlandBlock(TTCropTiers.sevenTier)
}