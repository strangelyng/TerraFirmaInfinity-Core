package net.terrafirmainfinity.core.common.data.tag;

import com.gregtechceu.gtceu.utils.TagUtil;
import com.tterrag.registrate.providers.RegistrateTagsProvider;
import net.dries007.tfc.common.items.TFCItems;
import net.dries007.tfc.util.Metal;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.terrafirmainfinity.core.InfinityCore;

public class InfinityItemTags {
    public static final TagKey<Item> ORE_PROSPECTORS_COPPER = itemTag("tools/ore_prospectors/copper");
    public static final TagKey<Item> ORE_PROSPECTORS_BRONZE = itemTag("tools/ore_prospectors/bronze");
    public static final TagKey<Item> ORE_PROSPECTORS_WROUGHT_IRON = itemTag("tools/ore_prospectors/wrought_iron");
    public static final TagKey<Item> ORE_PROSPECTORS_STEEL = itemTag("tools/ore_prospectors/steel");
    public static final TagKey<Item> ORE_PROSPECTORS_BLACK_STEEL = itemTag("tools/ore_prospectors/black_steel");
    public static final TagKey<Item> ORE_PROSPECTORS_BLUE_STEEL = itemTag("tools/ore_prospectors/blue_steel");
    public static final TagKey<Item> ORE_PROSPECTORS_RED_STEEL = itemTag("tools/ore_prospectors/red_steel");

    public static final TagKey<Item> CARBON_FUELS = itemTag("carbon_fuels");

    public static void init(RegistrateTagsProvider<Item> provider) {
        provider.addTag(ORE_PROSPECTORS_COPPER)
                .add(propickOf(Metal.COPPER));

        provider.addTag(ORE_PROSPECTORS_BRONZE)
                .add(propickOf(Metal.BRONZE))
                .add(propickOf(Metal.BISMUTH_BRONZE))
                .add(propickOf(Metal.BLACK_BRONZE));

        provider.addTag(ORE_PROSPECTORS_WROUGHT_IRON)
                .add(propickOf(Metal.WROUGHT_IRON));

        provider.addTag(ORE_PROSPECTORS_STEEL)
                .add(propickOf(Metal.STEEL));

        provider.addTag(ORE_PROSPECTORS_BLACK_STEEL)
                .add(propickOf(Metal.BLACK_STEEL));

        provider.addTag(ORE_PROSPECTORS_BLUE_STEEL)
                .add(propickOf(Metal.BLUE_STEEL));

        provider.addTag(ORE_PROSPECTORS_RED_STEEL)
                .add(propickOf(Metal.RED_STEEL));

        provider.addTag(CARBON_FUELS)
                .addOptionalTag(TagUtil.createItemTag("dusts/charcoal"))
                .addOptionalTag(TagUtil.createItemTag("small_dusts/charcoal"))
                .addOptionalTag(TagUtil.createItemTag("tiny_dusts/charcoal"))
                .addOptionalTag(TagUtil.createItemTag("gems/charcoal"))
                .addOptionalTag(TagUtil.createItemTag("dusts/coal"))
                .addOptionalTag(TagUtil.createItemTag("small_dusts/coal"))
                .addOptionalTag(TagUtil.createItemTag("tiny_dusts/coal"))
                .addOptionalTag(TagUtil.createItemTag("gems/coal"))
                .addOptionalTag(TagUtil.createItemTag("dusts/coke"))
                .addOptionalTag(TagUtil.createItemTag("gems/coke"))
                .addOptionalTag(TagUtil.createItemTag("dusts/anthracite"))
                .addOptionalTag(TagUtil.createItemTag("small_dusts/anthracite"))
                .addOptionalTag(TagUtil.createItemTag("tiny_dusts/anthracite"))
                .addOptionalTag(TagUtil.createItemTag("gems/anthracite"));
    }

    private static ResourceKey<Item> propickOf(Metal metal) {
        return TFCItems.METAL_ITEMS.get(metal).get(Metal.ItemType.PROPICK).key();
    }

    private static TagKey<Item> itemTag(String tagPath) {
        return TagKey.create(Registries.ITEM, InfinityCore.id(tagPath));
    }
}