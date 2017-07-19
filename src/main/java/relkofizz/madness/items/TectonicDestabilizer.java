package relkofizz.madness.items;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

public class TectonicDestabilizer extends BasicItem {

	private boolean hasUpgrade;
	public TectonicDestabilizer() {
		super("tectonicDestabilizer");
		this.setHarvestLevel("Diamond", 3);
		hasUpgrade=false;
	}
	
	public boolean canHarvestBlock(IBlockState blockIn)
    {
		return true;
    }
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		if(worldIn.isRemote){
			if(this.hasUpgrade){
				this.hasUpgrade = false;	
			}
			else{
				this.hasUpgrade = true;
			}
		}
		if(!worldIn.isRemote){
			System.out.println("Try sound");
			playerIn.playSound(SoundEvents.BLOCK_ANVIL_PLACE, 1f, 1f);
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
	
	@Override
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving)
    {
		//This implementation feels lame, try to improve it later
		Vec3d vec  = entityLiving.getLookVec().addVector(0.5, 0.5, 0.5); //adding 0.5 causes numbers to be rounded correctly when converted to int
		Vec3i vec2 = new Vec3i(vec.x, vec.y, vec.z); 
		//As all numbers will be one or zero, squareing them ensures they are non negative, subtract one from each to get the desired corner, and multiply to scale box
		if(hasUpgrade){
			vec2 = new Vec3i( ((vec2.getX()*vec2.getX())-1)*2, ((vec2.getY()*vec2.getY())-1)*2, ((vec2.getZ()*vec2.getZ())-1)*2); 
		}else{
			vec2 = new Vec3i( ((vec2.getX()*vec2.getX())-1), ((vec2.getY()*vec2.getY())-1), ((vec2.getZ()*vec2.getZ())-1)); 
		}
		
		for(BlockPos iterPos : BlockPos.getAllInBox(pos.subtract(vec2), pos.add(vec2))){
			if (iterPos.equals(pos)) continue; //Skips center block as It's already broken
			IBlockState blockState = worldIn.getBlockState(pos);
			Block block = state.getBlock();
			block.removedByPlayer(blockState, worldIn, iterPos, (EntityPlayer) entityLiving, true);
		}
        return false;
    }
}
