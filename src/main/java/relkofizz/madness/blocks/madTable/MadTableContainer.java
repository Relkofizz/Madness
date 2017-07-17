package relkofizz.madness.blocks.madTable;


import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;

public class MadTableContainer extends Container{

	public MadTableContainer(InventoryPlayer playerInv, final MadTableTile madTable) {
		if(madTable.inventory.getSlots()!=9){
			madTable.inventory.setSize(9); //this is a hacky fix, the inventory seems to initialize with one slot for some reason, temporary
		}
		for (int i=0; i<3; i++){
			for (int j=0; j<3; j++){
				addSlotToContainer(new SlotItemHandler(madTable.inventory, i*3+j, 62+18*j, 17+18*i) {
					@Override
					public void onSlotChanged() {
						madTable.markDirty();
					}
				});
			}
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				addSlotToContainer(new Slot(playerInv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}
		
		for (int k = 0; k < 9; k++) {
			addSlotToContainer(new Slot(playerInv, k, 8 + k * 18, 142));
		}
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int index) {
		ItemStack itemstack = null;
		Slot slot = inventorySlots.get(index);
		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			int containerSlots = inventorySlots.size() - player.inventory.mainInventory.size();
			if (index < containerSlots) {
				if (!this.mergeItemStack(itemstack1, containerSlots, inventorySlots.size(), true)) {
					return null;
				}
			} else if (!this.mergeItemStack(itemstack1, 0, containerSlots, false)) {
				return null;
			}
			if (itemstack1.isEmpty()) {
				slot.putStack(null);
			} else {
				slot.onSlotChanged();
			}
			if (itemstack1.getCount() == itemstack.getCount()) {
				return null;
			}
			slot.onTake(player, itemstack);
		}
		return itemstack;
	}
	
}
