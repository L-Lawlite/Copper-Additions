package net.lawliet.copper_additions.copperAdditionsRegistration;

import net.lawliet.copper_additions.CopperAdditions;
//import net.lawliet.copper_additions.blockEntities.WeatheringCopperChestBlock;
import net.lawliet.copper_additions.blocks.weatheringBlocks.WeatheringCopperBarBlock;
import net.lawliet.copper_additions.blocks.weatheringBlocks.WeatheringCopperChainBlock;
import net.lawliet.copper_additions.blocks.weatheringBlocks.WeatheringCopperLanternBlock;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;

public class Blocks {

    public static final DeferredBlock<Block> COPPER_TORCH;
    public static final DeferredBlock<Block> COPPER_WALL_TORCH;

    public static final DeferredBlock<Block> COPPER_BARS;
    public static final DeferredBlock<Block> EXPOSED_COPPER_BARS;
    public static final DeferredBlock<Block> WEATHERED_COPPER_BARS;
    public static final DeferredBlock<Block> OXIDIZED_COPPER_BARS;

    public static final DeferredBlock<Block> WAXED_COPPER_BARS;
    public static final DeferredBlock<Block> WAXED_EXPOSED_COPPER_BARS;
    public static final DeferredBlock<Block> WAXED_WEATHERED_COPPER_BARS;
    public static final DeferredBlock<Block> WAXED_OXIDIZED_COPPER_BARS;

    public static final DeferredBlock<Block> COPPER_CHAIN;
    public static final DeferredBlock<Block> EXPOSED_COPPER_CHAIN;
    public static final DeferredBlock<Block> WEATHERED_COPPER_CHAIN;
    public static final DeferredBlock<Block> OXIDIZED_COPPER_CHAIN;

    public static final DeferredBlock<Block> WAXED_COPPER_CHAIN;
    public static final DeferredBlock<Block> WAXED_EXPOSED_COPPER_CHAIN;
    public static final DeferredBlock<Block> WAXED_WEATHERED_COPPER_CHAIN;
    public static final DeferredBlock<Block> WAXED_OXIDIZED_COPPER_CHAIN;

    public static final DeferredBlock<Block> COPPER_LANTERN;
    public static final DeferredBlock<Block> EXPOSED_COPPER_LANTERN;
    public static final DeferredBlock<Block> WEATHERED_COPPER_LANTERN;
    public static final DeferredBlock<Block> OXIDIZED_COPPER_LANTERN;

    public static final DeferredBlock<Block> WAXED_COPPER_LANTERN;
    public static final DeferredBlock<Block> WAXED_EXPOSED_COPPER_LANTERN;
    public static final DeferredBlock<Block> WAXED_WEATHERED_COPPER_LANTERN;
    public static final DeferredBlock<Block> WAXED_OXIDIZED_COPPER_LANTERN;

//    public static final DeferredBlock<Block> COPPER_CHEST;


    static {
         COPPER_TORCH = CopperAdditions.BLOCKS.registerBlock(
                 "copper_torch",
                 properties -> new TorchBlock(ParticleTypes.COPPER_FIRE_FLAME.get(), properties),
                 BlockBehaviour.Properties.of().noCollission().instabreak().lightLevel(blockstate -> 14).sound(SoundType.WOOD).pushReaction(PushReaction.DESTROY)
         );
         COPPER_WALL_TORCH = CopperAdditions.BLOCKS.registerBlock(
                 "copper_wall_torch",
                 properties -> new WallTorchBlock(ParticleTypes.COPPER_FIRE_FLAME.get(), wallVariant(COPPER_TORCH.get(), properties, true)),
                 BlockBehaviour.Properties.of().noCollission().instabreak().lightLevel(blockstate -> 14).sound(SoundType.WOOD).pushReaction(PushReaction.DESTROY)
         );
         COPPER_BARS = CopperAdditions.BLOCKS.registerBlock(
                 "copper_bars",
                 properties -> new WeatheringCopperBarBlock(WeatheringCopper.WeatherState.UNAFFECTED, properties),
                 copperBarProperties()
                 );
        EXPOSED_COPPER_BARS = CopperAdditions.BLOCKS.registerBlock(
                "exposed_copper_bars",
                properties -> new WeatheringCopperBarBlock(WeatheringCopper.WeatherState.EXPOSED, properties),
                copperBarProperties()
        );
        WEATHERED_COPPER_BARS = CopperAdditions.BLOCKS.registerBlock(
                "weathered_copper_bars",
                properties -> new WeatheringCopperBarBlock(WeatheringCopper.WeatherState.WEATHERED, properties),
                copperBarProperties()
        );
        OXIDIZED_COPPER_BARS = CopperAdditions.BLOCKS.registerBlock(
                "oxidized_copper_bars",
                properties -> new WeatheringCopperBarBlock(WeatheringCopper.WeatherState.OXIDIZED, properties),
                copperBarProperties()
        );
        WAXED_COPPER_BARS = CopperAdditions.BLOCKS.registerBlock(
                "waxed_copper_bars",
                IronBarsBlock::new,
                copperBarProperties()
                );
        WAXED_EXPOSED_COPPER_BARS = CopperAdditions.BLOCKS.registerBlock(
                "waxed_exposed_copper_bars",
                IronBarsBlock::new,
                copperBarProperties()
        );
        WAXED_WEATHERED_COPPER_BARS = CopperAdditions.BLOCKS.registerBlock(
                "waxed_weathered_copper_bars",
                IronBarsBlock::new,
                copperBarProperties()
        );
        WAXED_OXIDIZED_COPPER_BARS = CopperAdditions.BLOCKS.registerBlock(
                "waxed_oxidized_copper_bars",
                IronBarsBlock::new,
                copperBarProperties()
        );


        COPPER_CHAIN = CopperAdditions.BLOCKS.registerBlock(
                "copper_chain",
                properties -> new WeatheringCopperChainBlock(WeatheringCopper.WeatherState.UNAFFECTED, properties),
                copperChainProperties()
                );
        EXPOSED_COPPER_CHAIN = CopperAdditions.BLOCKS.registerBlock(
                "exposed_copper_chain",
                properties -> new WeatheringCopperChainBlock(WeatheringCopper.WeatherState.EXPOSED, properties),
                copperChainProperties()
                );
        WEATHERED_COPPER_CHAIN = CopperAdditions.BLOCKS.registerBlock(
                "weathered_copper_chain",
                properties -> new WeatheringCopperChainBlock(WeatheringCopper.WeatherState.WEATHERED, properties),
                copperChainProperties()
                );
        OXIDIZED_COPPER_CHAIN = CopperAdditions.BLOCKS.registerBlock(
                "oxidized_copper_chain",
                properties -> new WeatheringCopperChainBlock(WeatheringCopper.WeatherState.OXIDIZED, properties),
                copperChainProperties()
                );
        WAXED_COPPER_CHAIN = CopperAdditions.BLOCKS.registerBlock(
                "waxed_copper_chain",
                ChainBlock::new,
                copperChainProperties()
        );
        WAXED_EXPOSED_COPPER_CHAIN = CopperAdditions.BLOCKS.registerBlock(
                "waxed_exposed_copper_chain",
                ChainBlock::new,
                copperChainProperties()
        );
        WAXED_WEATHERED_COPPER_CHAIN = CopperAdditions.BLOCKS.registerBlock(
                "waxed_weathered_copper_chain",
                ChainBlock::new,
                copperChainProperties()
        );
        WAXED_OXIDIZED_COPPER_CHAIN = CopperAdditions.BLOCKS.registerBlock(
                "waxed_oxidized_copper_chain",
                ChainBlock::new,
                copperChainProperties()
        );

        COPPER_LANTERN = CopperAdditions.BLOCKS.registerBlock(
                "copper_lantern",
                properties -> new WeatheringCopperLanternBlock(WeatheringCopper.WeatherState.UNAFFECTED, properties),
                copperLanternProperties()
        );
        EXPOSED_COPPER_LANTERN = CopperAdditions.BLOCKS.registerBlock(
                "exposed_copper_lantern",
                properties -> new WeatheringCopperLanternBlock(WeatheringCopper.WeatherState.EXPOSED, properties),
                copperLanternProperties()
        );
        WEATHERED_COPPER_LANTERN = CopperAdditions.BLOCKS.registerBlock(
                "weathered_copper_lantern",
                properties -> new WeatheringCopperLanternBlock(WeatheringCopper.WeatherState.WEATHERED, properties),
                copperLanternProperties()
        );
        OXIDIZED_COPPER_LANTERN = CopperAdditions.BLOCKS.registerBlock(
                "oxidized_copper_lantern",
                properties -> new WeatheringCopperLanternBlock(WeatheringCopper.WeatherState.OXIDIZED, properties),
                copperLanternProperties()
        );

        WAXED_COPPER_LANTERN = CopperAdditions.BLOCKS.registerBlock(
                "waxed_copper_lantern",
                LanternBlock::new,
                copperLanternProperties()
        );
        WAXED_EXPOSED_COPPER_LANTERN = CopperAdditions.BLOCKS.registerBlock(
                "waxed_exposed_copper_lantern",
                LanternBlock::new,
                copperLanternProperties()
        );
        WAXED_WEATHERED_COPPER_LANTERN = CopperAdditions.BLOCKS.registerBlock(
                "waxed_weathered_copper_lantern",
                LanternBlock::new,
                copperLanternProperties()
        );
        WAXED_OXIDIZED_COPPER_LANTERN = CopperAdditions.BLOCKS.registerBlock(
                "waxed_oxidized_copper_lantern",
                LanternBlock::new,
                copperLanternProperties()
        );

//        COPPER_CHEST = CopperAdditions.BLOCKS.registerBlock(
//                "copper_chest",
//                properties -> new WeatheringCopperChestBlock(
//                        WeatheringCopper.WeatherState.UNAFFECTED, CopperAdditionSounds.COPPER_CHEST_OPEN.value(), CopperAdditionSounds.COPPER_CHEST_CLOSE.value(), properties
//                ),
//                copperChestProperties().mapColor(net.minecraft.world.level.block.Blocks.COPPER_BLOCK.defaultMapColor())
//        );

    }

    private static BlockBehaviour.Properties wallVariant(Block baseBlock, BlockBehaviour.Properties properties, boolean overrideDescription){
        BlockBehaviour.Properties properties1 = properties.overrideLootTable(baseBlock.getLootTable());
        if (overrideDescription) {
            properties1 = properties1.overrideDescription(baseBlock.getDescriptionId());
        }

        return properties1;
    }

    private static BlockBehaviour.Properties copperBarProperties() {
        return BlockBehaviour.Properties.of()
                .requiresCorrectToolForDrops()
                .strength(5.0F, 6.0F)
                .sound(SoundType.COPPER)
                .noOcclusion();
    }

    private static BlockBehaviour.Properties copperChainProperties() {
        return BlockBehaviour.Properties.of()
                .forceSolidOn()
                .requiresCorrectToolForDrops()
                .strength(5.0F, 6.0F)
                .sound(SoundType.CHAIN)
                .noOcclusion();
    }

    private static BlockBehaviour.Properties copperLanternProperties() {
        return BlockBehaviour.Properties.of()
                .mapColor(MapColor.METAL)
                .forceSolidOn()
                .strength(3.5F)
                .sound(SoundType.LANTERN)
                .lightLevel(blockstate -> 15)
                .noOcclusion()
                .pushReaction(PushReaction.DESTROY);
    }

    private static BlockBehaviour.Properties copperChestProperties() {
        return BlockBehaviour.Properties.of()
                .strength(3.0F, 6.0F)
                .sound(SoundType.COPPER)
                .requiresCorrectToolForDrops();
    }

    public static void init() {}

    @SuppressWarnings("deprecation")
    public static void modifyRenderLayer() {
        CopperAdditions.BLOCKS.getEntries().stream().filter(blockHolder -> {
            String blockPath = blockHolder.getId().getPath();
            return blockPath.contains("copper_bars") || blockPath.contains("copper_chain");
        }).forEach(block -> ItemBlockRenderTypes.setRenderLayer(block.get(), RenderType.CUTOUT));
    }
}
