package relkofizz.madness;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import relkofizz.madness.blocks.ModBlocks;
import relkofizz.madness.items.ModItems;
import relkofizz.madness.network.PacketRequestUpdateMadTable;
import relkofizz.madness.network.PacketUpdateMadTable;
import relkofizz.madness.proxy.CommonProxy;


@Mod(modid = MainMadness.modId, version = MainMadness.version, name = MainMadness.name)
public class MainMadness {

	public static final String modId = "madness";
	public static final String name = "Madness Mod";
	public static final String version = "0.0.1";
	
	public static SimpleNetworkWrapper network;

	@SidedProxy(serverSide = "relkofizz.madness.proxy.CommonProxy", clientSide = "relkofizz.madness.proxy.ClientProxy")
	public static CommonProxy proxy;
	
	@Mod.Instance(modId)
	public static MainMadness instance;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		System.out.println(name + " is loading!");
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new MadnessGUIHandler());
		proxy.registerRenderers();
		
		network = NetworkRegistry.INSTANCE.newSimpleChannel(modId);
		network.registerMessage(new PacketUpdateMadTable.Handler(), PacketUpdateMadTable.class, 0, Side.CLIENT);
		network.registerMessage(new PacketRequestUpdateMadTable.Handler(), PacketRequestUpdateMadTable.class, 1, Side.SERVER);
		
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}

}