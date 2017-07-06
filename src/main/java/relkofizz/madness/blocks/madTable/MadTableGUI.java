package relkofizz.madness.blocks.madTable;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import relkofizz.madness.MainMadness;
import relkofizz.madness.blocks.ModBlocks;

public class MadTableGUI extends GuiContainer{

	private InventoryPlayer playerInv;
	
	public static final ResourceLocation BG_TEXTURE = new ResourceLocation(MainMadness.modId, "textures/gui/madTable.png");
	
	public MadTableGUI(Container container, InventoryPlayer playerInv) {
		super(container);
		this.playerInv = playerInv;
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY){
		GlStateManager.color(1, 1, 1, 1);
		mc.getTextureManager().bindTexture(BG_TEXTURE);
		int x = (width - xSize) /2;
		int y = (height - ySize) /2;
		drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY){
		String name = I18n.format(ModBlocks.madTable.getUnlocalizedName() + ".name");
		fontRendererObj.drawString(name, xSize / 2 - fontRendererObj.getStringWidth(name)/2, 6, 0x404040);
		fontRendererObj.drawString(playerInv.getDisplayName().getUnformattedText(), 8 ,ySize -94, 0x404040);
	}

}
