package net.erix24.minecraftspells.item.unlimiteditems;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public class UnlimitedForceSpellItem extends Item {
    public UnlimitedForceSpellItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean canAttackBlock(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer) {
        return false;
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        return false;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {

        BlockHitResult result = getPlayerPOVHitResult(pLevel, pPlayer, ClipContext.Fluid.NONE);

        double xDelta = -(pPlayer.getX() - result.getBlockPos().getX());
        double yDelta = -(pPlayer.getY() - result.getBlockPos().getY());
        double zDelta = -(pPlayer.getZ() - result.getBlockPos().getZ());

        double dashLine = Math.sqrt(xDelta * xDelta + yDelta * yDelta + zDelta * zDelta);
        double newXDelta = (xDelta / dashLine) * 20;
        double newYDelta = (yDelta / dashLine) * 20;
        double newZDelta = (zDelta / dashLine) * 20;

        // Get the player's current velocity
        Vec3 currentVelocity = pPlayer.getDeltaMovement();

        // Add the calculated delta to the current velocity (adding force)
        Vec3 newVelocity = currentVelocity.add(newXDelta, newYDelta, newZDelta);

        // Set the player's updated velocity
        pPlayer.setDeltaMovement(newVelocity);

        return super.use(pLevel, pPlayer, pUsedHand);
    }
}
