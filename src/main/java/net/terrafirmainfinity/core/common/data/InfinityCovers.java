package net.terrafirmainfinity.core.common.data;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.cover.CoverDefinition;
import com.gregtechceu.gtceu.client.renderer.cover.SimpleCoverRenderer;
import com.gregtechceu.gtceu.common.cover.ConveyorCover;
import com.gregtechceu.gtceu.common.cover.PumpCover;
import com.gregtechceu.gtceu.common.data.GTCovers;
import net.terrafirmainfinity.core.InfinityCore;
import net.terrafirmainfinity.core.common.cover.AirVentCover;

public class InfinityCovers {
    public final static CoverDefinition AIR_VENT = GTCovers.register(InfinityCore.id("air_vent"),
            AirVentCover::new, () ->
                    () -> new SimpleCoverRenderer(InfinityCore.id("block/cover/air_vent")));

    public final static CoverDefinition STEAM_CONVEYOR = GTCovers.register(InfinityCore.id("steam_conveyor"),
            ((definition, coverable, side) ->
                    new ConveyorCover(definition, coverable, side, GTValues.ULV, 4)),
            () -> GTCovers.CONVEYORS[0].getCoverRenderer());

    public final static CoverDefinition STEAM_PUMP = GTCovers.register(InfinityCore.id("steam_pump"),
            ((definition, coverable, side) ->
                    new PumpCover(definition, coverable, side, GTValues.ULV, 32)),
            () -> GTCovers.PUMPS[0].getCoverRenderer());

    public static void init() {}
}