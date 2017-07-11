package relkofizz.madness.blocks.madTable;

import javax.annotation.Nullable;


import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import relkofizz.madness.MadnessGUIHandler;
import relkofizz.madness.MainMadness;
import relkofizz.madness.blocks.BasicTileEntity;

public class MadTableBlock extends BasicTileEntity<MadTableTile> {

	public MadTableBlock() {
		super(Material.ROCK, "madTable");
		this.setCreativeTab(CreativeTabs.DECORATIONS);
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}	
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			if (player.isSneaking()) {
			} else {
				player.openGui(MainMadness.instance, MadnessGUIHandler.MADTABLE, world, pos.getX(), pos.getY(), pos.getZ());
			}
		}
		return true;
	}
	
	@Override
	public Class<MadTableTile> getTileEntityClass() {
		return MadTableTile.class;
	}
	
	@Nullable
	@Override
	public MadTableTile createTileEntity(World world, IBlockState state) {
		return new MadTableTile();
	}
	
	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		//Does not Drop all contents on breaking, block currently on hold, considering other crafting methods 
		MadTableTile tile = getTileEntity(world, pos);
		IItemHandler itemHandler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH);
		ItemStack stack = itemHandler.getStackInSlot(0);
		if (stack != null) {
			EntityItem item = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), stack);
			world.spawnEntityInWorld(item);
		}
		super.breakBlock(world, pos, state);
	}
}
