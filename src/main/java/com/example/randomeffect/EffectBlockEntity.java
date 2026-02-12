package com.example.randomeffect;

import net.minecraft.core.BlockPos;
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
        MobEffects.MOVEMENT_SPEED, MobEffects.MOVEMENT_SLOWDOWN, MobEffects.DIG_SPEED,
        MobEffects.DIG_SLOWDOWN, MobEffects.DAMAGE_BOOST, MobEffects.HEAL,
        MobEffects.HARM, MobEffects.JUMP, MobEffects.CONFUSION,
        MobEffects.REGENERATION, MobEffects.DAMAGE_RESISTANCE, MobEffects.FIRE_RESISTANCE,
        MobEffects.WATER_BREATHING, MobEffects.INVISIBILITY, MobEffects.BLINDNESS,
        MobEffects.NIGHT_VISION, MobEffects.HUNGER, MobEffects.WEAKNESS,
        MobEffects.POISON, MobEffects.WITHER, MobEffects.HEALTH_BOOST,
        MobEffects.ABSORPTION, MobEffects.SATURATION, MobEffects.GLOWING,
        MobEffects.LEVITATION, MobEffects.LUCK, MobEffects.UNLUCK, MobEffects.SLOW_FALLING
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
        for (Player p : players) {
            MobEffect effect = EFFECTS[random.nextInt(EFFECTS.length)];
            int duration = (5 + random.nextInt(25)) * 20;
            int amplifier = random.nextInt(3);
            p.addEffect(new MobEffectInstance(effect, duration, amplifier));
        }
    }
}
