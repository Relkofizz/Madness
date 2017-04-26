package relkofizz.madness.blocks;

import javax.annotation.Nullable;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import relkofizz.madness.blocks.tiles.MadTableTile;

public class MadTableBlock extends BasicTileEntity<MadTableTile> {

	public MadTableBlock() {
		super(Material.ROCK, "madTable");
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			MadTableTile tile = getTileEntity(world, pos);
			
			if (side == EnumFacing.DOWN) {
				tile.decrementCount();
			} else if (side == EnumFacing.UP) {
				tile.incrementCount();
			}
			player.addChatMessage(new TextComponentString("Count: " + tile.getCount()));
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
}
