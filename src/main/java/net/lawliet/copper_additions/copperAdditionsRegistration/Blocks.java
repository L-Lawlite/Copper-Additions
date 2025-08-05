package net.lawliet.copper_additions.copperAdditionsRegistration;

import net.lawliet.copper_additions.CopperAdditions;
import net.lawliet.copper_additions.blocks.weatheringBlocks.WeatheringCopperBarBlock;
import net.lawliet.copper_additions.blocks.weatheringBlocks.WeatheringCopperChainBlock;
import net.lawliet.copper_additions.blocks.weatheringBlocks.WeatheringCopperLanternBlock;
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
    public static final DeferredBlock<Block> COPPER_CHAINS;
    public static final DeferredBlock<Block> EXPOSED_COPPER_CHAINS;
    public static final DeferredBlock<Block> WEATHERED_COPPER_CHAINS;
    public static final DeferredBlock<Block> OXIDIZED_COPPER_CHAINS;
    public static final DeferredBlock<Block> COPPER_LANTERN;
    public static final DeferredBlock<Block> EXPOSED_COPPER_LANTERN;
    public static final DeferredBlock<Block> WEATHERED_COPPER_LANTERN;
    public static final DeferredBlock<Block> OXIDIZED_COPPER_LANTERN;


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

        COPPER_CHAINS = CopperAdditions.BLOCKS.registerBlock(
                "copper_chains",
                properties -> new WeatheringCopperChainBlock(WeatheringCopper.WeatherState.UNAFFECTED, properties),
                copperChainProperties()
                );
        EXPOSED_COPPER_CHAINS = CopperAdditions.BLOCKS.registerBlock(
                "exposed_copper_chains",
                properties -> new WeatheringCopperChainBlock(WeatheringCopper.WeatherState.EXPOSED, properties),
                copperChainProperties()
                );
        WEATHERED_COPPER_CHAINS = CopperAdditions.BLOCKS.registerBlock(
                "weathered_copper_chains",
                properties -> new WeatheringCopperChainBlock(WeatheringCopper.WeatherState.WEATHERED, properties),
                copperChainProperties()
                );
        OXIDIZED_COPPER_CHAINS = CopperAdditions.BLOCKS.registerBlock(
                "oxidized_copper_chains",
                properties -> new WeatheringCopperChainBlock(WeatheringCopper.WeatherState.OXIDIZED, properties),
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

    public static void init() {}
}
