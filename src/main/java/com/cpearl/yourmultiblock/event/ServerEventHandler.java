package com.cpearl.yourmultiblock.event;

import com.cpearl.yourmultiblock.YourMultiblock;
import com.cpearl.yourmultiblock.block.ControllerBlock;
import com.cpearl.yourmultiblock.structure.MultiblockStructure;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = YourMultiblock.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ServerEventHandler {
    @SubscribeEvent
    public static void onBreak(BlockEvent.BreakEvent event) {
        if (!(event.getPlayer() instanceof ServerPlayer player))
            return;
    }

    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        if (!(event.getEntity() instanceof ServerPlayer player))
            return;
        var level = player.getLevel();
        var pos = event.getPos();
        var blockstate = level.getBlockState(pos);
        var block = blockstate.getBlock();
        if (!(block instanceof ControllerBlock controllerBlock))
            return;
        if (!MultiblockStructure.STRUCTURES.containsKey(controllerBlock))
            return;
        for (var structure : MultiblockStructure.STRUCTURES.get(controllerBlock)) {
            if (!structure.formingItem.test(event.getItemStack().getItem()))
                continue;
            if (structure.tryForm(level, pos, player, blockstate))
                break;
        }
    }
}
