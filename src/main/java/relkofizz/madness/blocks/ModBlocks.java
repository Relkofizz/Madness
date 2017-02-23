package relkofizz.madness.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModBlocks {

	public static BasicBlock madTable;
	
	public static void init() {
		madTable = register(new BasicBlock(Material.ROCK,"madTable").setCreativeTab(CreativeTabs.MATERIALS));

	}

	private static <T extends Block> T register(T block, ItemBlock itemBlock) {
		GameRegistry.register(block);
		GameRegistry.register(itemBlock);

		if (block instanceof BasicBlock) {
			((BasicBlock)block).registerItemModel(itemBlock);
		}

		return block;
	}

	private static <T extends Block> T register(T block) {
		ItemBlock itemBlock = new ItemBlock(block);
		itemBlock.setRegistryName(block.getRegistryName());
		return register(block, itemBlock);
	}

}

