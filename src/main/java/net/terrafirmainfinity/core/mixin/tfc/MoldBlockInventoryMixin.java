package net.terrafirmainfinity.core.mixin.tfc;

import net.dries007.tfc.common.blockentities.MoldTableBlockEntity;
import net.dries007.tfc.common.capabilities.ItemCapabilities;
import net.dries007.tfc.util.data.FluidHeat;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.terrafirmainfinity.core.common.data.tag.InfinityFluidTags;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(MoldTableBlockEntity.MoldBlockInventory.class)
public abstract class MoldBlockInventoryMixin {
    @Shadow
    @Final
    private MoldTableBlockEntity moldTable;

    @Shadow
    protected abstract Optional<IFluidHandler> getMoldFluidHandler();

    @Inject(method = "fill", at = @At(value = "HEAD"))
    public void tfinfinity$fillMolten(FluidStack fluid, IFluidHandler.FluidAction action, CallbackInfoReturnable<Integer> cir) {
        if (action == IFluidHandler.FluidAction.EXECUTE && fluid.is(InfinityFluidTags.MOLTEN)) {
            @Nullable FluidHeat fluidHeat = FluidHeat.get(fluid.getFluid());

            if (fluidHeat != null) {
                this.getMoldFluidHandler().ifPresent(handler -> {
                    if (handler.getFluidInTank(0).getAmount() < handler.getTankCapacity(0)) {
                        Optional.ofNullable(this.moldTable.getMoldStack().getCapability(ItemCapabilities.HEAT))
                                .ifPresent(heatCap -> heatCap.setTemperatureIfWarmer(fluidHeat.meltTemperature() + 100));

                        this.moldTable.markForSync();
                    }
                });
            }
        }
    }
}
