package net.algorithmlx.tenytech;

import net.algorithmlx.tenytech.registry.ModBlocks;
import net.algorithmlx.tenytech.registry.ModItems;
import net.algorithmlx.tenytech.registry.Registry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(TenyTech.ModId)
public class TenyTech {

    public static final Logger LOGGER = LogManager.getLogger();
    public static final String ModId = "tenytech";
    public static final String NAME = "TenyTech Mod";
    public static final ItemGroup TT_IG = new TT_IG(TenyTech.ModId);
    //ItemGroups

    public TenyTech() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        Registry.init();

        bus.register(this);
        bus.register(new ModBlocks());
        bus.register(new ModItems());

        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
    }
}
