package net.algorithmlx.tenytech.setup;

import net.algorithmlx.tenytech.api.TabMethod;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = Constant.ModId, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModSetup {
    public static final TabMethod TENY_TECH = TabMethod.create("", Registry.ALGENIUM_BLOCK.get());

    public static void init(final FMLCommonSetupEvent event) {

    }

    @SubscribeEvent
    public static void serverSetup(final RegisterCommandsEvent event) {

    }
}
