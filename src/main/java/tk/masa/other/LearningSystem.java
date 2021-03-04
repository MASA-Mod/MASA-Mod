package tk.masa.other;

import java.util.HashSet;

public class LearningSystem {
	
	public static HashSet<ReaseachEnum> aspects = new HashSet<ReaseachEnum>();
	
	public static void research(ReaseachEnum re) {
		aspects.add(re);
		System.out.println(aspects.toString());
		//update();
	}
	public static void update() {
		
	}
}

