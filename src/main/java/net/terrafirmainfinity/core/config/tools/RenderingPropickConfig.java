/*
 * This class is adapted from TerraFirmaGreg-Core - Modern, which is licensed under LGPL-3.0
 * A copy of the license can be found here:
 * https://www.gnu.org/licenses/lgpl-3.0.en.html
 */

package net.terrafirmainfinity.core.config.tools;

import net.dries007.tfc.config.ConfigBuilder;
import net.terrafirmainfinity.core.util.InfinityUtil;

import java.util.function.Supplier;

/**
 * Used to compactly configure tier VI prospector's pick settings in {@link net.terrafirmainfinity.core.config.ServerConfig}.
 *
 * @param inner               base prospector settings.
 * @param preciselyRenderVein if true, ores in an ore vein will each be represented by a particle.
 * @see PropickConfig
 */
public record RenderingPropickConfig(PropickConfig inner, Supplier<Boolean> preciselyRenderVein) {
    public static RenderingPropickConfig build(ConfigBuilder builder, String name, int searchLength, int searchWidth, boolean preciselyRenderVein) {
        return new RenderingPropickConfig(
                PropickConfig.build(builder, name, searchLength, searchWidth),
                builder.comment(String.format("Should the %s Prospector's Pick render particles per vein (vague)?", InfinityUtil.snakecaseToTitle(name)),
                                "Setting false will render particles per block (precise).",
                                String.format("Default: %s", preciselyRenderVein))
                        .define(String.format("%sProspectorRender",
                                InfinityUtil.snakecaseToCamel(name)), preciselyRenderVein));
    }
}
