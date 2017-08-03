package relkofizz.madness;

import net.minecraft.world.World;

public class madnessDebug {

	public static void print(String s, World worldIn){
		if(worldIn!=null){
			if(!worldIn.isRemote){
				System.out.println(s);
			}
		}else{
			System.out.println(s);
		}
	}
}
