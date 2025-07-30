package net.lawliet.copper_additions.utility;

import net.lawliet.copper_additions.datagen.tags.CopperAdditionsTags;
import net.minecraft.world.item.ToolMaterial;

public class TootMaterialAddition {
    public static void init() {}

    public static final ToolMaterial COPPER = new ToolMaterial(CopperAdditionsTags.Blocks.INCORRECT_FOR_COPPER_TOOL, 190, 5.0F, 1.0F, 13, CopperAdditionsTags.Items.COPPER_TOOL_MATERIALS);

}
