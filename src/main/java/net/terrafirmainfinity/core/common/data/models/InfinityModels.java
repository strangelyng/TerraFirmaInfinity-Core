package net.terrafirmainfinity.core.common.data.models;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.registry.registrate.builder.MachineBuilder;
import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateBlockstateProvider;
import com.tterrag.registrate.util.nullness.NonNullBiConsumer;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.common.blocks.soil.ConnectedGrassBlock;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.client.model.generators.BlockModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.client.model.generators.MultiPartBlockStateBuilder;
import net.terrafirmainfinity.core.InfinityCore;

import static com.gregtechceu.gtceu.api.machine.property.GTMachineModelProperties.IS_ACTIVE;
import static com.gregtechceu.gtceu.api.machine.property.GTMachineModelProperties.IS_FORMED;
import static com.gregtechceu.gtceu.common.data.models.GTMachineModels.tieredHullTextures;

public class InfinityModels {
    public static NonNullBiConsumer<DataGenContext<Block, Block>, RegistrateBlockstateProvider> createDwarvenCasingModel(String material) {
        return (ctx, prov) -> {
            prov.simpleBlock(ctx.getEntry(), prov.models().cubeBottomTop(ctx.getName(),
                    InfinityCore.id("block/casings/dwarven/%s/side".formatted(material)),
                    InfinityCore.id("block/casings/dwarven/%s/bottom".formatted(material)),
                    InfinityCore.id("block/casings/dwarven/%s/top".formatted(material))));
        };
    }

    public static MachineBuilder.ModelInitializer createElectrodeHolderModel() {
        return (ctx, prov, builder) -> {
            BlockModelProvider models = prov.models();
            var unformedModel = models.nested()
                    .parent(models.getExistingFile(InfinityCore.id("block/machine/template/electrode_holder/block")));
            tieredHullTextures(unformedModel, builder.getOwner().getTier());
            var formedModel = models.nested()
                    .parent(models.getExistingFile(GTCEu.id("block/cube/tinted/bottom_top")));
            tieredHullTextures(formedModel, builder.getOwner().getTier());

            builder.part(unformedModel)
                    .condition(IS_FORMED, false)
                    .end();
            builder.part(formedModel)
                    .condition(IS_FORMED, true)
                    .end();
            builder.part(InfinityCore.id("block/machine/template/electrode_holder/overlay"))
                    .condition(IS_FORMED, true)
                    .end();
            builder.part(InfinityCore.id("block/machine/template/electrode_holder/active"))
                    .condition(IS_ACTIVE, true)
                    .end();

            builder.addReplaceableTextures("bottom", "top", "side");
        };
    }

    public static NonNullBiConsumer<DataGenContext<Block, ConnectedGrassBlock>, RegistrateBlockstateProvider> createTFCConnectedGrassModel() {
        return (ctx, prov) -> {
            String name = ctx.getName();
            Block block = ctx.getEntry();
            ModelFile modelBottom = prov.models().withExistingParent("block/" + name + "/bottom", ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "block/grass_bottom"))
                    .texture("texture", InfinityCore.id("block/" + name.replace("_grass", "")));
            ModelFile modelSide = prov.models().withExistingParent("block/" + name + "/side", ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "block/grass_side"))
                    .texture("texture", InfinityCore.id("block/" + name.replace("_grass", "")));
            ModelFile modelTop = prov.models().withExistingParent("block/" + name + "/top", ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "block/grass_top"))
                    .texture("texture", InfinityCore.id("block/" + name.replace("_grass", "")));
            ModelFile modelSnowySide = prov.models().withExistingParent("block/" + name + "/snowy_side", ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "block/grass_snowy_side"))
                    .texture("texture", InfinityCore.id("block/" + name.replace("_grass", "")));
            ModelFile modelSnowyTop = prov.models().withExistingParent("block/" + name + "/snowy_top", ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "block/grass_snowy_top"))
                    .texture("texture", InfinityCore.id("block/" + name.replace("_grass", "")));

            MultiPartBlockStateBuilder builder = prov.getMultipartBuilder(block);

            builder.part().modelFile(modelBottom).rotationX(90).addModel();
            builder.part().modelFile(modelTop).rotationX(270).rotationY(90).nextModel()
                    .modelFile(modelTop).rotationX(270).nextModel()
                    .modelFile(modelTop).rotationX(270).rotationY(180).nextModel()
                    .modelFile(modelTop).rotationX(270).rotationY(270)
                    .addModel().condition(BlockStateProperties.SNOWY, false);
            builder.part().modelFile(modelSnowyTop).rotationX(270).rotationY(90).nextModel()
                    .modelFile(modelSnowyTop).rotationX(270).nextModel()
                    .modelFile(modelSnowyTop).rotationX(270).rotationY(180).nextModel()
                    .modelFile(modelSnowyTop).rotationX(270).rotationY(270)
                    .addModel().condition(BlockStateProperties.SNOWY, true);
            builder.part().modelFile(modelTop).addModel().condition(BlockStateProperties.NORTH, true).condition(BlockStateProperties.SNOWY, false);
            builder.part().modelFile(modelTop).rotationY(90).addModel().condition(BlockStateProperties.EAST, true).condition(BlockStateProperties.SNOWY, false);
            builder.part().modelFile(modelTop).rotationY(180).addModel().condition(BlockStateProperties.SOUTH, true).condition(BlockStateProperties.SNOWY, false);
            builder.part().modelFile(modelTop).rotationY(270).addModel().condition(BlockStateProperties.WEST, true).condition(BlockStateProperties.SNOWY, false);
            builder.part().modelFile(modelSnowyTop).addModel().condition(BlockStateProperties.NORTH, true).condition(BlockStateProperties.SNOWY, true);
            builder.part().modelFile(modelSnowyTop).rotationY(90).addModel().condition(BlockStateProperties.EAST, true).condition(BlockStateProperties.SNOWY, true);
            builder.part().modelFile(modelSnowyTop).rotationY(180).addModel().condition(BlockStateProperties.SOUTH, true).condition(BlockStateProperties.SNOWY, true);
            builder.part().modelFile(modelSnowyTop).rotationY(270).addModel().condition(BlockStateProperties.WEST, true).condition(BlockStateProperties.SNOWY, true);
            builder.part().modelFile(modelSide).addModel().condition(BlockStateProperties.NORTH, false).condition(BlockStateProperties.SNOWY, false);
            builder.part().modelFile(modelSide).rotationY(90).addModel().condition(BlockStateProperties.EAST, false).condition(BlockStateProperties.SNOWY, false);
            builder.part().modelFile(modelSide).rotationY(180).addModel().condition(BlockStateProperties.SOUTH, false).condition(BlockStateProperties.SNOWY, false);
            builder.part().modelFile(modelSide).rotationY(270).addModel().condition(BlockStateProperties.WEST, false).condition(BlockStateProperties.SNOWY, false);
            builder.part().modelFile(modelSnowySide).addModel().condition(BlockStateProperties.NORTH, false).condition(BlockStateProperties.SNOWY, true);
            builder.part().modelFile(modelSnowySide).rotationY(90).addModel().condition(BlockStateProperties.EAST, false).condition(BlockStateProperties.SNOWY, true);
            builder.part().modelFile(modelSnowySide).rotationY(180).addModel().condition(BlockStateProperties.SOUTH, false).condition(BlockStateProperties.SNOWY, true);
            builder.part().modelFile(modelSnowySide).rotationY(270).addModel().condition(BlockStateProperties.WEST, false).condition(BlockStateProperties.SNOWY, true);
        };
    }
}
