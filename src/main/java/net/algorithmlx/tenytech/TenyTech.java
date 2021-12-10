package net.algorithmlx.tenytech;

import net.algorithmlx.tenytech.init.Registry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;

import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(TenyTech.ModId)
public class TenyTech {

    public static final Logger LOGGER = LogManager.getLogger();
    public static final String ModId = "tenytech";
    //ItemGroups

    public TenyTech() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        Registry.init();

    }
}
