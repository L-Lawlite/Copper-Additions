package net.lawliet.copper_additions.utility;

import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureSlot;

public class ModelTemplatesAdditions {
    public static final ModelTemplate BARS_CAP = ModelTemplates.create("copper_additions:template_bars_cap", "_cap", TextureSlotAdditions.BARS, TextureSlot.EDGE);
    public static final ModelTemplate BARS_CAP_ALT = ModelTemplates.create("copper_additions:template_bars_cap_alt", "_cap_alt", TextureSlotAdditions.BARS, TextureSlot.EDGE);
    public static final ModelTemplate BARS_POST = ModelTemplates.create("copper_additions:template_bars_post", "_post", TextureSlotAdditions.BARS, TextureSlot.EDGE);
    public static final ModelTemplate BARS_POST_ENDS = ModelTemplates.create("copper_additions:template_bars_post_ends", "_post_ends", TextureSlotAdditions.BARS, TextureSlot.EDGE);
    public static final ModelTemplate BARS_POST_SIDE = ModelTemplates.create("copper_additions:template_bars_side", "_side", TextureSlotAdditions.BARS, TextureSlot.EDGE);
    public static final ModelTemplate BARS_POST_SIDE_ALT = ModelTemplates.create("copper_additions:template_bars_side_alt", "_side_alt", TextureSlotAdditions.BARS, TextureSlot.EDGE);

}
