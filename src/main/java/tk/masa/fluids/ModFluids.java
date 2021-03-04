package tk.masa.fluids;

import com.mojang.datafixers.types.templates.Tag;

import net.minecraftforge.event.RegistryEvent;
import tk.masa.setup.Registration;

public class ModFluids {
	
	public static final FluidOil.Source oil = new FluidOil.Source();
	public static final FluidOil.Flowing flowing_oil = new FluidOil.Flowing();
	
	//public static class Tags {
	//	public static final Tag<Fluid> OIL = new FluidTages.Wrapper(Registration.location("oil")) 
	// }
		
	 
}