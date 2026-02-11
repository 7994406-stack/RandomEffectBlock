package com.example.randomeffect;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, RandomEffectMod.MOD_ID);
    
    public static final RegistryObject<BlockEntityType<EffectBlockEntity>> EFFECT_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("effect_block_entity",
                    () -> BlockEntityType.Builder.of(EffectBlockEntity::new,
                            RandomEffectMod.EFFECT_BLOCK.get()).build(null));
}
