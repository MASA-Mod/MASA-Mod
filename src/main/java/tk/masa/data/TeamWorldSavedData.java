package tk.masa.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.client.renderer.FaceDirection.Constants;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.DimensionSavedDataManager;
import net.minecraft.world.storage.WorldSavedData;
import tk.masa.masa;
import tk.masa.other.ReaseachEnum;

public class TeamWorldSavedData extends WorldSavedData{
	
	//public CompoundNBT data = new CompoundNBT();
	//public HashSet<String> playerNames = new HashSet<String>();
	//public HashSet<ReaseachEnum> aspects = new HashSet<ReaseachEnum>();
	
	//public ArrayList<HashSet> oneTeam = new ArrayList<>();
	//public ArrayList<HashSet> twoTeam = new ArrayList<>();
	public HashMap<String, HashMap> allTeamsMap = new HashMap<String, HashMap>();

	
	public static String dataName = masa.MODID + "_DATA";
	
	public TeamWorldSavedData() {
		super(dataName);
	}
	
	public TeamWorldSavedData(String s) {
		super(s);
	}
		
	@Override
	public void read(CompoundNBT nbt)
	{	
		
		String name = "";
		//{teams:[[{allowedPlayer:"TEST2"},{allowedPlayer:"TEST2"},{aspect:"PROBE_KOMET"}],[{allowedPlayer:"TEST2"},{allowedPlayer:"TEST2"},{aspect:"PROBE_KOMET"}]]}
		System.out.println("readData");
		System.out.println(nbt.toString());
		ListNBT allTeams = nbt.getList("teams", 9);
		System.out.println(allTeams.toString());
		Iterator<INBT> it = allTeams.iterator();
		while(it.hasNext()) {
			name = "";
			HashSet<String> playerNames = new HashSet<String>();
			HashSet<ReaseachEnum> aspects = new HashSet<ReaseachEnum>();
			//System.out.println(oneTeamName.toString());
			//ListNBT oneTeam = oneTeamName.getList("test", 10);
			ListNBT oneTeam = (ListNBT) it.next();
			System.out.println(oneTeam.toString());
			Iterator<INBT> it2 = oneTeam.iterator();
			while(it2.hasNext()) {
				CompoundNBT c = (CompoundNBT) it2.next();
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
	public CompoundNBT write(CompoundNBT nbt)
	{
		System.out.println("writeData");
		CompoundNBT data = new CompoundNBT();
		ListNBT AllnbtList = new ListNBT();
		

		for (Entry<String, HashMap> entry : allTeamsMap.entrySet()) {
			
			HashMap<String, HashSet> oneTeam = entry.getValue();
			String name = entry.getKey();
			HashSet<String> playerNames = oneTeam.get("players");
			
			
			ListNBT nbtList = new ListNBT();
			for (String player : playerNames)
	        {
	            CompoundNBT temp = new CompoundNBT();
	            temp.putString("allowedPlayer", player);
	            nbtList.add(temp);
	        }
			
			HashSet<ReaseachEnum> aspects = oneTeam.get("aspects");
			for (ReaseachEnum e : aspects)
	        {
				CompoundNBT temp = new CompoundNBT();
				temp.putString("aspect", e.name());
				nbtList.add(temp);
	        }
			
			CompoundNBT temp = new CompoundNBT();
			temp.putString("name", name);
			nbtList.add(temp);
			//CompoundNBT nameTeam = new CompoundNBT();
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
	
	public static TeamWorldSavedData get(World world) {
		ServerWorld sworld = world.getServer().getWorld(DimensionType.OVERWORLD);
		DimensionSavedDataManager storage = sworld.getSavedData();
		return storage.getOrCreate(TeamWorldSavedData::new, dataName);
	}
	
	
}
