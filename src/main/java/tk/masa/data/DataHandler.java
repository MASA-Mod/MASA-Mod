package tk.masa.data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import tk.masa.other.ReaseachEnum;

public class DataHandler {
	public static TeamWorldSavedData twsd;
	
	public static void init(World world){
		System.out.println("INITDATA");
		ServerWorld s = (ServerWorld) world;
        ServerWorld overworld = s.getServer().getWorld(DimensionType.OVERWORLD);
        twsd = TeamWorldSavedData.get(overworld);
	}

	
	public static void test(PlayerEntity player) {
		addTeam("TestTeam");
		
        addPlayer("TestTeam", "TEST");
        addPlayer("TestTeam", "TEST2");
        
        //addAspect("TestTeam", ReaseachEnum.PROBE_KOMET);
        
        addTeam("TestTeam2");
		
        addPlayer("TestTeam2", "TESTER");
        addPlayer("TestTeam2", "TESTER2");
        
        //addAspect("TestTeam2", ReaseachEnum.PREASURE_EARTH);
        
        System.out.println("TEAMADD");
        System.out.println(hasPlayerAspect("TEST", ReaseachEnum.PROBE_KOMET));
        System.out.println(hasPlayerAspect("TEST", ReaseachEnum.PROBE_MOON));
        
        twsd.markDirty();
	}
	

	public static Object[] listTeams(){
		return twsd.allTeamsMap.keySet().toArray();
	}
	public static void debug() {
		System.out.println("MASA-DEBUG");
		System.out.println(twsd.allTeamsMap.toString());
	}
	public static void addPlayer(String team, String name) {
		HashMap<String, HashSet> oneTeam = twsd.allTeamsMap.get(team);
		oneTeam.get("players").add(name);
		System.out.println(name + " joined team " + team);
		twsd.markDirty();
	}
	public static void removePlayer(String team, String name) {
		HashMap<String, HashSet> oneTeam = twsd.allTeamsMap.get(team);
		oneTeam.get("players").remove(name);
		System.out.println(name + " left team " + team);
		twsd.markDirty();
	}
	public static void addAspectToTeam(String team, ReaseachEnum re) {
		HashMap<String, HashSet> oneTeam = twsd.allTeamsMap.get(team);
		oneTeam.get("aspects").add(re);
		System.out.println(team + " learned " + re.name());
		twsd.markDirty();
	}
	public static void addAspectToPlayer(String playerName, ReaseachEnum re) {
		for (Entry<String, HashMap> entry : twsd.allTeamsMap.entrySet()) {
			HashMap<String, HashSet> oneTeam = entry.getValue();
			System.out.println(oneTeam.toString());
			HashSet<String> playerNames = oneTeam.get("players");
			HashSet<ReaseachEnum> res = oneTeam.get("aspects");
			if(playerNames.contains(playerName)) {
				res.add(re);
			}
		}
		twsd.markDirty();
	}
	public static void addTeam(String name) {
		HashSet<String> playerNames = new HashSet<String>();
		HashSet<ReaseachEnum> aspects = new HashSet<ReaseachEnum>();
		HashMap<String, HashSet> oneTeamMap = new HashMap<String, HashSet>();
		oneTeamMap.put("aspects", aspects);
		oneTeamMap.put("players", playerNames);
		twsd.allTeamsMap.put(name, oneTeamMap);
		System.out.println("Added team " + name);
		twsd.markDirty();
	}
	public static void deleteTeam(String name) {
		twsd.allTeamsMap.remove(name);
		twsd.markDirty();
	}
	public static boolean hasPlayerAspect(String playerName, ReaseachEnum re) {
		for (Entry<String, HashMap> entry : twsd.allTeamsMap.entrySet()) {
			HashMap<String, HashSet> oneTeam = entry.getValue();
			System.out.println(oneTeam.toString());
			HashSet<String> playerNames = oneTeam.get("players");
			HashSet<ReaseachEnum> res = oneTeam.get("aspects");
			if(playerNames.contains(playerName) && res.contains(re)) {
				System.out.println(playerName + " does have " + re.name());
				return true;
			}
		}
		System.out.println(playerName + " does not have " + re.name());
		return false;
	}
	
}
