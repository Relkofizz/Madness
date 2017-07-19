package relkofizz.madness.items;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import relkofizz.madness.MadnessTab;
import relkofizz.madness.MainMadness;

public class ModItems {

	public static final List<Item> itemRegList = new ArrayList<>();
	public static BasicItem tSteelIngot;
	public static TectonicDestabilizer tectonicDestabilizer;
	
	public static void preInit() {
		System.out.println("Item Init Start");
		
		tSteelIngot = new BasicItem("tSteelIngot");
		
		tectonicDestabilizer = new TectonicDestabilizer();
		
		itemRegList.add(tSteelIngot);
		itemRegList.add(tectonicDestabilizer);
	}
	public static void postInit() {
		tSteelIngot.registerItemModel();
		tectonicDestabilizer.registerItemModel();
	}

	
	
	public static void register(BasicItem i) {
		i.registerItemModel();
		itemRegList.add(i);
	}
}
