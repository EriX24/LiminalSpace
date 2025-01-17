package net.erix24.minecraftspells.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import static net.erix24.minecraftspells.item.utility.damageSpellUtility.damageSpell;

public class BoostSpellItem extends Item {
    public BoostSpellItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity pMiningEntity) {
        return false;
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
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
            for (int i = 0; i < 100; i += 1) {
                pLevel.addParticle(ParticleTypes.TOTEM_OF_UNDYING, pPlayer.getBlockX(), pPlayer.getBlockY(),
                        pPlayer.getBlockZ(), (Math.random() - 0.5) * 1.5, 2,
                        (Math.random() - 0.5) * 1.5);
            }

            Vec3 newVelocity = new Vec3(0, 1.5, 0);

            pPlayer.setDeltaMovement(newVelocity);

            damageSpell(pPlayer, pUsedHand);
        }

        return super.use(pLevel, pPlayer, pUsedHand);

    }
}
