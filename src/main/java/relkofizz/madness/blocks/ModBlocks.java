package relkofizz.madness.blocks;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import relkofizz.madness.blocks.madTable.MadTableBlock;
import relkofizz.madness.blocks.tinyTable.TinyTableBlock;
import relkofizz.madness.items.ModItems;

public class ModBlocks {

	public static BasicBlock madTable;
	public static TinyTableBlock tinyTable;
	
	public static final List<Block> blockRegList = new ArrayList<>();
    
	public static void init() {
		System.out.println("Block Init Start");
		
		madTable = new MadTableBlock();
		tinyTable = new TinyTableBlock();
		
		blockRegList.add(madTable);	
		blockRegList.add(tinyTable);	
		
		for(int i=0;i<blockRegList.size();i++){
			ModItems.itemRegList.add(new ItemBlock(blockRegList.get(i)).setRegistryName(blockRegList.get(i).getRegistryName()));
		}
	}
}

