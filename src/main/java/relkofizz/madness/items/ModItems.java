package relkofizz.madness.items;

import net.minecraft.creativetab.CreativeTabs;
import relkofizz.madness.blocks.ModBlocks;

public class ModItems {

	public static BasicItem tSteelIngot;
	
	public static void init() {
		System.out.println("Item Init Start");
		tSteelIngot = new BasicItem("tSteelIngot").setCreativeTab(CreativeTabs.REDSTONE);
		ModBlocks.itemRegList.add(tSteelIngot);
	}
}
