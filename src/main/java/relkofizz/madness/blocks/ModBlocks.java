package relkofizz.madness.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import relkofizz.madness.blocks.madTable.MadTableBlock;

public class ModBlocks {

	public static BasicBlock madTableD;
	
	public static void init() {
		madTableD = register(new MadTableBlock());

	}

	private static <T extends Block> T register(T block, ItemBlock itemBlock) {
		GameRegistry.register(block);
		GameRegistry.register(itemBlock);

		if (block instanceof BasicBlock) {
			((BasicBlock)block).registerItemModel(itemBlock);
		}

		if (block instanceof BasicTileEntity) {
			GameRegistry.registerTileEntity(((BasicTileEntity<?>)block).getTileEntityClass(), block.getRegistryName().toString());
		}
		
		return block;
	}

	private static <T extends Block> T register(T block) {
		ItemBlock itemBlock = new ItemBlock(block);
		itemBlock.setRegistryName(block.getRegistryName());
		return register(block, itemBlock);
	}

}

