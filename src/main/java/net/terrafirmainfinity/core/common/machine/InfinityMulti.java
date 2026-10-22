package net.terrafirmainfinity.core.common.machine;

import net.terrafirmainfinity.core.common.machine.multiblock.multi.*;

public class InfinityMulti {
    public static void init() {
        IndustrialArcFurnace.init();
        ElectrolyticCell.init();
        SpiralSeparator.init();
        MetallurgicalConverter.init();
        FlashFurnace.init();
    }
}
