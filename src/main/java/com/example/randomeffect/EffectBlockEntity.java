package com.example.randomeffect;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import java.util.List;
import java.util.Random;

public class EffectBlockEntity extends BlockEntity {
    private int tickCounter = 0;
    private final Random random = new Random();

    private static final MobEffect[] EFFECTS = {
        MobEffects.MOVEMENT_SPEED, MobEffects.DIG_SPEED, MobEffects.DAMAGE_BOOST, 
        MobEffects.JUMP, MobEffects.REGENERATION, MobEffects.DAMAGE_RESISTANCE, 
        MobEffects.FIRE_RESISTANCE, MobEffects.WATER_BREATHING, MobEffects.INVISIBILITY, 
        MobEffects.NIGHT_VISION, MobEffects.HEALTH_BOOST, MobEffects.ABSORPTION, 
        MobEffects.GLOWING, MobEffects.LEVITATION, MobEffects.SLOW_FALLING
    };

    public EffectBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.EFFECT_BLOCK_ENTITY.get(), pos, state);
    }

    public void tick() {
        if (level == null || level.isClientSide) return;
        if (++tickCounter >= 200) {
            tickCounter = 0;
            applyEffects();
        }
    }

    private void applyEffects() {
        List<Player> players = level.getEntitiesOfClass(Player.class, new AABB(worldPosition).inflate(10));
        
        if (!players.isEmpty()) {
            // Эффекты для блока (звук и частицы)
            level.playSound(null, worldPosition, SoundEvents.AMETHYST_BLOCK_CHIME, SoundSource.BLOCKS, 1.0F, 1.0F);
            
            for (int i = 0; i < 20; i++) {
                double d0 = (double)worldPosition.getX() + random.nextDouble();
                double d1 = (double)worldPosition.getY() + 1.2D;
                double d2 = (double)worldPosition.getZ() + random.nextDouble();
                level.addFreshEntity(new net.minecraft.world.entity.projectile.EvokerFangs(level, d0, d1, d2, 0, 0, null)); // Визуальный эффект
                // Используем частицы через уровень
                ((net.minecraft.server.level.ServerLevel)level).sendParticles(ParticleTypes.WITCH, d0, d1, d2, 1, 0, 0, 0, 0.1);
            }

            for (Player p : players) {
                MobEffect effect = EFFECTS[random.nextInt(EFFECTS.length)];
                p.addEffect(new MobEffectInstance(effect, (10 + random.nextInt(20)) * 20, random.nextInt(2)));
                // Звук лично игроку
                level.playSound(null, p.blockPosition(), SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.PLAYERS, 0.5F, 1.2F);
            }
        }
    }
}
