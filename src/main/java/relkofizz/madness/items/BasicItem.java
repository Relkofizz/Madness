package relkofizz.madness.items;

import net.minecraft.item.Item;
import relkofizz.madness.MainMadness;

public class BasicItem extends Item {

	protected String name;

	public BasicItem(String name) {
		this.name = name;
		setUnlocalizedName(name);
		setRegistryName(name);
		this.setCreativeTab(MainMadness.creativeTab);
	}

	public void registerItemModel() {
		MainMadness.proxy.registerItemRenderer(this, 0, name);
	}
}