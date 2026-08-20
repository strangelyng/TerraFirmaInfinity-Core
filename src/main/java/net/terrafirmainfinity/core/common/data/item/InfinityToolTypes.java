package net.terrafirmainfinity.core.common.data.item;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.item.tool.GTToolType;
import com.gregtechceu.gtceu.api.item.tool.ToolHelper;
import com.gregtechceu.gtceu.common.data.GTSoundEntries;
import com.gregtechceu.gtceu.common.data.item.GTItemAbilities;
import com.gregtechceu.gtceu.common.item.tool.behavior.*;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import net.dries007.tfc.common.TFCTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
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

    // New Electric Tool Tiers

    public static final GTToolType CHAINSAW_MV = GTToolType.builder("mv_chainsaw")
            .idFormat("mv_%s_chainsaw")
            .toolTag(GTToolType.ToolItemTagType.MATCH, ItemTags.AXES)
            .toolTag(GTToolType.ToolItemTagType.MATCH, CustomTags.TOOLS_CHAINSAW)
            .harvestTag(BlockTags.MINEABLE_WITH_AXE)
            .harvestTag(BlockTags.SWORD_EFFICIENT)
            .harvestTag(BlockTags.MINEABLE_WITH_HOE)
            .definition(b -> b
                    .blockBreaking().efficiencyMultiplier(2.5F)
                    .attackDamage(3.0F).attackSpeed(-3.2F)
                    .brokenStack(ToolHelper.SUPPLY_POWER_UNIT_MV)
                    .behaviors(HarvestIceBehavior.INSTANCE, DisableShieldBehavior.INSTANCE, TreeFellingBehavior.INSTANCE))
            .sound(GTSoundEntries.CHAINSAW_TOOL, true)
            .electric(GTValues.MV)
            .toolClasses(GTToolType.AXE)
            .defaultAbilities(ItemAbilities.AXE_DIG, ItemAbilities.SWORD_DIG, ItemAbilities.HOE_DIG, GTItemAbilities.SAW_DIG)
            .build();

    public static final GTToolType CHAINSAW_EV = GTToolType.builder("ev_chainsaw")
            .idFormat("ev_%s_chainsaw")
            .toolTag(GTToolType.ToolItemTagType.MATCH, ItemTags.AXES)
            .toolTag(GTToolType.ToolItemTagType.MATCH, CustomTags.TOOLS_CHAINSAW)
            .harvestTag(BlockTags.MINEABLE_WITH_AXE)
            .harvestTag(BlockTags.SWORD_EFFICIENT)
            .harvestTag(BlockTags.MINEABLE_WITH_HOE)
            .definition(b -> b
                    .blockBreaking().efficiencyMultiplier(3.5F)
                    .attackDamage(5.0F).attackSpeed(-3.2F)
                    .brokenStack(ToolHelper.SUPPLY_POWER_UNIT_EV)
                    .behaviors(HarvestIceBehavior.INSTANCE, DisableShieldBehavior.INSTANCE, TreeFellingBehavior.INSTANCE))
            .sound(GTSoundEntries.CHAINSAW_TOOL, true)
            .electric(GTValues.EV)
            .toolClasses(GTToolType.AXE)
            .defaultAbilities(ItemAbilities.AXE_DIG, ItemAbilities.SWORD_DIG, ItemAbilities.HOE_DIG, GTItemAbilities.SAW_DIG)
            .build();

    public static final GTToolType WRENCH_MV = GTToolType.builder("mv_wrench")
            .idFormat("mv_%s_wrench")
            .toolTag(GTToolType.ToolItemTagType.CRAFTING, CustomTags.CRAFTING_WRENCHES)
            .toolTag(GTToolType.ToolItemTagType.MATCH, CustomTags.TOOLS_WRENCH)
            .harvestTag(CustomTags.MINEABLE_WITH_WRENCH)
            .definition(b -> b
                    .blockBreaking().crafting()
                    .sneakBypassUse().efficiencyMultiplier(2.5F)
                    .attackDamage(1.0F).attackSpeed(-2.8F)
                    .behaviors(BlockRotatingBehavior.INSTANCE,
                            new EntityDamageBehavior(3.0F, CustomTags.IRON_GOLEMS),
                            ToolModeSwitchBehavior.INSTANCE)
                    .brokenStack(ToolHelper.SUPPLY_POWER_UNIT_MV))
            .sound(GTSoundEntries.WRENCH_TOOL, true)
            .electric(GTValues.MV)
            .toolClasses(GTToolType.WRENCH)
            .defaultAbilities(GTItemAbilities.WRENCH_DIG, GTItemAbilities.WRENCH_DISMANTLE, GTItemAbilities.WRENCH_CONNECT)
            .build();

    public static final GTToolType WRENCH_EV = GTToolType.builder("ev_wrench")
            .idFormat("ev_%s_wrench")
            .toolTag(GTToolType.ToolItemTagType.CRAFTING, CustomTags.CRAFTING_WRENCHES)
            .toolTag(GTToolType.ToolItemTagType.MATCH, CustomTags.TOOLS_WRENCH)
            .harvestTag(CustomTags.MINEABLE_WITH_WRENCH)
            .definition(b -> b
                    .blockBreaking().crafting()
                    .sneakBypassUse().efficiencyMultiplier(3.5F)
                    .attackDamage(1.0F).attackSpeed(-2.8F)
                    .behaviors(BlockRotatingBehavior.INSTANCE,
                            new EntityDamageBehavior(3.0F, CustomTags.IRON_GOLEMS),
                            ToolModeSwitchBehavior.INSTANCE)
                    .brokenStack(ToolHelper.SUPPLY_POWER_UNIT_EV))
            .sound(GTSoundEntries.WRENCH_TOOL, true)
            .electric(GTValues.EV)
            .toolClasses(GTToolType.WRENCH)
            .defaultAbilities(GTItemAbilities.WRENCH_DIG, GTItemAbilities.WRENCH_DISMANTLE, GTItemAbilities.WRENCH_CONNECT)
            .build();

    public static final GTToolType WIRE_CUTTER_MV = GTToolType.builder("mv_wirecutter")
            .idFormat("mv_%s_wire_cutter")
            .toolTag(GTToolType.ToolItemTagType.CRAFTING, CustomTags.CRAFTING_WIRE_CUTTERS)
            .toolTag(GTToolType.ToolItemTagType.MATCH, CustomTags.TOOLS_WIRE_CUTTER)
            .harvestTag(CustomTags.MINEABLE_WITH_WIRE_CUTTER)
            .definition(b -> b
                    .blockBreaking().crafting()
                    .sneakBypassUse().damagePerCraftingAction(4)
                    .attackDamage(-1.0F).attackSpeed(-2.4F)
                    .brokenStack(ToolHelper.SUPPLY_POWER_UNIT_MV))
            .sound(GTSoundEntries.WIRECUTTER_TOOL, true)
            .electric(GTValues.MV)
            .toolClasses(GTToolType.WIRE_CUTTER)
            .defaultAbilities(GTItemAbilities.DEFAULT_WIRE_CUTTER_ACTIONS)
            .build();

    public static final GTToolType WIRE_CUTTER_EV = GTToolType.builder("ev_wirecutter")
            .idFormat("ev_%s_wire_cutter")
            .toolTag(GTToolType.ToolItemTagType.CRAFTING, CustomTags.CRAFTING_WIRE_CUTTERS)
            .toolTag(GTToolType.ToolItemTagType.MATCH, CustomTags.TOOLS_WIRE_CUTTER)
            .harvestTag(CustomTags.MINEABLE_WITH_WIRE_CUTTER)
            .definition(b -> b
                    .blockBreaking().crafting()
                    .sneakBypassUse().damagePerCraftingAction(4)
                    .attackDamage(-1.0F).attackSpeed(-2.4F)
                    .brokenStack(ToolHelper.SUPPLY_POWER_UNIT_EV))
            .sound(GTSoundEntries.WIRECUTTER_TOOL, true)
            .electric(GTValues.EV)
            .toolClasses(GTToolType.WIRE_CUTTER)
            .defaultAbilities(GTItemAbilities.DEFAULT_WIRE_CUTTER_ACTIONS)
            .build();

    public static final GTToolType BUZZSAW_MV = GTToolType.builder("mv_buzzsaw")
            .idFormat("mv_%s_buzzsaw")
            .toolTag(GTToolType.ToolItemTagType.CRAFTING, CustomTags.CRAFTING_SAWS)
            .toolTag(GTToolType.ToolItemTagType.MATCH, CustomTags.TOOLS_SAW)
            .toolTag(GTToolType.ToolItemTagType.MATCH, CustomTags.TOOLS_BUZZSAW)
            .definition(b -> b.crafting()
                    .attackDamage(1.5F).attackSpeed(-3.2F)
                    .brokenStack(ToolHelper.SUPPLY_POWER_UNIT_MV))
            .sound(GTSoundEntries.CHAINSAW_TOOL, true)
            .electric(GTValues.MV)
            .toolClasses(GTToolType.SAW)
            .build();

    public static final GTToolType BUZZSAW_HV = GTToolType.builder("hv_buzzsaw")
            .idFormat("hv_%s_buzzsaw")
            .toolTag(GTToolType.ToolItemTagType.CRAFTING, CustomTags.CRAFTING_SAWS)
            .toolTag(GTToolType.ToolItemTagType.MATCH, CustomTags.TOOLS_SAW)
            .toolTag(GTToolType.ToolItemTagType.MATCH, CustomTags.TOOLS_BUZZSAW)
            .definition(b -> b.crafting()
                    .attackDamage(1.5F).attackSpeed(-3.2F)
                    .brokenStack(ToolHelper.SUPPLY_POWER_UNIT_HV))
            .sound(GTSoundEntries.CHAINSAW_TOOL, true)
            .electric(GTValues.HV)
            .toolClasses(GTToolType.SAW)
            .build();

    public static final GTToolType BUZZSAW_EV = GTToolType.builder("ev_buzzsaw")
            .idFormat("ev_%s_buzzsaw")
            .toolTag(GTToolType.ToolItemTagType.CRAFTING, CustomTags.CRAFTING_SAWS)
            .toolTag(GTToolType.ToolItemTagType.MATCH, CustomTags.TOOLS_SAW)
            .toolTag(GTToolType.ToolItemTagType.MATCH, CustomTags.TOOLS_BUZZSAW)
            .definition(b -> b.crafting()
                    .attackDamage(1.5F).attackSpeed(-3.2F)
                    .brokenStack(ToolHelper.SUPPLY_POWER_UNIT_EV))
            .sound(GTSoundEntries.CHAINSAW_TOOL, true)
            .electric(GTValues.EV)
            .toolClasses(GTToolType.SAW)
            .build();

    public static final GTToolType BUZZSAW_IV = GTToolType.builder("iv_buzzsaw")
            .idFormat("iv_%s_buzzsaw")
            .toolTag(GTToolType.ToolItemTagType.CRAFTING, CustomTags.CRAFTING_SAWS)
            .toolTag(GTToolType.ToolItemTagType.MATCH, CustomTags.TOOLS_SAW)
            .toolTag(GTToolType.ToolItemTagType.MATCH, CustomTags.TOOLS_BUZZSAW)
            .definition(b -> b.crafting()
                    .attackDamage(1.5F).attackSpeed(-3.2F)
                    .brokenStack(ToolHelper.SUPPLY_POWER_UNIT_IV))
            .sound(GTSoundEntries.CHAINSAW_TOOL, true)
            .electric(GTValues.IV)
            .toolClasses(GTToolType.SAW)
            .build();

    public static final GTToolType SCREWDRIVER_MV = GTToolType.builder("mv_screwdriver")
            .idFormat("mv_%s_screwdriver")
            .toolTag(GTToolType.ToolItemTagType.CRAFTING, CustomTags.CRAFTING_SCREWDRIVERS)
            .toolTag(GTToolType.ToolItemTagType.MATCH, CustomTags.TOOLS_SCREWDRIVER)
            .definition(b -> b.crafting()
                    .sneakBypassUse()
                    .attackDamage(-1.0F).attackSpeed(3.0F)
                    .efficiencyMultiplier(3.0F)
                    .behaviors(new EntityDamageBehavior(3.0F, CustomTags.SPIDERS))
                    .brokenStack(ToolHelper.SUPPLY_POWER_UNIT_MV))
            .sound(GTSoundEntries.SCREWDRIVER_TOOL)
            .electric(GTValues.MV)
            .toolClasses(GTToolType.SCREWDRIVER)
            .defaultAbilities(GTItemAbilities.DEFAULT_SCREWDRIVER_ACTIONS)
            .build();

    public static final GTToolType SCREWDRIVER_EV = GTToolType.builder("ev_screwdriver")
            .idFormat("ev_%s_screwdriver")
            .toolTag(GTToolType.ToolItemTagType.CRAFTING, CustomTags.CRAFTING_SCREWDRIVERS)
            .toolTag(GTToolType.ToolItemTagType.MATCH, CustomTags.TOOLS_SCREWDRIVER)
            .definition(b -> b.crafting()
                    .sneakBypassUse()
                    .attackDamage(-1.0F).attackSpeed(3.0F)
                    .efficiencyMultiplier(3.0F)
                    .behaviors(new EntityDamageBehavior(3.0F, CustomTags.SPIDERS))
                    .brokenStack(ToolHelper.SUPPLY_POWER_UNIT_EV))
            .sound(GTSoundEntries.SCREWDRIVER_TOOL)
            .electric(GTValues.EV)
            .toolClasses(GTToolType.SCREWDRIVER)
            .defaultAbilities(GTItemAbilities.DEFAULT_SCREWDRIVER_ACTIONS)
            .build();
}
