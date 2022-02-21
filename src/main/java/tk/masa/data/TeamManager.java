package tk.masa.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.storage.DimensionDataStorage;
import tk.masa.masa;

public class TeamManager extends SavedData{
	
	//public CompoundTag data = new CompoundTag();
	//public HashSet<String> playerNames = new HashSet<String>();
	//public HashSet<ReaseachEnum> aspects = new HashSet<ReaseachEnum>();
	
	//public ArrayList<HashSet> oneTeam = new ArrayList<>();
	//public ArrayList<HashSet> twoTeam = new ArrayList<>();
	public static TeamManager twsd;
	public HashMap<String, HashMap> allTeamsMap = new HashMap<String, HashMap>();
	public static String dataName = masa.MODID + "_DATA";
	
	public static void init(Level world){
		System.out.println("INITDATA");
		ServerLevel s = (ServerLevel) world;
        ServerLevel overworld = s.getServer().getLevel(Level.OVERWORLD);
        twsd = TeamManager.get(overworld);
	}

	
	public static void test() {
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
        twsd.setDirty();
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
		twsd.setDirty();
	}
	public static void removePlayer(String team, String name) {
		HashMap<String, HashSet> oneTeam = twsd.allTeamsMap.get(team);
		oneTeam.get("players").remove(name);
		System.out.println(name + " left team " + team);
		twsd.setDirty();
	}
	public static void addAspectToTeam(String team, ReaseachEnum re) {
		HashMap<String, HashSet> oneTeam = twsd.allTeamsMap.get(team);
		oneTeam.get("aspects").add(re);
		System.out.println(team + " learned " + re.name());
		twsd.setDirty();
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
		twsd.setDirty();
	}
	public static void addTeam(String name) {
		HashSet<String> playerNames = new HashSet<String>();
		HashSet<ReaseachEnum> aspects = new HashSet<ReaseachEnum>();
		HashMap<String, HashSet> oneTeamMap = new HashMap<String, HashSet>();
		oneTeamMap.put("aspects", aspects);
		oneTeamMap.put("players", playerNames);
		twsd.allTeamsMap.put(name, oneTeamMap);
		System.out.println("Added team " + name);
		twsd.setDirty();
	}
	public static void deleteTeam(String name) {
		twsd.allTeamsMap.remove(name);
		twsd.setDirty();
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
	
	
	public static TeamManager get(Level world) {
		ServerLevel sworld = world.getServer().getLevel(Level.OVERWORLD);
		//DimensionDataStorage storage = ((ServerLevel)level).getDataStorage();
		DimensionDataStorage storage = sworld.getDataStorage();
		return storage.computeIfAbsent(TeamManager::new, TeamManager::new, dataName);
	}
	
    public TeamManager() {
    }
	
	public TeamManager(CompoundTag nbt) {
		String name = "";
		//{teams:[[{allowedPlayer:"TEST2"},{allowedPlayer:"TEST2"},{aspect:"PROBE_KOMET"}],[{allowedPlayer:"TEST2"},{allowedPlayer:"TEST2"},{aspect:"PROBE_KOMET"}]]}
		System.out.println("readData");
		System.out.println(nbt.toString());
		ListTag allTeams = nbt.getList("teams", 9);
		System.out.println(allTeams.toString());
		Iterator<Tag> it = allTeams.iterator();
		while(it.hasNext()) {
			name = "";
			HashSet<String> playerNames = new HashSet<String>();
			HashSet<ReaseachEnum> aspects = new HashSet<ReaseachEnum>();
			//System.out.println(oneTeamName.toString());
			//ListTag oneTeam = oneTeamName.getList("test", 10);
			ListTag oneTeam = (ListTag) it.next();
			System.out.println(oneTeam.toString());
			Iterator<Tag> it2 = oneTeam.iterator();
			while(it2.hasNext()) {
				CompoundTag c = (CompoundTag) it2.next();
				System.out.println(c.toString());
				if(c.contains("allowedPlayer")) {
					playerNames.add(c.getString("allowedPlayer"));
				}else if(c.contains("aspect")) {
					aspects.add(ReaseachEnum.valueOf(c.getString("aspect")));
				}else if(c.contains("name")) {
					name = c.getString("name");
				}
			}
			
			HashMap<String, HashSet> oneTeamMap = new HashMap<String, HashSet>();
			oneTeamMap.put("aspects", aspects);
			oneTeamMap.put("players", playerNames);
			allTeamsMap.put(name, oneTeamMap);
		}
		
		System.out.println(allTeamsMap.toString());
		
		//data = nbt;
	}

	@Override
	public CompoundTag save(CompoundTag nbt)
	{
		System.out.println("writeData");
		CompoundTag data = new CompoundTag();
		ListTag AllnbtList = new ListTag();
		

		for (Entry<String, HashMap> entry : allTeamsMap.entrySet()) {
			
			HashMap<String, HashSet> oneTeam = entry.getValue();
			String name = entry.getKey();
			HashSet<String> playerNames = oneTeam.get("players");
			
			
			ListTag nbtList = new ListTag();
			for (String player : playerNames)
	        {
	            CompoundTag temp = new CompoundTag();
	            temp.putString("allowedPlayer", player);
	            nbtList.add(temp);
	        }
			
			HashSet<ReaseachEnum> aspects = oneTeam.get("aspects");
			for (ReaseachEnum e : aspects)
	        {
				CompoundTag temp = new CompoundTag();
				temp.putString("aspect", e.name());
				nbtList.add(temp);
	        }
			
			CompoundTag temp = new CompoundTag();
			temp.putString("name", name);
			nbtList.add(temp);
			//CompoundTag nameTeam = new CompoundTag();
			//nameTeam.put(name, nbtList);
			//AllnbtList.add(nameTeam);
			AllnbtList.add(nbtList);
		}
		//
		data.put("teams", AllnbtList);
		System.out.println("writeData");
		System.out.println(data.toString());
		//data.array
		return data;
		//return nbt;
	}
	
}
