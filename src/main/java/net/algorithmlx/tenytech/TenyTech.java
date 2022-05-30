package net.algorithmlx.tenytech;

import net.algorithmlx.tenytech.setup.Constant;
import net.algorithmlx.tenytech.setup.ModSetup;
import net.algorithmlx.tenytech.setup.Registry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Constant.ModId)
public class TenyTech {
    public static final Logger LOGGER = LogManager.getLogger();

    public TenyTech() {
        Registry.init();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ModSetup::init);
    }
}