package relkofizz.madness.items;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

public class TectonicDestabilizer extends BasicItem {

	public TectonicDestabilizer() {
		super("tectonicDestabilizer");
		this.setHarvestLevel("Diamond", 3);
	}
	
	public boolean canHarvestBlock(IBlockState blockIn)
    {
        return true;
    }
	
	@Override
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving)
    {
		//This implementation feels lame, try to improve it later
	
		Vec3d vec  = entityLiving.getLookVec().addVector(0.5, 0.5, 0.5); //adding 0.5 causes numbers to be rounded correctly when converted to int
		Vec3i vec2 = new Vec3i(vec.x, vec.y, vec.z); 
		vec2 = new Vec3i( ((vec2.getX()*vec2.getX())-1), ((vec2.getY()*vec2.getY())-1), ((vec2.getZ()*vec2.getZ())-1)); //As all numbers will be one or zero, squareing them ensures they are non negative
		
		for(BlockPos iterPos : BlockPos.getAllInBox(pos.subtract(vec2), pos.add(vec2))){
			if (iterPos.equals(pos)) continue; //Skips center block as It's already broken
			IBlockState blockState = worldIn.getBlockState(pos);
			Block block = state.getBlock();
			block.removedByPlayer(blockState, worldIn, iterPos, (EntityPlayer) entityLiving, true);
		}
        return false;
    }
}
