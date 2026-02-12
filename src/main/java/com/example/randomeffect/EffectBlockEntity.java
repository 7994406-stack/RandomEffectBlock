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
    private static final MobEffect[] EFFECTS = { MobEffects.MOVEMENT_SPEED, MobEffects.JUMP, MobEffects.REGENERATION, MobEffects.GLOWING, MobEffects.LEVITATION };
    public EffectBlockEntity(BlockPos pos, BlockState state) { super(ModBlockEntities.EFFECT_BLOCK_ENTITY.get(), pos, state); }
    public void tick() {
        if (level == null || level.isClientSide) return;
        if (++tickCounter >= 200) { tickCounter = 0; applyEffects(); }
    }
    private void applyEffects() {
        List<Player> players = level.getEntitiesOfClass(Player.class, new AABB(worldPosition).inflate(10));
        for (Player p : players) {
            p.addEffect(new MobEffectInstance(EFFECTS[random.nextInt(EFFECTS.length)], (5 + random.nextInt(25)) * 20, random.nextInt(3)));
        }
    }
}
