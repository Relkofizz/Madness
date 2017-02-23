package relkofizz.madness;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import relkofizz.madness.blocks.ModBlocks;
import relkofizz.madness.items.ModItems;
import relkofizz.madness.proxy.CommonProxy;


@Mod(modid = MainMadness.modId, version = MainMadness.version, name = MainMadness.name)
public class MainMadness {

	public static final String modId = "madness";
	public static final String name = "Madness Mod";
	public static final String version = "0.0.1";

	@SidedProxy(serverSide = "relkofizz.madness.proxy.CommonProxy", clientSide = "relkofizz.madness.proxy.ClientProxy")
	public static CommonProxy proxy;
	
	@Mod.Instance(modId)
	public static MainMadness instance;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ModItems.init();
		ModBlocks.init();
		System.out.println(name + " is loading!");
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}

}