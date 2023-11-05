package com.algorithmlx.tenytech.init

import net.minecraftforge.common.ForgeConfigSpec

object TTConfig {
    private val builder = ForgeConfigSpec.Builder()
    @get:JvmStatic
    val spec: ForgeConfigSpec

    init {
        builder.push("For NOT common usage")
    }

    val enableMAIntegration: ForgeConfigSpec.BooleanValue = builder.define("mystical_agriculture_integration", true)

    init {
        builder.push("MA Integration Settings")
    }

    val enableSevenTierCrops: ForgeConfigSpec.BooleanValue = builder.define("7_tier_crops", true)
    val enableCruxedNitro: ForgeConfigSpec.BooleanValue = builder.define("cruxed_nitro", true)
    val enableDragonBreath: ForgeConfigSpec.BooleanValue = builder.define("cruxed_dragon_breath", true)
    val enableCruxedDragonEgg: ForgeConfigSpec.BooleanValue = builder.define("cruxed_dragon_egg", true)
    val enableCruxedNetherStar: ForgeConfigSpec.BooleanValue = builder.define("cruxed_nether_star", true)

    init {
        builder.pop()
    }

    val enableAugmentOfFly: ForgeConfigSpec.BooleanValue = builder.define("augment_of_fly", true)
    val enableDoubleBlackIron: ForgeConfigSpec.BooleanValue = builder.define("double_black_iron", true)
    val enableAlgeniumEssence: ForgeConfigSpec.BooleanValue = builder.define("algenium_essence", true)
    val enableAlgeniumBlock: ForgeConfigSpec.BooleanValue = builder.define("algenium_block", true)

    init {
        builder.pop()
        builder.push("Fly/Angel Ring settings")
    }

    val enableRings: ForgeConfigSpec.BooleanValue = builder.define("enable_rings", true)
    val enableFlyRing: ForgeConfigSpec.BooleanValue =  builder.define("fly_ring", true)
    val enableAngelRing: ForgeConfigSpec.BooleanValue = builder.define("angel_ring", true)

    val flyRingWorkTime: ForgeConfigSpec.IntValue = builder.defineInRange("fly_ring_time", 7, 12000, Int.MAX_VALUE)

    init {
        builder.pop()
        spec = builder.build()
    }
}