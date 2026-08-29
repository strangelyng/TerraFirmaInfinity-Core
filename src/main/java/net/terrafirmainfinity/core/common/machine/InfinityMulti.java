package net.terrafirmainfinity.core.common.machine;

import net.terrafirmainfinity.core.common.machine.multiblock.multi.ElectrolyticCell;
import net.terrafirmainfinity.core.common.machine.multiblock.multi.BasicGravitySeparator;
import net.terrafirmainfinity.core.common.machine.multiblock.multi.MetallurgicalConverter;

public class InfinityMulti {
    public static void init() {
        ElectrolyticCell.init();
        BasicGravitySeparator.init();
        MetallurgicalConverter.init();
    }
}
