package net.terrafirmainfinity.core.config;

import net.dries007.tfc.config.BaseConfig;
import net.dries007.tfc.config.ConfigBuilder;
import net.terrafirmainfinity.core.config.tools.PropickConfig;
import net.terrafirmainfinity.core.config.tools.RenderingPropickConfig;

public class ServerConfig extends BaseConfig {
    public final PropickConfig copperPropickConfig;
    public final PropickConfig bronzePropickConfig;
    public final PropickConfig wroughtIronPropickConfig;
    public final PropickConfig steelPropickConfig;
    public final PropickConfig blackSteelPropickConfig;
    public final RenderingPropickConfig blueSteelPropickConfig;
    public final RenderingPropickConfig redSteelPropickConfig;

    ServerConfig(ConfigBuilder builder) {
        builder.push("prospector_picks")
                .push("copper");
        copperPropickConfig = PropickConfig.build(builder, "copper", 15, 5);
        builder.pop().push("bronze");
        bronzePropickConfig = PropickConfig.build(builder, "bronze", 20, 8);
        builder.pop().push("wrought_iron");
        wroughtIronPropickConfig = PropickConfig.build(builder, "wrought_iron", 30, 10);
        builder.pop().push("steel");
        steelPropickConfig = PropickConfig.build(builder, "steel", 40, 12);
        builder.pop().push("black_steel");
        blackSteelPropickConfig = PropickConfig.build(builder, "black_steel", 50, 15);
        builder.pop().push("blue_steel");
        blueSteelPropickConfig = RenderingPropickConfig.build(builder, "blue_steel", 75, 15, false);
        builder.pop().push("red_steel");
        redSteelPropickConfig = RenderingPropickConfig.build(builder, "red_steel", 50, 25, true);
    }
}
