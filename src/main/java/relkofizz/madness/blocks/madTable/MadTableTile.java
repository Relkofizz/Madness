package relkofizz.madness.blocks.madTable;

import javax.annotation.Nullable;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import relkofizz.madness.MainMadness;
import relkofizz.madness.network.PacketRequestUpdateMadTable;
import relkofizz.madness.network.PacketUpdateMadTable;

public class MadTableTile extends TileEntity{
	public ItemStackHandler inventory = new ItemStackHandler(){
		@Override
		protected void onContentsChanged(int slot){
			if(!getWorld().isRemote){
				MainMadness.network.sendToAllAround(new PacketUpdateMadTable(MadTableTile.this), new NetworkRegistry.TargetPoint(getWorld().provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 64));
			}
		}
	};
		
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound){
		compound.setTag("inventory", inventory.serializeNBT());
		return super.writeToNBT(compound);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound){
		inventory.deserializeNBT(compound.getCompoundTag("inventory"));
		super.readFromNBT(compound);
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
	}
	
	@SuppressWarnings("unchecked")
	@Nullable
	@Override
	public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? (T)inventory : super.getCapability(capability, facing);
	}
	
	@Override
	public void onLoad() {
		if (this.getWorld().isRemote) {
			MainMadness.network.sendToServer(new PacketRequestUpdateMadTable(this));
			inventory.setSize(9);
		}
	}
	
	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return new AxisAlignedBB(getPos(), getPos().add(1, 2, 1));
	}
}