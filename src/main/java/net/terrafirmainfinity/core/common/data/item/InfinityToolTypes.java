package net.terrafirmainfinity.core.common.data.item;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.item.tool.GTToolType;
import net.dries007.tfc.common.TFCTags;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.Tags;
import net.terrafirmainfinity.core.common.data.item.tool.behavior.ChiselBehavior;
import net.terrafirmainfinity.core.common.data.item.tool.behavior.MaceBehavior;

public class InfinityToolTypes {

    // TODO: Propick

    public static final GTToolType CHISEL = GTToolType.builder("chisel")
            .idFormat("%s_chisel")
            .toolTag(GTToolType.ToolItemTagType.MATCH, TFCTags.Items.TOOLS_CHISEL)
            .harvestTag(TFCTags.Blocks.MINEABLE_WITH_CHISEL)
            .definition(b -> b.cannotAttack().behaviors(ChiselBehavior.INSTANCE))
            .toolClassNames("chisel")
            .defaultAbilities(ItemAbilities.PICKAXE_DIG)
            .materialAmount(GTValues.M)
            .build();

    // TODO: Javelin

    public static final GTToolType MACE = GTToolType.builder("mace")
            .idFormat("%s_mace")
            .toolTag(GTToolType.ToolItemTagType.MATCH, Tags.Items.TOOLS_MACE)
            .definition(b -> b.attacking().attackDamage(4.0f).attackSpeed(-3.4f).behaviors(MaceBehavior.INSTANCE))
            .toolClassNames("mace")
            .materialAmount(GTValues.M * 2)
            .build();
}
