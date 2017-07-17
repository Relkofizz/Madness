package relkofizz.madness.blocks.tinyTable;

import javax.annotation.Nonnull;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TinyTableContainer extends ContainerWorkbench{
	
	public TinyTableContainer(InventoryPlayer playerInv, World world){
		
		super(playerInv, world, BlockPos.ORIGIN);
	}

	@Override
	public boolean canInteractWith(@Nonnull EntityPlayer player) {
		return true;
	}
}
