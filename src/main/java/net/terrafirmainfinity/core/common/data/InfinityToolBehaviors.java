package net.terrafirmainfinity.core.common.data;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.item.tool.behavior.ToolBehaviorType;
import com.gregtechceu.gtceu.api.registry.GTRegistries;
import net.terrafirmainfinity.core.common.data.item.tool.behavior.ChiselBehavior;
import net.terrafirmainfinity.core.common.data.item.tool.behavior.MaceBehavior;

public class InfinityToolBehaviors {

    // TODO: Propick

    public static final ToolBehaviorType<ChiselBehavior> CHISEL = GTRegistries.register(GTRegistries.TOOL_BEHAVIORS,
            GTCEu.id("chisel"), new ToolBehaviorType<>(ChiselBehavior.CODEC, ChiselBehavior.STREAM_CODEC));

    // TODO: Javelin

    public static final ToolBehaviorType<MaceBehavior> MACE = GTRegistries.register(GTRegistries.TOOL_BEHAVIORS,
            GTCEu.id("mace"), new ToolBehaviorType<>(MaceBehavior.CODEC, MaceBehavior.STREAM_CODEC));

    public static void init() {}
}
