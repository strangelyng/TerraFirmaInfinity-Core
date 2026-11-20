package net.terrafirmainfinity.core.common.machine.multiblock.multi;

import com.gregtechceu.gtceu.api.machine.feature.IMachineFeature;
import net.terrafirmainfinity.core.common.machine.part.FuelPartMachine;

public interface IFueledMultiblock extends IMachineFeature {
    FuelPartMachine<?> getFuelInput();
}
