package net.terrafirmainfinity.core.common.cover;

import com.gregtechceu.gtceu.api.capability.ICoverable;
import com.gregtechceu.gtceu.api.cover.CoverBehavior;
import com.gregtechceu.gtceu.api.cover.CoverDefinition;
import com.gregtechceu.gtceu.api.machine.TickableSubscription;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.fluids.FluidUtil;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;

public class AirVentCover extends CoverBehavior {

    private TickableSubscription subscription;

    public AirVentCover(CoverDefinition definition, ICoverable coverHolder, Direction attachedSide) {
        super(definition, coverHolder, attachedSide);
    }

    @Override
    public boolean canAttach() {
        return super.canAttach() &&
                FluidUtil.getFluidHandler(coverHolder.getLevel(), coverHolder.getBlockPos(), attachedSide).isPresent();
    }

    @Override
    public void onLoad() {
        super.onLoad();
        subscription = coverHolder.subscribeServerTick(subscription, this::update);
    }

    @Override
    public void onRemoved() {
        super.onRemoved();
        if (subscription != null) {
            subscription.unsubscribe();
        }
    }

    // TODO: Only check if workable on neighbor change / Use GAS_COLLECTOR_RECIPE to fetch appropriate air
    public void update() {
        if (coverHolder.getOffsetTimer() % 20 != 0 ) {
            return;
        }

        Level level = coverHolder.getLevel();

        if (level.dimension() != Level.OVERWORLD) return;

        if (!level.getBlockState(coverHolder.getBlockPos().relative(attachedSide)).isAir()) {
            return;
        }

        FluidUtil.getFluidHandler(coverHolder.getLevel(), coverHolder.getBlockPos(), attachedSide)
                .ifPresent(h -> h.fill(GTMaterials.Air.getFluid(100), IFluidHandler.FluidAction.EXECUTE));
    }
}
