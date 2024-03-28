package com.cpearl.yourmultiblock.block;

import com.cpearl.yourmultiblock.recipe.MultiblockRecipe;
import com.cpearl.yourmultiblock.registry.YMBBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.ItemHandlerHelper;

public class ControllerBlockEntity extends BlockEntity {
    public ControllerBlockEntity(BlockPos pos, BlockState state) {
        super(YMBBlockEntityTypes.CONTROLLER.get(), pos, state);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, BlockEntity blockEntity) {
        if (blockEntity instanceof ControllerBlockEntity controllerBlockEntity)
            controllerBlockEntity.tick(level, pos, state);
    }

    protected MultiblockRecipe processRecipe = null;
    protected int processTime = 0;

    public void tick(Level level, BlockPos pos, BlockState state) {
        var facing = state.getValue(HorizontalDirectionalBlock.FACING);
        var rotation = switch (facing) {
            case DOWN, UP, NORTH -> Rotation.NONE;
            case SOUTH -> Rotation.CLOCKWISE_180;
            case WEST -> Rotation.COUNTERCLOCKWISE_90;
            case EAST -> Rotation.CLOCKWISE_90;
        };

        var input = new BlockPos(1, 0, 0);
        var output = new BlockPos(-1, 0, 0);
        var inputBE = level.getBlockEntity(pos.offset(input.rotate(rotation)));
        var outputBE = level.getBlockEntity(pos.offset(output.rotate(rotation)));
        if (inputBE == null || outputBE == null) {
            processRecipe = null;
            processTime = 0;
            return;
        }
        var inputCapability = inputBE.getCapability(ForgeCapabilities.ITEM_HANDLER);
        var outputCapability = outputBE.getCapability(ForgeCapabilities.ITEM_HANDLER);
        if (!(inputCapability.isPresent() && outputCapability.isPresent())) {
            processRecipe = null;
            processTime = 0;
            return;
        }
        if (processRecipe != null && ++processTime == processRecipe.processTime) {
            outputCapability.ifPresent(capability -> {
                ItemHandlerHelper.insertItem(capability, new ItemStack(Items.DIAMOND), false);
            });
            processRecipe = null;
            processTime = 0;
        }
        if (processRecipe == null) {
            inputCapability.ifPresent(capability -> {
                var ingredient = new ItemStack(Items.APPLE);
                for (int i = 0; i < capability.getSlots(); i++)
                {
                    var stack = capability.getStackInSlot(i);
                    if (stack.is(ingredient.getItem())) {
                        var extractItem = capability.extractItem(i, ingredient.getCount(), true);
                        if (extractItem.getCount() == ingredient.getCount()) {
                            ingredient = null;
                            break;
                        }
                        ingredient.setCount(ingredient.getCount() - extractItem.getCount());
                    }
                }
                if (ingredient == null) {
                    ingredient = new ItemStack(Items.APPLE);
                    for (int i = 0; i < capability.getSlots(); i++)
                    {
                        var stack = capability.getStackInSlot(i);
                        if (stack.is(ingredient.getItem())) {
                            var extractItem = capability.extractItem(i, ingredient.getCount(), false);
                            if (extractItem.getCount() == ingredient.getCount())
                                break;
                            ingredient.setCount(ingredient.getCount() - extractItem.getCount());
                        }
                    }
                    processRecipe = MultiblockRecipe.TEST1;
                }
            });
        }
    }
}
