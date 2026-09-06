package net.terrafirmainfinity.core.common.machine;

import net.terrafirmainfinity.core.common.machine.multiblock.multi.ElectrolyticCell;
import net.terrafirmainfinity.core.common.machine.multiblock.multi.FlashFurnace;
import net.terrafirmainfinity.core.common.machine.multiblock.multi.SpiralSeparator;
import net.terrafirmainfinity.core.common.machine.multiblock.multi.MetallurgicalConverter;

public class InfinityMulti {
    public static void init() {
        ElectrolyticCell.init();
        SpiralSeparator.init();
        MetallurgicalConverter.init();
        FlashFurnace.init();
    }
}
