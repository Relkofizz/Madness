package relkofizz.madness.proxy;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import relkofizz.madness.MainMadness;
import relkofizz.madness.blocks.madTable.MadTableTESRender;
import relkofizz.madness.blocks.madTable.MadTableTile;

public class ClientProxy extends CommonProxy{
	
	public void registerItemRenderer(Item item, int meta, String id) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(MainMadness.modId + ":" + id, "inventory"));
	}
	
	public String localize(String unlocalized, Object... args) {
		return I18n.format(unlocalized, args);
	}
	
	@Override
	public void registerRenderers() {
		ClientRegistry.bindTileEntitySpecialRenderer(MadTableTile.class, new MadTableTESRender());
	}
}
