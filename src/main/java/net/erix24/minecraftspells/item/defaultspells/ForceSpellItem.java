package net.erix24.minecraftspells.item.defaultspells;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import static net.erix24.minecraftspells.item.utility.damageSpellUtility.damageSpell;

public class ForceSpellItem extends Item {
    public ForceSpellItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity pMiningEntity) {
        return false;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        boolean conditionOne = !pLevel.getBlockState(new BlockPos(pPlayer.getBlockX(), pPlayer.getBlockY() - 1, pPlayer.getBlockZ())).is(Blocks.AIR)
                && pLevel.getFluidState(new BlockPos(pPlayer.getBlockX(), pPlayer.getBlockY() - 1, pPlayer.getBlockZ())).isEmpty();
        boolean conditionTwo = !pLevel.getBlockState(new BlockPos(pPlayer.getBlockX() - 1, pPlayer.getBlockY() - 1, pPlayer.getBlockZ())).is(Blocks.AIR)
                && pLevel.getFluidState(new BlockPos(pPlayer.getBlockX() - 1, pPlayer.getBlockY() - 1, pPlayer.getBlockZ())).isEmpty();
        boolean conditionThree = !pLevel.getBlockState(new BlockPos(pPlayer.getBlockX(), pPlayer.getBlockY() - 1, pPlayer.getBlockZ() - 1)).is(Blocks.AIR)
                && pLevel.getFluidState(new BlockPos(pPlayer.getBlockX(), pPlayer.getBlockY() - 1, pPlayer.getBlockZ() - 1)).isEmpty();
        boolean conditionFour = !pLevel.getBlockState(new BlockPos(pPlayer.getBlockX() + 1, pPlayer.getBlockY() - 1, pPlayer.getBlockZ())).is(Blocks.AIR)
                && pLevel.getFluidState(new BlockPos(pPlayer.getBlockX() + 1, pPlayer.getBlockY() - 1, pPlayer.getBlockZ())).isEmpty();
        boolean conditionFive = !pLevel.getBlockState(new BlockPos(pPlayer.getBlockX(), pPlayer.getBlockY() - 1, pPlayer.getBlockZ() + 1)).is(Blocks.AIR)
                && pLevel.getFluidState(new BlockPos(pPlayer.getBlockX(), pPlayer.getBlockY() - 1, pPlayer.getBlockZ() - 1)).isEmpty();

        if (conditionOne || conditionTwo || conditionThree || conditionFour || conditionFive) {
//            for (int i = 0; i < 100; i += 1) {
//                pLevel.addParticle(ParticleTypes.TOTEM_OF_UNDYING, pPlayer.getBlockX(), pPlayer.getBlockY(),
//                        pPlayer.getBlockZ(), (Math.random() - 0.5) * 1.5, 2,
//                        (Math.random() - 0.5) * 1.5);
//            }

            BlockHitResult result = getPlayerPOVHitResult(pLevel, pPlayer, ClipContext.Fluid.NONE);

            // Get the velocity to fling the player
            Vec3 newVelocity = getVelocity(pPlayer, result);

            if (conditionOne) {
                showSpellParticle(pLevel, pPlayer, newVelocity, 0, 0);
            }
            if (conditionTwo) {
                showSpellParticle(pLevel, pPlayer, newVelocity, -1, 0);
            }
            if (conditionThree) {
                showSpellParticle(pLevel, pPlayer, newVelocity, 0, -1);
            }
            if (conditionFour) {
                showSpellParticle(pLevel, pPlayer, newVelocity, 1, 0);
            }
            if (conditionFive) {
                showSpellParticle(pLevel, pPlayer, newVelocity, 0, 1);
            }

            // Set the player's updated velocity
            pPlayer.setDeltaMovement(newVelocity);

            damageSpell(pPlayer, pUsedHand);

        }
        return super.use(pLevel, pPlayer, pUsedHand);


    }

    private static @NotNull Vec3 getVelocity(Player pPlayer, BlockHitResult result) {
        double xDelta = -(pPlayer.getX() - result.getBlockPos().getX());
        double yDelta = -(pPlayer.getY() - result.getBlockPos().getY());
        double zDelta = -(pPlayer.getZ() - result.getBlockPos().getZ());

        double dashLine = Math.sqrt(xDelta * xDelta + yDelta * yDelta + zDelta * zDelta);
        double newXDelta = (xDelta / dashLine);
        double newYDelta = (yDelta / dashLine);
        double newZDelta = (zDelta / dashLine);

        // Get the player's current velocity
        Vec3 currentVelocity = pPlayer.getDeltaMovement();

        // Add the calculated delta to the current velocity (adding force)
        return currentVelocity.add(newXDelta, newYDelta, newZDelta);
    }

    private static void showSpellParticle(Level pLevel, Player pPlayer, Vec3 particleVel, Integer blockXMod, Integer BlockZMod) {
        for (int i = 0; i < 20; i += 1) {
            pLevel.addParticle(ParticleTypes.TOTEM_OF_UNDYING, pPlayer.getBlockX() + blockXMod + 0.5,
                    pPlayer.getBlockY() + 0.5, pPlayer.getBlockZ() + BlockZMod + 0.5, particleVel.x * Math.random() * 3,
                    particleVel.y * Math.random() * 3, particleVel.z * Math.random() * 3);
        }
    }
}
