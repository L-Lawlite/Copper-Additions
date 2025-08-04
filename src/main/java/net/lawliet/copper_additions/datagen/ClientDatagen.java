package net.lawliet.copper_additions.datagen;

import com.mojang.logging.LogUtils;
import net.lawliet.copper_additions.CopperAdditions;
import net.lawliet.copper_additions.datagen.lang.LanguageGenerator;
import net.lawliet.copper_additions.datagen.model.EquipmentRenderGenerator;
import net.lawliet.copper_additions.datagen.model.ModelGenerator;
import net.lawliet.copper_additions.datagen.recipes.RecipeGenerator;
import net.lawliet.copper_additions.datagen.tags.BlockTagGenerator;
import net.lawliet.copper_additions.datagen.tags.ItemTagGenerator;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.slf4j.Logger;

@EventBusSubscriber(modid = CopperAdditions.MODID)
public class ClientDatagen {
    private static final Logger LOGGER = LogUtils.getLogger();

    @SubscribeEvent
    public static void generate(GatherDataEvent.Client event) {
        LOGGER.info("Generating Client Files...");
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();

        generator.addProvider(true,new LanguageGenerator(packOutput,"en_us"));
        event.createProvider(ModelGenerator::new);
        event.createProvider(EquipmentRenderGenerator::new);
        event.createProvider(RecipeGenerator.Runner::new);
//        event.createProvider(DataMapGenerator::new);


//        event.createProvider((output,lookupProvider) -> new LootTableProvider(
//                output,
//                Set.of(),
//                List.of(
//                    new LootTableProvider.SubProviderEntry(
//                            LootTableGenerator::new,
//                            LootContextParamSets.BLOCK
//                    )
//                ),
//                lookupProvider
//        ));
//

        event.createBlockAndItemTags(BlockTagGenerator::new, ItemTagGenerator::new);



    }
}
