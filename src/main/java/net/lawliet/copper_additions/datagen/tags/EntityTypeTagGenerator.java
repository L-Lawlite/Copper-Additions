package net.lawliet.copper_additions.datagen.tags;

import net.lawliet.copper_additions.CopperAdditions;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.world.entity.EntityType;

import java.util.concurrent.CompletableFuture;

public class EntityTypeTagGenerator extends EntityTypeTagsProvider {
    public EntityTypeTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider, CopperAdditions.MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider p_255894_) {
        this.tag(CopperAdditionsTags.EntityTypes.CAN_SPAWN_WITH_COPPER_ARMOR)
                .add(EntityType.SKELETON)
                .add(EntityType.BOGGED)
                .add(EntityType.STRAY)
                .add(EntityType.ZOMBIE)
                .add(EntityType.ZOMBIE_VILLAGER)
                .add(EntityType.HUSK);
    }
}
