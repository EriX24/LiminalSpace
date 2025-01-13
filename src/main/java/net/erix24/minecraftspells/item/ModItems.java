package net.erix24.minecraftspells.item;

import net.erix24.minecraftspells.Spells;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    // The DeferredRegister for the items
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Spells.MOD_ID);

    // Items
    public static final RegistryObject<Item> SPELL_PARCHMENT = ITEMS.register("spell_parchment",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BOOST_SPELL_PARCHMENT = ITEMS.register("boost_spell_parchment",
            () -> new Item(new Item.Properties()));

    // Method to register the items
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
