package net.terrafirmainfinity.core.common.data;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.cover.CoverDefinition;
import com.gregtechceu.gtceu.client.renderer.cover.IOCoverRenderer;
import com.gregtechceu.gtceu.common.cover.ConveyorCover;
import com.gregtechceu.gtceu.common.cover.PumpCover;
import net.minecraft.core.Holder;
import net.terrafirmainfinity.core.common.cover.AirVentCover;

import static net.terrafirmainfinity.core.InfinityCore.InfinityRegistrate;

public class InfinityCovers {
    public final static Holder<CoverDefinition> AIR_VENT = InfinityRegistrate.cover("air_vent", AirVentCover::new);

    public final static Holder<CoverDefinition> STEAM_CONVEYOR = InfinityRegistrate.cover("steam_conveyor",
            ((definition, coverable, side) ->
                    new ConveyorCover(definition, coverable, side, GTValues.ULV, 4)),
            () -> () -> new IOCoverRenderer(
                    GTCEu.id("block/cover/conveyor"),
                    null,
                    GTCEu.id("block/cover/conveyor_emissive"),
                    GTCEu.id("block/cover/conveyor_inverted_emissive")
            ));

    public final static Holder<CoverDefinition> STEAM_PUMP = InfinityRegistrate.cover("steam_pump",
            ((definition, coverable, side) ->
                    new PumpCover(definition, coverable, side, GTValues.ULV, 32)),
            () -> () -> IOCoverRenderer.PUMP_LIKE_COVER_RENDERER);

    public static void init() {}
}