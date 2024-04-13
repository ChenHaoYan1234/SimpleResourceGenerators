package bootmgr.simple_resource_generators.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;
import java.util.Objects;

public class ContainerGenerators extends Container {
    protected Slot slot;
    protected Slot[] playerSlots = new Slot[36];

    ContainerGenerators(EntityPlayer player, BlockPos blockPos) {
        super();
        TileEntity block = player.world.getTileEntity(blockPos);
        addSlotToContainer(slot = new SlotItemHandler(Objects.requireNonNull(block).getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null), 0, 7, 26));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                playerSlots[i * 9 + j] = new Slot(player.inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18);
                addSlotToContainer(playerSlots[i * 9 + j]);
            }
        }

        for (int i = 0; i < 9; i++) {
            playerSlots[i] = new Slot(player.inventory, i, 8 + i * 18, 142);
            addSlotToContainer(playerSlots[i]);
        }
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }
}
