package net.terrafirmainfinity.core.common.data.tag;

import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import com.tterrag.registrate.providers.RegistrateTagsProvider;
import net.terrafirmainfinity.core.common.data.InfinityTagPrefix;

public class InfinityTagPrefixTags {
    public static void init(RegistrateTagsProvider.IntrinsicImpl<TagPrefix> provider) {
        provider.addTag(CustomTags.INHALATION_TRIGGER_PREFIXES)
                .add(InfinityTagPrefix.powder.getKey());
    }
}
