package relkofizz.madness.items;


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
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import relkofizz.madness.madnessDebug;

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
		madnessDebug.print("Item use Begin", world);
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
		madnessDebug.print("ItemUseEnd", worldIn);
		RayTraceResult RTR = this.rayTrace(worldIn, (EntityPlayer) entityLiving, true);
		madnessDebug.print("RTR is: "+RTR, worldIn);
		if (RTR!=null){ //RTR is set to null if player is out of range from block, not sure if this is intended behavior, but it is convenient for this purpose	
			//This appears to use only the range the player is given in creative mode, intend to fix later
			BlockPos pos = RTR.getBlockPos();
			madnessDebug.print("Pos is: "+pos, worldIn);
		
			Vec3i cornerCoords = RTR.sideHit.getDirectionVec();
			madnessDebug.print("Direction Vec is: "+cornerCoords, worldIn);
			
			entityLiving.knockBack(entityLiving, 0.5f, entityLiving.getLookVec().x, entityLiving.getLookVec().z);
			
			madnessDebug.print("Velocity added "+cornerCoords.getX()*-3 +" "+ cornerCoords.getY()*-3 +" "+ cornerCoords.getZ()*-3, worldIn);
			
			cornerCoords = new Vec3i( ((cornerCoords.getX()*cornerCoords.getX())-1), ((cornerCoords.getY()*cornerCoords.getY())-1), ((cornerCoords.getZ()*cornerCoords.getZ())-1)); 
			
			madnessDebug.print("Corners are: "+cornerCoords, worldIn);		
			
			for(BlockPos iterPos : BlockPos.getAllInBox(pos.subtract(cornerCoords), pos.add(cornerCoords))){
				IBlockState blockState = worldIn.getBlockState(iterPos);
				if(!blockState.getBlock().isAir(blockState, worldIn, iterPos)){
					//madnessDebug.print("Tried to remove block at " + iterPos + " of type "+blockState.getBlock(), worldIn);
					blockState.getBlock().removedByPlayer(blockState, worldIn, iterPos, (EntityPlayer) entityLiving, true);
				}
			}
			
		}else{
			madnessDebug.print("Too Far", worldIn);
		}
		return super.onItemUseFinish(stack, worldIn, entityLiving);
	}
}
