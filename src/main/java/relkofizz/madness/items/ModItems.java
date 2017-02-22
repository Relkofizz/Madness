package relkofizz.madness.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems {

	public static BasicItem tSteelIngot;

	
	public static void init() {
		tSteelIngot = register(new BasicItem("tSteelIngot").setCreativeTab(CreativeTabs.MATERIALS));
	}

	private static <T extends Item> T register(T item) {
		GameRegistry.register(item);

		if (item instanceof BasicItem) {
			((BasicItem)item).registerItemModel();
		}

		return item;
	}

}
