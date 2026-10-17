package net.terrafirmainfinity.core.integration.create;

import com.simibubi.create.api.registry.CreateBuiltInRegistries;
import com.simibubi.create.content.kinetics.mechanicalArm.ArmBlockEntity;
import com.simibubi.create.content.kinetics.mechanicalArm.ArmInteractionPoint;
import com.simibubi.create.content.kinetics.mechanicalArm.ArmInteractionPointType;
import net.dries007.tfc.common.blockentities.MoldTableBlockEntity;
import net.dries007.tfc.common.blocks.devices.CharcoalForgeBlock;
import net.dries007.tfc.common.blocks.devices.CrucibleBlock;
import net.dries007.tfc.common.blocks.devices.MoldTableBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.terrafirmainfinity.core.InfinityCore;
import org.jetbrains.annotations.Nullable;

public class CustomArmInteractionPoints {
    static {
        register("crucible", new CrucibleType());
        register("charcoal_forge", new CharcoalForgeType());
        register("mold_table", new MoldTableType());
    }

    private static <T extends ArmInteractionPointType> void register(String name, T type) {
        Registry.register(CreateBuiltInRegistries.ARM_INTERACTION_POINT_TYPE, InfinityCore.MOD_ID + name, type);
    }

    public static class CrucibleType extends ArmInteractionPointType {
        @Override
        public boolean canCreatePoint(Level level, BlockPos pos, BlockState state) {
            return state.getBlock() instanceof CrucibleBlock;
        }

        @Override
        public @Nullable ArmInteractionPoint createPoint(Level level, BlockPos pos, BlockState state) {
            return new ArmInteractionPoint(this, level, pos, state);
        }
    }

    public static class CharcoalForgeType extends ArmInteractionPointType {
        @Override
        public boolean canCreatePoint(Level level, BlockPos pos, BlockState state) {
            return state.getBlock() instanceof CharcoalForgeBlock;
        }

        @Override
        public @Nullable ArmInteractionPoint createPoint(Level level, BlockPos pos, BlockState state) {
            return new ArmInteractionPoint(this, level, pos, state);
        }
    }

    public static class MoldTableType extends ArmInteractionPointType {
        @Override
        public boolean canCreatePoint(Level level, BlockPos pos, BlockState state) {
            return state.getBlock() instanceof MoldTableBlock;
        }

        @Override
        public @Nullable ArmInteractionPoint createPoint(Level level, BlockPos pos, BlockState state) {
            return new MoldTablePoint(this, level, pos, state);
        }
    }

    public static class MoldTablePoint extends ArmInteractionPoint {
        public MoldTablePoint(ArmInteractionPointType type, Level level, BlockPos pos, BlockState state) {
            super(type, level, pos, state);
        }

        @Override
        public int getSlotCount(ArmBlockEntity armBlockEntity) {
            return 2;
        }

        @Override
        public ItemStack extract(ArmBlockEntity armBlockEntity, int slot, boolean simulate) {
            if (slot != MoldTableBlockEntity.OUTPUT_SLOT) return ItemStack.EMPTY; // Only extract from the Output Slot

            if (!(level.getBlockEntity(pos) instanceof MoldTableBlockEntity moldTableBE)) {
                return ItemStack.EMPTY;
            }
            return moldTableBE.getInventory().extractItem(slot, 64, simulate);
        }
    }

    public static void init() {}
}
