package net.terrafirmainfinity.core.common.data;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.block.ICoilType;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.common.block.CoilBlock;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.StringRepresentable;
import net.terrafirmainfinity.core.common.data.material.InfinityMaterials;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class InfinityCoilBlock extends CoilBlock {
    public InfinityCoilBlock(Properties properties, ICoilType coilType) {
        super(properties, coilType);
    }

    public enum CoilType implements StringRepresentable, ICoilType {
        CUPRONICKEL("cupronickel", 800, 0, 1, 1, GTMaterials.Cupronickel, GTCEu.id("block/casings/coils/machine_coil_cupronickel")),
        NICHROME("nichrome", 1400, 1, 2, 1, GTMaterials.Nichrome, GTCEu.id("block/casings/coils/machine_coil_nichrome")),
        KANTHAL("kanthal", 1700, 2, 2, 2, GTMaterials.Kanthal, GTCEu.id("block/casings/coils/machine_coil_kanthal")),
        MOLYBDENUM_DISILICIDE("molybdenum_disilicide", 2100, 3, 3, 2, GTMaterials.MolybdenumDisilicide, GTCEu.id("block/casings/coils/machine_coil_molybdenum_disilicide")),
        WRE_ALLOY("wre_alloy", 3200, 4, 4, 2, InfinityMaterials.WReAlloy, GTCEu.id("block/casings/coils/machine_coil_hssg"));

        @NotNull
        private final String name;

        private final int coilTemperature;
        private final int tier;

        private final int level;
        private final int energyDiscount;
        @NotNull
        private final Holder<Material> material;
        @NotNull
        private final ResourceLocation texture;

        CoilType(String name, int coilTemperature, int tier, int level, int energyDiscount,
                 Holder<Material> material, ResourceLocation texture) {
            this.name = name;
            this.coilTemperature = coilTemperature;
            this.tier = tier;
            this.level = level;
            this.energyDiscount = energyDiscount;
            this.material = material;
            this.texture = texture;
        }

        @Override
        public String getName() {
            return this.name;
        }

        @Override
        public int getCoilTemperature() {
            return this.coilTemperature;
        }

        @Override
        public int getLevel() {
            return this.level;
        }

        @Override
        public int getEnergyDiscount() {
            return this.energyDiscount;
        }

        @Override
        public int getTier() {
            return this.tier;
        }

        @Override
        public @Nullable Holder<Material> getMaterial() {
            return this.material;
        }

        @Override
        public ResourceLocation getTexture() {
            return this.texture;
        }

        @Override
        public String getSerializedName() {
            return name;
        }
    }
}
