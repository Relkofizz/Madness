package relkofizz.madness.items;


import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

public class TectonicDestabilizer extends BasicItem {

	private boolean hasUpgrade;
    public final int itemUseDuration;

	public TectonicDestabilizer() {
		super("tectonicDestabilizer");
		itemUseDuration =32;
		this.setHarvestLevel("Diamond", 3);
		hasUpgrade=false;
	}
	
	public boolean canHarvestBlock(IBlockState blockIn)
    {
		return true;
    }
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        {
            playerIn.setActiveHand(handIn);
            return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
        }
    }
	
	@Override
	public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX,
			float hitY, float hitZ, EnumHand hand) {
		System.out.println("Item use Begin");
		// TODO Auto-generated method stub
		return super.onItemUseFirst(player, world, pos, side, hitX, hitY, hitZ, hand);
	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.EAT;
    }
	
	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 32;
	}
	
	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
		System.out.println("ItemUseEnd");
		//Implentation in progress, not funcitonall yet
		RayTraceResult RTR = new RayTraceResult(entityLiving);
		BlockPos pos = new BlockPos(RTR.hitVec.x, RTR.hitVec.y, RTR.hitVec.z);//Prior implemtation used RTR.getBlockPos;,casued pos to be null, uncertain cause
		IBlockState state = worldIn.getBlockState(pos);			
		Block block = state.getBlock();
		
		Vec3i cornerCoords = EnumFacing.getDirectionFromEntityLiving(pos, entityLiving).getDirectionVec();

		System.out.println(cornerCoords+" M2 A");
		cornerCoords = new Vec3i( ((cornerCoords.getX()*cornerCoords.getX())-1), ((cornerCoords.getY()*cornerCoords.getY())-1), ((cornerCoords.getZ()*cornerCoords.getZ())-1)); 
		System.out.println(cornerCoords+" M2 B");
		//This implementation feels lame, try to improve it later
	
		System.out.println("Pos is:"+pos);
		
		for(BlockPos iterPos : BlockPos.getAllInBox(pos.subtract(cornerCoords), pos.add(cornerCoords))){
			if (iterPos.equals(pos)) continue; //Skips center block as It's already broken
			IBlockState blockState = worldIn.getBlockState(pos);
			block.removedByPlayer(blockState, worldIn, iterPos, (EntityPlayer) entityLiving, true);
		}//*/
		
		return super.onItemUseFinish(stack, worldIn, entityLiving);
	}
		
		

/*	@Override
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
    }//*/
}
