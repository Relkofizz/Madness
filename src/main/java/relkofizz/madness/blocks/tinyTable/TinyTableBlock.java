package relkofizz.madness.blocks.tinyTable;

import javax.annotation.Nullable;

import net.minecraft.block.BlockWorkbench;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import relkofizz.madness.MadnessGUIHandler;
import relkofizz.madness.MainMadness;
import relkofizz.madness.blocks.BasicBlock;

public class TinyTableBlock extends BasicBlock{

	protected static String name = "tinyTable";
	public TinyTableBlock()
    {
        super(Material.WOOD,name);
        this.fullBlock = false;
        this.lightOpacity = 3;
        this.blockHardness = 0;
        this.setCreativeTab(CreativeTabs.DECORATIONS);
    }
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override 
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
    {
		if(!playerIn.isSneaking()){
			if (worldIn.isRemote)
	        {
	            return true;
	        }
	        else
	        {
	        	playerIn.openGui(MainMadness.instance, MadnessGUIHandler.TINYTABLE, worldIn, pos.getX(), pos.getY(), pos.getZ());
	            playerIn.addStat(StatList.CRAFTING_TABLE_INTERACTION);
	            return true;
	        }
        }else{
        	if(playerIn.getHeldItemMainhand()==null){
				playerIn.setHeldItem(hand, this.getDrops(worldIn, pos, state, 0).get(0));
				this.removedByPlayer(state, worldIn, pos, playerIn, false);
			}
        }
	return false;
    }
}