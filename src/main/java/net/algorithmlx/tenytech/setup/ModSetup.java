package net.algorithmlx.tenytech.setup;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = Constant.ModId, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModSetup {
    public static final ItemGroup TENY_TECH = new ItemGroup(Constant.ModId + ".tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Registry.ALGENIUM_ESSENCE.get());
        }
        
    };

    public static void init(final FMLCommonSetupEvent event) {

    }

    @SubscribeEvent
    public static void serverSetup(final RegisterCommandsEvent event) {

    }
}
