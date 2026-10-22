package net.terrafirmainfinity.core.common.data.material.property;

import com.gregtechceu.gtceu.api.data.chemical.material.properties.IMaterialProperty;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.MaterialProperties;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey;

public class ElectrodeProperty implements IMaterialProperty {
    private int durability;
    private int efficiency;

    public ElectrodeProperty(int efficiency, int durability) {
        this.efficiency = efficiency;
        this.durability = durability;
    }

    public void setEfficiency(int efficiency) {
        if (efficiency <= 0) throw new IllegalArgumentException("Electrode Efficiency must be greater than zero!");
        this.efficiency = efficiency;
    }

    public void setDurability(int durability) {
        if (durability <= 0) throw new IllegalArgumentException("Electrode Durability must be greater than zero!");
        this.durability = durability;
    }

    @Override
    public void verifyProperty(MaterialProperties properties) {
        properties.ensureSet(PropertyKey.INGOT, true);
    }

    public int getDurability() {
        return durability;
    }

    public int getEfficiency() {
        return efficiency;
    }
}
