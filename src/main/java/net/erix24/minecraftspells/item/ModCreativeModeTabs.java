package net.erix24.minecraftspells.item;

import net.erix24.minecraftspells.Spells;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Spells.MOD_ID);

    // Tabs
    public static final RegistryObject<CreativeModeTab> SPELL_TAB = CREATIVE_MODE_TABS.register("spell_tab" ,
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.SPELL_PARCHMENT.get()))
                    .title(Component.translatable("creativetab.spell_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.SPELL_PARCHMENT.get());
                        output.accept(ModItems.KINETIC_PARCHMENT.get());
                        output.accept(ModItems.FLAME_PARCHMENT.get());

                        output.accept(ModItems.FORCE_SPELL_PARCHMENT.get());
                        output.accept(ModItems.BOOST_SPELL_PARCHMENT.get());

                        output.accept(ModItems.UNLIMITED_FORCE_SPELL_PARCHMENT.get());
                        output.accept(ModItems.UNLIMITED_BOOST_SPELL_PARCHMENT.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
