package relkofizz.madness.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import relkofizz.madness.MainMadness;

public class BasicItem extends Item {

	protected String name;

	public BasicItem(String name) {
		this.name = name;
		setUnlocalizedName(name);
		setRegistryName(name);
	}

	public void registerItemModel() {
		MainMadness.proxy.registerItemRenderer(this, 0, name);
	}

	@Override
	public BasicItem setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}
}