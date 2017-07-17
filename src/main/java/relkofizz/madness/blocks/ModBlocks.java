package relkofizz.madness.blocks;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import relkofizz.madness.blocks.madTable.MadTableBlock;
import relkofizz.madness.blocks.madTable.MadTableTile;
import relkofizz.madness.blocks.tinyTable.TinyTableBlock;
import relkofizz.madness.items.ModItems;
import net.minecraftforge.event.RegistryEvent;

public class ModBlocks {

	public static BasicBlock madTable;
	public static TinyTableBlock tinyTable;
	
	public static final List<Block> blockRegList = new ArrayList<>();

    public static final List<Item> itemRegList = new ArrayList<>();
    
	public static void init() {
		System.out.println("Block Init Start");
		madTable = new MadTableBlock();
		tinyTable = new TinyTableBlock();
		
		//Make this bit more general later
		blockRegList.add(madTable);	
		itemRegList.add(new ItemBlock(madTable).setRegistryName(madTable.getRegistryName()));
		blockRegList.add(tinyTable);	
		itemRegList.add(new ItemBlock(tinyTable).setRegistryName(tinyTable.getRegistryName()));
		}
	
	//Move this to it's own class
	@EventBusSubscriber
	public static class BlockRegistrationHandler {
		
		@SubscribeEvent
		public static  void registerBlocks(final RegistryEvent.Register<Block> event) {
			System.out.println("Madness Block Registration Start");
			ModBlocks.init();

			final Block[] blocks = new Block[blockRegList.size()];
			
			blockRegList.toArray(blocks);

			GameRegistry.registerTileEntity(MadTableTile.class, "MadTableTile");

		    event.getRegistry().registerAll(blocks);
		}
		
		@SubscribeEvent
		public static  void registerItemBlocks(final RegistryEvent.Register<Item> event) {
			System.out.println("Madness Item Registration Start");
			
			ModItems.init();
		
			final Item[] items = new Item[itemRegList.size()];
			
			itemRegList.toArray(items);
			
			event.getRegistry().registerAll(items);
		    
		}
	}

}

