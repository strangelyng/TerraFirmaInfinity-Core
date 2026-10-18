package net.terrafirmainfinity.core.client.model.runtimegen;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet;
import com.gregtechceu.gtceu.api.item.tool.GTToolType;
import com.gregtechceu.gtceu.data.pack.GTDynamicResourcePack;
import com.gregtechceu.gtceu.utils.GTUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.models.model.DelegatedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class InfinityToolItemModelGenerator {

    private static final Set<InfinityToolItemModelGenerator> MODELS = new HashSet<>();

    public static void reinitModels() {
        for (InfinityToolItemModelGenerator model : MODELS) {
            ResourceLocation itemId = BuiltInRegistries.ITEM.getKey(model.item);
            GTDynamicResourcePack.addItemModel(itemId,
                    new DelegatedModel(getToolItemModelPath(model.iconSet, model.toolType)));
        }
    }

    private final Item item;
    private final GTToolType toolType;
    private final MaterialIconSet iconSet;

    private InfinityToolItemModelGenerator(Item item, GTToolType toolType, MaterialIconSet iconSet) {
        this.item = item;
        this.toolType = toolType;
        this.iconSet = iconSet;
    }

    public static void add(Item item, GTToolType toolType, MaterialIconSet iconSet) {
        MODELS.add(new InfinityToolItemModelGenerator(item, toolType, iconSet));
    }

    public static ResourceLocation getToolItemModelPath(@NotNull MaterialIconSet materialIconSet, GTToolType gtToolType) {
        MaterialIconSet toolIconSet = materialIconSet;
        if (!toolIconSet.isRootIconset && GTCEu.isClientSide() && Minecraft.getInstance() != null &&
            Minecraft.getInstance().getResourceManager() != null) {
            while (toolIconSet != null && !toolIconSet.isRootIconset) {
                ResourceLocation location = toolIconSet.id
                        .withPath(String.format("models/item/tools/%s/%s.json", toolIconSet.getName(), gtToolType.name));

                if (GTUtil.resourceExists(location))
                    break;
                toolIconSet = toolIconSet.parentIconset;
            }
        }

        Objects.requireNonNull(toolIconSet);

        ResourceLocation location = toolIconSet.id
                .withPath(String.format("item/tools/%s/%s", toolIconSet.getName(), gtToolType.name));

        return location;
    }
}
