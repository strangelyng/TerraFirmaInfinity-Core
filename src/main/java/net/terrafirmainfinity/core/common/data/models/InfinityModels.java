package net.terrafirmainfinity.core.common.data.models;

import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateBlockstateProvider;
import com.tterrag.registrate.util.nullness.NonNullBiConsumer;
import net.minecraft.world.level.block.Block;
import net.terrafirmainfinity.core.InfinityCore;

public class InfinityModels {
    public static NonNullBiConsumer<DataGenContext<Block, Block>, RegistrateBlockstateProvider> createDwarvenCasingModel(String material) {
        return (ctx, prov) -> {
            prov.simpleBlock(ctx.getEntry(), prov.models().cubeBottomTop(ctx.getName(),
                    InfinityCore.id("block/casings/dwarven/%s/side".formatted(material)),
                    InfinityCore.id("block/casings/dwarven/%s/bottom".formatted(material)),
                    InfinityCore.id("block/casings/dwarven/%s/top".formatted(material))));
        };
    }
}
