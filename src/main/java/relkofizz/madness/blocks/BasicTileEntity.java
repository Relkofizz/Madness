package relkofizz.madness.blocks;

import javax.annotation.Nullable;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class BasicTileEntity<TE extends TileEntity> extends BasicBlock {

		public BasicTileEntity(Material material, String name) {
			super(material, name);
		}
		
		public abstract Class<TE> getTileEntityClass();
		
		@SuppressWarnings("unchecked")
		public TE getTileEntity(IBlockAccess world, BlockPos pos) {
			return (TE)world.getTileEntity(pos);
		}
		
		@Override
		public boolean hasTileEntity(IBlockState state) {
			return true;
		}
		
		@Nullable
		@Override
		public abstract TE createTileEntity(World world, IBlockState state);

	}