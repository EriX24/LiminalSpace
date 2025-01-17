package net.erix24.minecraftspells.item.utility;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class damageSpellUtility {
    public static void damageSpell(Player player, InteractionHand usedHand) {
        ItemStack stack = player.getItemInHand(usedHand);
        stack.hurtAndBreak(1, player, player_ -> player_.broadcastBreakEvent(usedHand));
    }
}
