package relkofizz.madness.proxy;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import relkofizz.madness.MainMadness;

public class CommonProxy {
	public void registerItemRenderer(Item item, int meta, String id) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(MainMadness.modId + ":" + id, "inventory"));
	}
}
