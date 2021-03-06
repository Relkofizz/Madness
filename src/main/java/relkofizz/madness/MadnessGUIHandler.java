package relkofizz.madness;


import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import relkofizz.madness.blocks.madTable.MadTableContainer;
import relkofizz.madness.blocks.madTable.MadTableGUI;
import relkofizz.madness.blocks.madTable.MadTableTile;
import relkofizz.madness.blocks.tinyTable.TinyTableContainer;
import relkofizz.madness.blocks.tinyTable.TinyTableGUI;

public class MadnessGUIHandler implements IGuiHandler {
	
	public static final int MADTABLE = 0;
	public static final int TINYTABLE = 1;


	@Override
	public Container getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID){
		case MADTABLE:
			return new MadTableContainer(player.inventory, (MadTableTile)world.getTileEntity(new BlockPos(x,y,z)));
		case TINYTABLE:
			return new TinyTableContainer(player.inventory, world);
		default:
			return null;
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID){
		case MADTABLE:
			return new MadTableGUI(getServerGuiElement(ID, player, world, x, y, z), player.inventory);
		case TINYTABLE:
			return new TinyTableGUI(player.inventory, world);
		default:
		return null;
		}
	}
	
}
