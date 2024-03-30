package com.cpearl.yourmultiblock.structure;

import com.cpearl.yourmultiblock.block.ControllerBlock;
import com.cpearl.yourmultiblock.recipe.MultiblockRecipeType;
import com.cpearl.yourmultiblock.registry.YMBBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Tuple;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;
import org.antlr.v4.runtime.misc.MultiMap;

import java.util.*;
import java.util.function.Predicate;

public class MultiblockStructure {
    public static final MultiMap<ControllerBlock, MultiblockStructure> STRUCTURES = new MultiMap<>();

    public static void addStructure(MultiblockStructure structure) {
        var block = structure.controllerBlock;
        var list = STRUCTURES.get(block);
        if (list == null) {
            STRUCTURES.map(block, structure);
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).name.equals(structure.name)) {
                list.set(i, structure);
                return;
            }
        }
        list.add(structure);
    }

    public final ResourceLocation name;
    public final ControllerBlock controllerBlock;
    public final List<Tuple<BlockPos, Predicate<Block>>> blocks;
    public final Predicate<Item> formingItem;
    public final List<MultiblockRecipeType> recipeType;
    public final List<BlockPos> inputs, outputs;

    public MultiblockStructure(ResourceLocation name, ControllerBlock controllerBlock, List<Tuple<BlockPos, Predicate<Block>>> blocks, Predicate<Item> formingItem, List<MultiblockRecipeType> recipeType, List<BlockPos> inputs, List<BlockPos> outputs) {
        this.name = name;
        this.controllerBlock = controllerBlock;
        this.blocks = blocks;
        this.formingItem = formingItem;
        this.recipeType = recipeType;
        this.inputs = inputs;
        this.outputs = outputs;
    }

    public void addBlock(BlockPos pos, Predicate<Block> block) {
        blocks.add(new Tuple<>(pos, block));
    }

    public boolean tryForm(ServerLevel level, BlockPos pos, ServerPlayer player, BlockState blockstate) {
        player.swing(InteractionHand.MAIN_HAND);
        var blockEntity = level.getBlockEntity(pos, YMBBlockEntityTypes.CONTROLLER.get());
        if (blockEntity.isEmpty())
            return false;
        var facing = blockstate.getValue(HorizontalDirectionalBlock.FACING);
        var rotation = switch (facing) {
            case DOWN, UP, NORTH -> Rotation.NONE;
            case SOUTH -> Rotation.CLOCKWISE_180;
            case WEST -> Rotation.COUNTERCLOCKWISE_90;
            case EAST -> Rotation.CLOCKWISE_90;
        };

        for (var posPredicate : blocks) {
            var blockPos = posPredicate.getA().rotate(rotation);
            var predicate = posPredicate.getB();
            if (!predicate.test(level.getBlockState(pos.offset(blockPos)).getBlock()))
                return false;
        }

        blockEntity.get().structure = this;
        return true;
    }

    public static class Builder {
        public final ResourceLocation name;
        public ControllerBlock controllerBlock;
        public Predicate<Item> formingItem;
        public final List<List<String>> pattern = new ArrayList<>();
        public char center;
        public final Map<Character, Predicate<Block>> dict = new HashMap<>();
        public final List<MultiblockRecipeType> recipeTypes = new ArrayList<>();
        public final Set<Character> inputs = new HashSet<>(), outputs = new HashSet<>();

        protected Builder(ResourceLocation name) {
            this.name = name;
        }

        public Builder recipeTypes(MultiblockRecipeType ...type) {
            recipeTypes.addAll(Arrays.asList(type));
            return this;
        }

        public Builder formingItemCond(Predicate<Item> item) {
            this.formingItem = item;
            return this;
        }

        public Builder formingItem(Item item) {
            return formingItemCond(Predicate.isEqual(item));
        }

        public Builder formingItemTag(ResourceLocation tag) {
            return formingItemCond(ForgeRegistries.ITEMS.tags()
                    .getTag(TagKey.create(Registry.ITEM_REGISTRY, tag))::contains);
        }

        public Builder pattern(String ...line) {
            this.pattern.add(List.of(line));
            return this;
        }

        public Builder controller(char ch, Block controllerBlock) {
            if (!(controllerBlock instanceof ControllerBlock controller))
                throw new IllegalArgumentException();
            this.center = ch;
            this.controllerBlock = controller;
            this.dict.put(ch, Predicate.isEqual(controllerBlock));
            return this;
        }

        public Builder whereCond(char ch, Predicate<Block> block) {
            this.dict.put(ch, block);
            return this;
        }

        public Builder where(char ch, Block block) {
            return whereCond(ch, Predicate.isEqual(block));
        }

        public Builder whereTag(char ch, ResourceLocation tag) {
            return whereCond(ch,
                    ForgeRegistries.BLOCKS.tags().getTag(TagKey.create(Registry.BLOCK_REGISTRY, tag))::contains);
        }

        public Builder inputBlock(char ...input) {
            for (char ch : input)
                inputs.add(ch);
            return this;
        }

        public Builder outputBlock(char ...output) {
            for (char ch : output)
                outputs.add(ch);
            return this;
        }

        public MultiblockStructure build() {
            List<Tuple<BlockPos, Predicate<Block>>> blocks = new ArrayList<>();
            List<BlockPos> inputBlocks = new ArrayList<>(), outputBlocks = new ArrayList<>();
            BlockPos centerPos = null;
            for (int i = 0; i < pattern.size(); i++) {
                var layer = pattern.get(i);
                for (int j = 0; j < layer.size(); j++) {
                    var line = layer.get(j);
                    for (int k = 0; k < line.length(); k++) {
                        var pos = new BlockPos(i, j, -k);
                        var ch = line.charAt(k);
                        var block = dict.get(ch);
                        if (ch == center)
                            centerPos = new BlockPos(i, j, -k);
                        if (inputs.contains(ch))
                            inputBlocks.add(pos);
                        if (outputs.contains(ch))
                            outputBlocks.add(pos);
                        blocks.add(new Tuple<>(pos, block));
                    }
                }
            }
            if (centerPos == null) {
                throw new RuntimeException("No center in multiblock!");
            }
            var centerX = centerPos.getX();
            var centerY = centerPos.getY();
            var centerZ = centerPos.getZ();
            blocks.forEach(BlockPosPredicateTuple -> {
                var pos = BlockPosPredicateTuple.getA();
                BlockPosPredicateTuple.setA(pos.offset(-centerX, -centerY, -centerZ));
            });
            return new MultiblockStructure(name, controllerBlock, blocks, formingItem,
                    recipeTypes, inputBlocks, outputBlocks);
        }
    }
}
