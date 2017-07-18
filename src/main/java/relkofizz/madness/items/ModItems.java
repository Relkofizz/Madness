package relkofizz.madness.items;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import relkofizz.madness.MainMadness;

public class ModItems {

	public static final List<Item> itemRegList = new ArrayList<>();
	
	public static BasicItem tSteelIngot;
	
	public static void init() {
		System.out.println("Item Init Start");
		tSteelIngot = new BasicItem("tSteelIngot").setCreativeTab(CreativeTabs.REDSTONE);
		tSteelIngot.registerItemModel();

		itemRegList.add(tSteelIngot);
	}
}
