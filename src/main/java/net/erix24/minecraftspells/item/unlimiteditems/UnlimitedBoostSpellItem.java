package net.erix24.minecraftspells.item.unlimiteditems;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class UnlimitedBoostSpellItem extends Item {
    public UnlimitedBoostSpellItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        Vec3 newVelocity = new Vec3(0, 5, 0);

        ItemStack stack = pPlayer.getItemInHand(pUsedHand);

        pPlayer.setDeltaMovement(newVelocity);

        return super.use(pLevel, pPlayer, pUsedHand);
    }
}
