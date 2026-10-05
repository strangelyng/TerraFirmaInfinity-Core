package net.terrafirmainfinity.core.common.data.block;

import net.dries007.tfc.common.blocks.soil.IDirtBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Supplier;

public class LateriteBlock extends Block implements IDirtBlock {
    private final Supplier<? extends Block> grass;

    public LateriteBlock(Properties properties, Supplier<? extends Block> grass) {
        super(properties);
        this.grass = grass;
    }

    @Override
    public BlockState getGrass() {
        return grass.get().defaultBlockState();
    }
}
