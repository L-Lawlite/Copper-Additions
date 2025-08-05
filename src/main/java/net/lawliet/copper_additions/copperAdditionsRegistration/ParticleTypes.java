package net.lawliet.copper_additions.copperAdditionsRegistration;

import net.lawliet.copper_additions.CopperAdditions;
import net.minecraft.core.particles.SimpleParticleType;

import java.util.function.Supplier;

public class ParticleTypes {
    public static void init() {}

    public static final Supplier<SimpleParticleType> COPPER_FIRE_FLAME;

    static {
        COPPER_FIRE_FLAME = CopperAdditions.PARTICLE_TYPES.register("copper_fire_flame", () -> new SimpleParticleType(false));

    }

}
