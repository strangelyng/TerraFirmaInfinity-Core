package net.terrafirmainfinity.core.common.cover;

import com.gregtechceu.gtceu.api.capability.ICoverable;
import com.gregtechceu.gtceu.api.cover.CoverBehavior;
import com.gregtechceu.gtceu.api.cover.CoverDefinition;
import com.gregtechceu.gtceu.api.machine.ConditionalSubscriptionHandler;
import com.gregtechceu.gtceu.api.sync_system.annotations.SaveField;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidUtil;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.terrafirmainfinity.core.common.data.InfinityMaps;

public class AirVentCover extends CoverBehavior {
    @SaveField
    protected FluidStack dimensionAir;

    protected final ConditionalSubscriptionHandler subscriptionHandler;

    public AirVentCover(CoverDefinition definition, ICoverable coverHolder, Direction attachedSide) {
        super(definition, coverHolder, attachedSide);
        subscriptionHandler = new ConditionalSubscriptionHandler(coverHolder, this::update, this::isSubscriptionActive);
    }

    protected boolean isSubscriptionActive() {
        return isAttachedSideFree() && !getDimensionAir().isEmpty();
    }

    protected boolean isAttachedSideFree() {
        return coverHolder.getLevel().getBlockState(coverHolder.getBlockPos().relative(attachedSide)).isAir();
    }

    protected void setDimensionAir() {
        this.dimensionAir = InfinityMaps.getAirStackInDimension(coverHolder.getLevel().dimension());
    }

    @Override
    public boolean canAttach() {
        return super.canAttach() &&
                FluidUtil.getFluidHandler(coverHolder.getLevel(), coverHolder.getBlockPos(), attachedSide).isPresent();
    }

    @Override
    public void onLoad() {
        super.onLoad();
        setDimensionAir();
        subscriptionHandler.initialize(coverHolder.getLevel());
    }

    @Override
    public void onRemoved() {
        super.onRemoved();
        subscriptionHandler.unsubscribe();
    }

    @Override
    public void onNeighborChanged(Block block, BlockPos fromPos, boolean isMoving) {
        super.onNeighborChanged(block, fromPos, isMoving);
        subscriptionHandler.updateSubscription();
    }

    protected void update() {
        if (coverHolder.getOffsetTimer() % 20 != 0 ) {
            return;
        }

        FluidUtil.getFluidHandler(coverHolder.getLevel(), coverHolder.getBlockPos(), attachedSide)
                .ifPresent(h -> h.fill(getDimensionAir(), IFluidHandler.FluidAction.EXECUTE));

        subscriptionHandler.updateSubscription();
    }

    public FluidStack getDimensionAir() {
        return dimensionAir;
    }
}
