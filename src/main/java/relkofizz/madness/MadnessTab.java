package relkofizz.madness;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import relkofizz.madness.blocks.ModBlocks;

public class MadnessTab extends CreativeTabs{

	public MadnessTab() {
		super(MainMadness.modId);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(ModBlocks.madTable);
	}

}
