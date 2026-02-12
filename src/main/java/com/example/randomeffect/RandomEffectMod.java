package com.example.randomeffect;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod(RandomEffectMod.MOD_ID)
public class RandomEffectMod {
    public static final String MOD_ID = "randomeffect";

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);

    public static final RegistryObject<Block> EFFECT_BLOCK = BLOCKS.register("effect_block",
            () -> new EffectBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));

    public static final RegistryObject<Item> EFFECT_BLOCK_ITEM = ITEMS.register("effect_block",
            () -> new BlockItem(EFFECT_BLOCK.get(), new Item.Properties()));

    // Создаем свою вкладку креатива
    public static final RegistryObject<CreativeModeTab> MOD_TAB = CREATIVE_TABS.register("effect_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.randomeffect_tab"))
                    .icon(() -> new ItemStack(EFFECT_BLOCK.get()))
                    .displayItems((parameters, output) -> {
                        output.accept(EFFECT_BLOCK_ITEM.get()); // Добавляем блок во вкладку
                    }).build());

    public RandomEffectMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        CREATIVE_TABS.register(modEventBus);
        ModBlockEntities.BLOCK_ENTITIES.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }
}
