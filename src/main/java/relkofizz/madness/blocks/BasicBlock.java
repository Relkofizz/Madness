package relkofizz.madness.blocks;



import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import relkofizz.madness.MainMadness;

public class BasicBlock extends Block {

	protected String name;

	public BasicBlock(Material material, String name) {
		super(material);

		this.name = name;

		setUnlocalizedName(name);
		setRegistryName(name);
	}

	public void registerItemModel(ItemBlock itemBlock) {
		MainMadness.proxy.registerItemRenderer(itemBlock, 0, name);
	}

	@Override
	public BasicBlock setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}

}