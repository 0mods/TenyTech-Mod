package net.algorithmlx.tenytech.item;

import com.blakebr0.mysticalagriculture.item.EssenceItem;
import net.algorithmlx.tenytech.init.ModSetup;
import net.algorithmlx.tenytech.lib.ModCorePlugin;

public class AlgeniumEssence extends EssenceItem {
    public AlgeniumEssence() {
        super(ModCorePlugin.CROP_TIER_7, properties -> properties.tab(ModSetup.TENY_TECH));
    }
}
