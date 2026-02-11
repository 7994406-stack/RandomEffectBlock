package com.example.randomeffect;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod(RandomEffectMod.MOD_ID)
public class RandomEffectMod {
    public static final String MOD_ID = "randomeffect";
    
    public static final DeferredRegister<Block> BLOCKS = 
        DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);
    
    public static final DeferredRegister<Item> ITEMS = 
        DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
    
    public static final RegistryObject<Block> EFFECT_BLOCK = BLOCKS.register("effect_block",
        () -> new EffectBlock(BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK)
            .strength(3.0F, 3.0F)
            .requiresCorrectToolForDrops()));
    
    public static final RegistryObject<Item> EFFECT_BLOCK_ITEM = ITEMS.register("effect_block",
        () -> new BlockItem(EFFECT_BLOCK.get(), new Item.Properties()));
    
    public RandomEffectMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        ModBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        
        modEventBus.addListener(this::addCreative);
        
        MinecraftForge.EVENT_BUS.register(this);
    }
    
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(EFFECT_BLOCK_ITEM);
        }
    }
}
