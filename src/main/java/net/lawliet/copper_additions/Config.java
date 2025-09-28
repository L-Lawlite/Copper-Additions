package net.lawliet.copper_additions;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Neo's config APIs
public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    private static final ModConfigSpec.DoubleValue COPPER_ARMOR_SPAWN_CHANCE = BUILDER
            .comment("Chance to spawn Copper Armor on mobs", "This chance apply after the initial vanilla chance fail", "and might need adjusting to match 1.21.9")
            .defineInRange("copperArmorSpawnChance", 0.37D, 0.0D, 1.0D);

    static final ModConfigSpec SPEC = BUILDER.build();

    public static double copperArmorSpawnChance;


    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        //Fill static values here
        copperArmorSpawnChance = COPPER_ARMOR_SPAWN_CHANCE.get();
    }
}
