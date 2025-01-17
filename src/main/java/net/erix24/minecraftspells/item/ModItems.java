package net.erix24.minecraftspells.item;

import net.erix24.minecraftspells.Spells;
import net.erix24.minecraftspells.item.defaultspells.BoostSpellItem;
import net.erix24.minecraftspells.item.defaultspells.ForceSpellItem;
import net.erix24.minecraftspells.item.defaultspells.IgniteSpellItem;
import net.erix24.minecraftspells.item.defaultspells.SpellParchmentItem;
import net.erix24.minecraftspells.item.unlimiteditems.UnlimitedBoostSpellItem;
import net.erix24.minecraftspells.item.unlimiteditems.UnlimitedForceSpellItem;
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
            () -> new SpellParchmentItem(new Item.Properties()));
    public static final RegistryObject<Item> KINETIC_PARCHMENT = ITEMS.register("kinetic_parchment",
            () -> new SpellParchmentItem(new Item.Properties()));
    public static final RegistryObject<Item> FLAME_PARCHMENT = ITEMS.register("flame_parchment",
            () -> new SpellParchmentItem(new Item.Properties()));

    public static final RegistryObject<Item> FORCE_SPELL_PARCHMENT = ITEMS.register("force_spell_parchment",
            () -> new ForceSpellItem(new Item.Properties().stacksTo(1).durability(8)));
    public static final RegistryObject<Item> BOOST_SPELL_PARCHMENT = ITEMS.register("boost_spell_parchment",
            () -> new BoostSpellItem(new Item.Properties().stacksTo(1).durability(8)));

    public static final RegistryObject<Item> IGNITE_SPELL_PARCHMENT = ITEMS.register("ignite_spell_parchment",
            () -> new IgniteSpellItem(new Item.Properties().stacksTo(1).durability(4)));

    // Unlimited items
    public static final RegistryObject<Item> UNLIMITED_BOOST_SPELL_PARCHMENT = ITEMS.register("boost_spell_unlimited",
            () -> new UnlimitedBoostSpellItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> UNLIMITED_FORCE_SPELL_PARCHMENT = ITEMS.register("force_spell_unlimited",
            () -> new UnlimitedForceSpellItem(new Item.Properties().stacksTo(1)));

    // Method to register the items
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
