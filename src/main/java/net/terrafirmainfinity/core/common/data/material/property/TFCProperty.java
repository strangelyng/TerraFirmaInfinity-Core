package net.terrafirmainfinity.core.common.data.material.property;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.IMaterialProperty;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.MaterialProperties;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.fluids.store.FluidStorageKeys;
import net.minecraft.world.level.material.Fluid;
import org.jetbrains.annotations.Nullable;

public class TFCProperty implements IMaterialProperty {
    /**
     * Value from net.dries007.tfc.data.providers.BuiltInFluidHeat
     * Used for heat capacity calculations
     */
    private static final float HEAT_CAPACITY = 0.003f;

    /**
     * The Forging Temperature (in Celsius) of this Material.
     * This is typically 60% of the Material's melting temperature.
     */
    private int forgingTemp;
    /**
     * The Welding Temperature (in Celsius) of this Material.
     * This is typically 80% of the Material's melting temperature.
     */
    private int weldingTemp;
    /**
     * The Melting Temperature (in Celsius) of this Material.
     */
    private int meltTemp;
    /**
     * The Base Heat Capacity of this Material.
     * Higher values make items take longer to heat up.
     *
     * Also used to calculate specificHeatCapacity for metal fluids.
     * Example values include:
     *
     * Gold, 0.6; Silver, 0.48; Iron, 0.35; Zinc, 0.21; Tin, 0.14
     */
    private float baseHeatCapacity;
    /**
     * The TFC Anvil Tier at which this Material can be worked.
     *
     * Welding is usually possible one tier lower.
     */
    private int anvilTier;

    /**
     * The GTMaterial to link to this TFCProperty.
     * Used to determine the output fluid when melting in TFC contexts.
     * May be null.
     */
    @Nullable
    private Material outputMaterial;

    public TFCProperty(Material outputMaterial, float baseHeatCapacity, int anvilTier) {
        setOutputMaterial(outputMaterial);

        if (outputMaterial.getFluid(FluidStorageKeys.LIQUID) != null) {
            int fluidTemp = outputMaterial.getFluid(FluidStorageKeys.LIQUID).getFluidType().getTemperature();

            int normalizedTemp = fluidTemp - 273;

            setMeltTemp(normalizedTemp);
            setWeldingTemp((int) (normalizedTemp * 0.8));
            setForgingTemp((int) (normalizedTemp * 0.6));
            setAnvilTier(anvilTier);
        } else throw new IllegalArgumentException("Could not create TFC Property from Material: Missing fluid!");

        setBaseHeatCapacity(baseHeatCapacity);
        setAnvilTier(anvilTier);
    }

    public TFCProperty(int meltTemp, float baseHeatCapacity, int anvilTier) {
        this((int) (meltTemp * 0.6), (int) (meltTemp * 0.8), meltTemp, baseHeatCapacity, null, anvilTier);
    }

    public TFCProperty(int meltTemp, float baseHeatCapacity, @Nullable Material outputMaterial, int anvilTier) {
        this((int) (meltTemp * 0.6), (int) (meltTemp * 0.8), meltTemp, baseHeatCapacity, outputMaterial, anvilTier);
    }

    public TFCProperty(int forgingTemp, int weldingTemp, int meltTemp, float baseHeatCapacity, int anvilTier) {
        this(forgingTemp, weldingTemp, meltTemp,  baseHeatCapacity, null, anvilTier);
    }

    public TFCProperty(int forgingTemp, int weldingTemp, int meltTemp, float baseHeatCapacity, @Nullable Material outputMaterial, int anvilTier) {
        setForgingTemp(forgingTemp);
        setWeldingTemp(weldingTemp);
        setMeltTemp(meltTemp);
        setBaseHeatCapacity(baseHeatCapacity);
        setOutputMaterial(outputMaterial);
        setAnvilTier(anvilTier);
    }

    public void setForgingTemp(int forgingTemp) {
        this.forgingTemp = Math.max(forgingTemp, 0);
    }

    public int getForgingTemp() {
        return this.forgingTemp;
    }

    public void setWeldingTemp(int weldingTemp) {
        this.weldingTemp = Math.max(weldingTemp, 0);
    }

    public int getWeldingTemp() {
        return this.weldingTemp;
    }

    public void setMeltTemp(int meltTemp) {
        this.meltTemp = Math.max(meltTemp, 0);
    }

    public int getMeltTemp() {
        return this.meltTemp;
    }

    public void setBaseHeatCapacity(float baseHeatCapacity) {
        this.baseHeatCapacity = 0.35f;

        if (baseHeatCapacity > 0 && baseHeatCapacity < 1) {
            this.baseHeatCapacity = baseHeatCapacity;
        }
    }

    /**
     *
     * @param units Represents the amount of material, 1 Ingot = 100
     */
    public float getHeatCapacity(int units) {
        return (this.getSpecificHeatCapacity() / HEAT_CAPACITY) * (units / 100f);
    }

    public float getHeatCapacity(TagPrefix tagPrefix) {
        return (this.getSpecificHeatCapacity() / HEAT_CAPACITY) * (float) (tagPrefix.materialAmount() / GTValues.M);
    }

    public float getSpecificHeatCapacity() {
        return HEAT_CAPACITY / this.baseHeatCapacity;
    }

    public @Nullable Material getOutputMaterial() {
        return outputMaterial;
    }

    public @Nullable Fluid getOutputFluid() {
        if (this.outputMaterial != null && this.outputMaterial.hasFluid()) {
            return this.outputMaterial.getFluid();
        } else return null;
    }

    public void setOutputMaterial(@Nullable Material outputMaterial) {
        this.outputMaterial = outputMaterial;
    }

    public void setAnvilTier(int anvilTier) {
        this.anvilTier = 0;

        if (anvilTier < 7 && anvilTier > 0) {
            this.anvilTier = anvilTier;
        }
    }

    public int getWeldingTier(int anvilTier) {
        if (anvilTier <= 0) {
            return 0;
        } else return this.anvilTier--;
    }

    @Override
    public void verifyProperty(MaterialProperties properties) {
    }
}
