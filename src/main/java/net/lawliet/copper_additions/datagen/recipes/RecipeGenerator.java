package net.lawliet.copper_additions.datagen.recipes;

import net.lawliet.copper_additions.CopperAdditions;
import net.lawliet.copper_additions.copperAdditionsRegistration.Items;
import net.lawliet.copper_additions.datagen.tags.CopperAdditionsTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.Tags;

import java.util.concurrent.CompletableFuture;

public class RecipeGenerator extends RecipeProvider{
    protected RecipeGenerator(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    @Override
    protected void buildRecipes() {
        craftingRecipeGenerator();
        smeltingRecipeGenerator();
        blastingRecipeGenerator();
    }



    private void smeltingRecipeGenerator() {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(new ItemLike[]{
                Items.COPPER_PICKAXE,
                Items.COPPER_SHOVEL,
                Items.COPPER_AXE,
                Items.COPPER_HOE,
                Items.COPPER_SWORD,
                Items.COPPER_HELMET,
                Items.COPPER_CHESTPLATE,
                Items.COPPER_LEGGINGS,
                Items.COPPER_BOOTS,
                Items.COPPER_HORSE_ARMOR
        }), RecipeCategory.MISC, Items.COPPER_NUGGET, 0.1F, 200)
                .unlockedBy("has_copper_pickaxe", this.has(Items.COPPER_PICKAXE))
                .unlockedBy("has_copper_shovel", this.has(Items.COPPER_SHOVEL))
                .unlockedBy("has_copper_axe", this.has(Items.COPPER_AXE))
                .unlockedBy("has_copper_hoe", this.has(Items.COPPER_HOE))
                .unlockedBy("has_copper_sword", this.has(Items.COPPER_SWORD))
                .unlockedBy("has_copper_helmet", this.has(Items.COPPER_HELMET))
                .unlockedBy("has_copper_chestplate", this.has(Items.COPPER_CHESTPLATE))
                .unlockedBy("has_copper_leggings", this.has(Items.COPPER_LEGGINGS))
                .unlockedBy("has_copper_boots", this.has(Items.COPPER_BOOTS))
                .unlockedBy("has_copper_horse_armor", this.has(Items.COPPER_HORSE_ARMOR))
                .save(this.output,"%s:%s".formatted(CopperAdditions.MODID, getSmeltingRecipeName(Items.COPPER_NUGGET)));

    }

    private void blastingRecipeGenerator() {
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(new ItemLike[]{
                Items.COPPER_PICKAXE,
                Items.COPPER_SHOVEL,
                Items.COPPER_AXE,
                Items.COPPER_HOE,
                Items.COPPER_SWORD,
                Items.COPPER_HELMET,
                Items.COPPER_CHESTPLATE,
                Items.COPPER_LEGGINGS,
                Items.COPPER_BOOTS,
                Items.COPPER_HORSE_ARMOR
        }), RecipeCategory.MISC, Items.COPPER_NUGGET, 0.1F, 100)
                .unlockedBy("has_copper_pickaxe", this.has(Items.COPPER_PICKAXE))
                .unlockedBy("has_copper_shovel", this.has(Items.COPPER_SHOVEL))
                .unlockedBy("has_copper_axe", this.has(Items.COPPER_AXE))
                .unlockedBy("has_copper_hoe", this.has(Items.COPPER_HOE))
                .unlockedBy("has_copper_sword", this.has(Items.COPPER_SWORD))
                .unlockedBy("has_copper_helmet", this.has(Items.COPPER_HELMET))
                .unlockedBy("has_copper_chestplate", this.has(Items.COPPER_CHESTPLATE))
                .unlockedBy("has_copper_leggings", this.has(Items.COPPER_LEGGINGS))
                .unlockedBy("has_copper_boots", this.has(Items.COPPER_BOOTS))
                .unlockedBy("has_copper_horse_armor", this.has(Items.COPPER_HORSE_ARMOR))
                .save(this.output,"%s:%s".formatted(CopperAdditions.MODID, getBlastingRecipeName(Items.COPPER_NUGGET)));

    }

    private void craftingRecipeGenerator() {
        this.nineBlockStorageRecipes(
                RecipeCategory.MISC, Items.COPPER_NUGGET,
                RecipeCategory.MISC, net.minecraft.world.item.Items.COPPER_INGOT,
                CopperAdditions.MODID + ":copper_ingot_from_nuggets",  "copper_ingot",
                CopperAdditions.MODID + ":copper_nugget", "copper_nugget"
                );
        this.shaped(RecipeCategory.TOOLS, Items.COPPER_HOE)
                .define('#', net.minecraft.world.item.Items.STICK)
                .define('X', CopperAdditionsTags.Items.COPPER_TOOL_MATERIALS)
                .pattern("XX")
                .pattern(" #")
                .pattern(" #")
                .unlockedBy("has_copper", this.has(CopperAdditionsTags.Items.COPPER_TOOL_MATERIALS))
                .save(this.output);
        this.shaped(RecipeCategory.TOOLS, Items.COPPER_PICKAXE)
                .define('#', net.minecraft.world.item.Items.STICK)
                .define('X', CopperAdditionsTags.Items.COPPER_TOOL_MATERIALS)
                .pattern("XXX")
                .pattern(" # ")
                .pattern(" # ")
                .unlockedBy("has_copper", this.has(CopperAdditionsTags.Items.COPPER_TOOL_MATERIALS))
                .save(this.output);
        this.shaped(RecipeCategory.TOOLS,Items.COPPER_SHOVEL)
                .define('#', net.minecraft.world.item.Items.STICK)
                .define('X', CopperAdditionsTags.Items.COPPER_TOOL_MATERIALS)
                .pattern("X")
                .pattern("#")
                .pattern("#").unlockedBy("has_copper", this.has(CopperAdditionsTags.Items.COPPER_TOOL_MATERIALS)).save(this.output);
        this.shaped(RecipeCategory.TOOLS, Items.COPPER_AXE)
                .define('#', net.minecraft.world.item.Items.STICK)
                .define('X', CopperAdditionsTags.Items.COPPER_TOOL_MATERIALS)
                .pattern("XX")
                .pattern("X#")
                .pattern(" #").unlockedBy("has_copper", this.has(CopperAdditionsTags.Items.COPPER_TOOL_MATERIALS))
                .save(this.output);
        this.shaped(RecipeCategory.COMBAT, Items.COPPER_SWORD)
                .define('#', net.minecraft.world.item.Items.STICK)
                .define('X', CopperAdditionsTags.Items.COPPER_TOOL_MATERIALS)
                .pattern("X")
                .pattern("X")
                .pattern("#").unlockedBy("has_copper", this.has(CopperAdditionsTags.Items.COPPER_TOOL_MATERIALS))
                .save(this.output);
        this.shaped(RecipeCategory.COMBAT, Items.COPPER_HELMET)
                .define('X', Tags.Items.INGOTS_COPPER)
                .pattern("XXX")
                .pattern("X X").unlockedBy("has_iron_ingot", this.has(Tags.Items.INGOTS_COPPER))
                .save(this.output);
        this.shaped(RecipeCategory.COMBAT, Items.COPPER_BOOTS)
                .define('X', Tags.Items.INGOTS_COPPER)
                .pattern("X X")
                .pattern("X X").unlockedBy("has_iron_ingot", this.has(Tags.Items.INGOTS_COPPER))
                .save(this.output);
        this.shaped(RecipeCategory.COMBAT, Items.COPPER_CHESTPLATE)
                .define('X', Tags.Items.INGOTS_COPPER)
                .pattern("X X")
                .pattern("XXX")
                .pattern("XXX").unlockedBy("has_iron_ingot", this.has(Tags.Items.INGOTS_COPPER))
                .save(this.output);
        this.shaped(RecipeCategory.COMBAT, Items.COPPER_LEGGINGS)
                .define('X', Tags.Items.INGOTS_COPPER)
                .pattern("XXX")
                .pattern("X X")
                .pattern("X X").unlockedBy("has_iron_ingot", this.has(Tags.Items.INGOTS_COPPER))
                .save(this.output);
    } 

    public static class Runner extends RecipeProvider.Runner {

        public Runner(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries) {
            super(packOutput, registries);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
            return new RecipeGenerator(provider,recipeOutput);
        }

        @Override
        public String getName() {
            return "Copper Additions Recipe";
        }
    }
}
