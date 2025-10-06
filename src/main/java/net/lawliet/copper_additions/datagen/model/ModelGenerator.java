package net.lawliet.copper_additions.datagen.model;

import net.lawliet.copper_additions.CopperAdditions;
import net.lawliet.copper_additions.copperAdditionsRegistration.Blocks;
import net.lawliet.copper_additions.copperAdditionsRegistration.Items;
import net.lawliet.copper_additions.utility.ModelTemplatesAdditions;
import net.lawliet.copper_additions.utility.TextureSlotAdditions;
import net.lawliet.copper_additions.utility.EquipmentAssetsPath;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.blockstates.*;
import net.minecraft.client.data.models.model.*;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class ModelGenerator extends ModelProvider {
    public ModelGenerator(PackOutput output) {
        super(output, CopperAdditions.MODID);
    }
    private ItemModelGenerators itemModels;
    private BlockModelGenerators blockModels;

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        this.itemModels = itemModels;
        this.blockModels = blockModels;
        itemModels.generateFlatItem(Items.COPPER_NUGGET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(Items.COPPER_SWORD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(Items.COPPER_SHOVEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(Items.COPPER_PICKAXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(Items.COPPER_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(Items.COPPER_HOE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateTrimmableItem(Items.COPPER_HELMET.get(), EquipmentAssetsPath.COPPER, "helmet", false);
        itemModels.generateTrimmableItem(Items.COPPER_CHESTPLATE.get(), EquipmentAssetsPath.COPPER, "chestplate", false);
        itemModels.generateTrimmableItem(Items.COPPER_LEGGINGS.get(), EquipmentAssetsPath.COPPER, "leggings", false);
        itemModels.generateTrimmableItem(Items.COPPER_BOOTS.get(), EquipmentAssetsPath.COPPER, "boots", false);
        itemModels.generateFlatItem(Items.COPPER_HORSE_ARMOR.get(), ModelTemplates.FLAT_ITEM);

        createCopperBars(Blocks.COPPER_BARS.get(), Blocks.WAXED_COPPER_BARS.get());
        createCopperBars(Blocks.EXPOSED_COPPER_BARS.get(), Blocks.WAXED_EXPOSED_COPPER_BARS.get());
        createCopperBars(Blocks.WEATHERED_COPPER_BARS.get(), Blocks.WAXED_WEATHERED_COPPER_BARS.get());
        createCopperBars(Blocks.OXIDIZED_COPPER_BARS.get(), Blocks.WAXED_OXIDIZED_COPPER_BARS.get());

        createCopperChain(Blocks.COPPER_CHAIN.get(), Blocks.WAXED_COPPER_CHAIN.get());
        createCopperChain(Blocks.EXPOSED_COPPER_CHAIN.get(), Blocks.WAXED_EXPOSED_COPPER_CHAIN.get());
        createCopperChain(Blocks.WEATHERED_COPPER_CHAIN.get(), Blocks.WAXED_WEATHERED_COPPER_CHAIN.get());
        createCopperChain(Blocks.OXIDIZED_COPPER_CHAIN.get(), Blocks.WAXED_OXIDIZED_COPPER_CHAIN.get());

        createCopperLantern(Blocks.COPPER_LANTERN.get(), Blocks.WAXED_COPPER_LANTERN.get());
        createCopperLantern(Blocks.EXPOSED_COPPER_LANTERN.get(), Blocks.WAXED_EXPOSED_COPPER_LANTERN.get());
        createCopperLantern(Blocks.WEATHERED_COPPER_LANTERN.get(), Blocks.WAXED_WEATHERED_COPPER_LANTERN.get());
        createCopperLantern(Blocks.OXIDIZED_COPPER_LANTERN.get(), Blocks.WAXED_OXIDIZED_COPPER_LANTERN.get());

        blockModels.createChest(Blocks.COPPER_CHEST.get(), net.minecraft.world.level.block.Blocks.COPPER_BLOCK, ResourceLocation.withDefaultNamespace("copper"), false);

        blockModels.createNormalTorch(Blocks.COPPER_TORCH.get(), Blocks.COPPER_WALL_TORCH.get());
    }

    private void createCopperBars(Block unwaxedBlock, Block waxedBlock) {
        TextureMapping texturemapping = ModelGenerator.bars(unwaxedBlock);
        ResourceLocation barEnds = ModelTemplatesAdditions.BARS_POST_ENDS.create(unwaxedBlock, texturemapping, itemModels.modelOutput);
        ResourceLocation barPost = ModelTemplatesAdditions.BARS_POST.create(unwaxedBlock, texturemapping, itemModels.modelOutput);
        ResourceLocation barCap = ModelTemplatesAdditions.BARS_CAP.create(unwaxedBlock, texturemapping, itemModels.modelOutput);
        ResourceLocation barCapAlt = ModelTemplatesAdditions.BARS_CAP_ALT.create(unwaxedBlock, texturemapping, itemModels.modelOutput);
        ResourceLocation barPostSide = ModelTemplatesAdditions.BARS_POST_SIDE.create(unwaxedBlock, texturemapping, itemModels.modelOutput);
        ResourceLocation barPostSideAlt = ModelTemplatesAdditions.BARS_POST_SIDE_ALT.create(unwaxedBlock, texturemapping, itemModels.modelOutput);
        this.createBars(unwaxedBlock, barEnds, barPost, barCap, barCapAlt, barPostSide, barPostSideAlt);
        this.createBars(waxedBlock, barEnds, barPost, barCap, barCapAlt, barPostSide, barPostSideAlt);
        blockModels.registerSimpleFlatItemModel(unwaxedBlock);
        itemModels.itemModelOutput.copy(unwaxedBlock.asItem(), waxedBlock.asItem());
    }

    private void createBars(Block block, ResourceLocation barEnds, ResourceLocation barPost, ResourceLocation barCap, ResourceLocation barCapAlt, ResourceLocation barPostSide, ResourceLocation barPostSideAlt) {
        this.blockModels.blockStateOutput.accept(
                MultiPartGenerator.multiPart(block)
                        .with(
                                Variant.variant().with(VariantProperties.MODEL, barEnds)
                        )
                        .with(
                                Condition.condition()
                                        .term(BlockStateProperties.EAST, false)
                                        .term(BlockStateProperties.NORTH, false)
                                        .term(BlockStateProperties.SOUTH, false)
                                        .term(BlockStateProperties.WEST, false),
                                Variant.variant().with(VariantProperties.MODEL, barPost)
                        )
                        .with(
                                Condition.condition()
                                        .term(BlockStateProperties.EAST, false)
                                        .term(BlockStateProperties.NORTH, true)
                                        .term(BlockStateProperties.SOUTH, false)
                                        .term(BlockStateProperties.WEST, false),
                                Variant.variant().with(VariantProperties.MODEL, barCap)
                        )
                        .with(
                                Condition.condition()
                                        .term(BlockStateProperties.EAST, true)
                                        .term(BlockStateProperties.NORTH, false)
                                        .term(BlockStateProperties.SOUTH, false)
                                        .term(BlockStateProperties.WEST, false),
                                Variant.variant().with(VariantProperties.MODEL, barCap)
                                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)
                        )
                        .with(
                                Condition.condition()
                                        .term(BlockStateProperties.EAST, false)
                                        .term(BlockStateProperties.NORTH, false)
                                        .term(BlockStateProperties.SOUTH, true)
                                        .term(BlockStateProperties.WEST, false),
                                Variant.variant().with(VariantProperties.MODEL, barCapAlt)
                        )
                        .with(
                                Condition.condition()
                                        .term(BlockStateProperties.EAST, false)
                                        .term(BlockStateProperties.NORTH, false)
                                        .term(BlockStateProperties.SOUTH, false)
                                        .term(BlockStateProperties.WEST, true),
                                Variant.variant().with(VariantProperties.MODEL, barCapAlt)
                                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)
                        )
                        .with(
                                Condition.condition()
                                        .term(BlockStateProperties.NORTH, true),
                                Variant.variant().with(VariantProperties.MODEL, barPostSide)
                        )
                        .with(
                                Condition.condition()
                                        .term(BlockStateProperties.EAST, true),
                                Variant.variant().with(VariantProperties.MODEL, barPostSide)
                                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)
                        )
                        .with(
                                Condition.condition()
                                        .term(BlockStateProperties.SOUTH, true),
                                Variant.variant().with(VariantProperties.MODEL, barPostSideAlt)
                        )
                        .with(
                                Condition.condition()
                                        .term(BlockStateProperties.WEST, true),
                                Variant.variant().with(VariantProperties.MODEL, barPostSideAlt)
                                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)
                        )
        );
    }

    private void createCopperChain(Block unwaxedBlock, Block waxedBlock) {
        ResourceLocation blockModel =  ModelLocationUtils.getModelLocation(unwaxedBlock);
        this.blockModels.createAxisAlignedPillarBlockCustomModel(unwaxedBlock, blockModel);
        this.blockModels.createAxisAlignedPillarBlockCustomModel(waxedBlock, blockModel);
        this.blockModels.registerSimpleFlatItemModel(unwaxedBlock.asItem());
        this.itemModels.itemModelOutput.copy(unwaxedBlock.asItem(),waxedBlock.asItem());
    }

    private void createCopperLantern(Block unwaxedlanternBlock, Block waxedLanternBlock) {
        ResourceLocation lantern = TexturedModel.LANTERN.create(unwaxedlanternBlock, blockModels.modelOutput);
        ResourceLocation hangingLantern = TexturedModel.HANGING_LANTERN.create(unwaxedlanternBlock, blockModels.modelOutput);
        blockModels.registerSimpleFlatItemModel(unwaxedlanternBlock.asItem());
//        blockModels.registerSimpleItemModel(unwaxedlanternBlock.asItem(), blockModels.createFlatItemModel(unwaxedlanternBlock.asItem()));
        itemModels.itemModelOutput.copy(unwaxedlanternBlock.asItem(), waxedLanternBlock.asItem());
        blockModels.blockStateOutput.accept(MultiVariantGenerator.multiVariant(unwaxedlanternBlock).with(BlockModelGenerators.createBooleanModelDispatch(BlockStateProperties.HANGING, hangingLantern, lantern)));
        blockModels.blockStateOutput.accept(MultiVariantGenerator.multiVariant(waxedLanternBlock).with(BlockModelGenerators.createBooleanModelDispatch(BlockStateProperties.HANGING, hangingLantern, lantern)));
    }

    public static TextureMapping bars(Block block) {
        return new TextureMapping().put(TextureSlotAdditions.BARS, TextureMapping.getBlockTexture(block)).put(TextureSlot.EDGE, TextureMapping.getBlockTexture(block));
    }

}
