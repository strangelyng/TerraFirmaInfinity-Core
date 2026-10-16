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
 * Used to compactly configure prospector's pick settings in {@link net.terrafirmainfinity.core.config.ServerConfig}.
 *
 * @param searchLength Length of prospector pick's search area.
 * @param searchWidth  Half width (radius) of prospector pick's search area.
 */
public record PropickConfig(Supplier<Integer> searchLength, Supplier<Integer> searchWidth) {
    public static PropickConfig build(ConfigBuilder builder, String name, int searchLength, int searchWidth) {
        return new PropickConfig(
                builder.comment("Length of search area.")
                        .define(String.format("%sOreProspectorLength", InfinityUtil.snakecaseToCamel(name)),
                                searchLength, 0, 200),
                builder.comment("Half the width of the search area.",
                                "Example: If you want a 20x20 set the value to 10")
                        .define(String.format("%sOreProspectorHalfWidth", InfinityUtil.snakecaseToCamel(name)),
                                searchWidth, 0, 50));
    }
}
