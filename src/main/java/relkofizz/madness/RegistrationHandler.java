package relkofizz.madness;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import relkofizz.madness.blocks.ModBlocks;
import relkofizz.madness.blocks.madTable.MadTableTile;
import relkofizz.madness.items.ModItems;

@EventBusSubscriber
public class RegistrationHandler {
	
	@SubscribeEvent
	public static  void registerBlocks(final RegistryEvent.Register<Block> event) {
		System.out.println("Madness Block Registration Start");
		
		ModBlocks.init();

		final Block[] blocks = new Block[ModBlocks.blockRegList.size()];
		
		ModBlocks.blockRegList.toArray(blocks);

		GameRegistry.registerTileEntity(MadTableTile.class, "MadTableTile");

	    event.getRegistry().registerAll(blocks);
	}
	
	@SubscribeEvent
	public static  void registerItems(final RegistryEvent.Register<Item> event) {
		System.out.println("Madness Item Registration Start");
		
		ModItems.init();
	
		final Item[] items = new Item[ModItems.itemRegList.size()];
		
		ModItems.itemRegList.toArray(items);
		
		event.getRegistry().registerAll(items);	    
	}
	
	@SubscribeEvent
    public static void registerBlockModels(ModelRegistryEvent event){
		System.out.println("Madness Model Registration Start");
		for (Item item : ModItems.itemRegList){
			ModelLoader.setCustomModelResourceLocation(item, new ItemStack(item).getItemDamage(), new ModelResourceLocation(item.getRegistryName(), "inventory"));

		}
	}
}