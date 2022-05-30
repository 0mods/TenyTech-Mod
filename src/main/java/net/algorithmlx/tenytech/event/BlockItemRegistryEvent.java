package net.algorithmlx.tenytech.event;

import net.algorithmlx.tenytech.setup.Constant;
import net.algorithmlx.tenytech.setup.ModSetup;
import net.algorithmlx.tenytech.setup.Registry;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Constant.ModId, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BlockItemRegistryEvent {
    @SubscribeEvent
    public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
        Registry.BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block -> {
            event.getRegistry()
                    .register(
                            new BlockItem(
                                block, 
                                new Item.Properties()
                                    .tab(ModSetup.TENY_TECH)
                            )
                        .setRegistryName(
                            block.getRegistryName()
                        )
                    );
        });
    }
}

