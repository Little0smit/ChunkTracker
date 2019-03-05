package JSON;

import com.google.gson.Gson;
import constants.Constant;
import databases.Database;
import objects.*;
import objects.Process;
import objects.quests.Quest;

import java.io.*;

public class DataParser {

	public static void parse (boolean dummy){
		try {
			if (dummy) {
				System.out.println("bajs");
				parseFile("src/main/resources/DummyMobs.json", Constant.MOB_DATABASE, Mob[].class);
				parseFile("src/main/resources/DummyShops.json", Constant.SHOP_DATABASE, Shop[].class);
				parseFile("src/main/resources/DummyProcesses.json", Constant.PROCESS_DATABASE, Process[].class);
				parseFile("src/main/resources/DummyQuests.json", Constant.QUEST_DATABASE, Quest[].class);
				parseFile("src/main/resources/DummyChunks.json", Constant.CHUNK_DATABASE, Chunk[].class);
				parseFile("src/main/resources/DummySkillingLocations.json", Constant.SKILLING_LOCATION_DATABASE, SkillingLocation[].class);
				parseFile("src/main/resources/DummyEquippableItems.json", Constant.EQUIPPABLE_ITEM_DATABASE, EquippableItem[].class);
			} else {
				parseFile("src/main/resources/Mobs.json", Constant.MOB_DATABASE, Mob[].class);
				parseFile("src/main/resources/Shops.json", Constant.SHOP_DATABASE, Shop[].class);
				parseFile("src/main/resources/Processes.json", Constant.PROCESS_DATABASE, Process[].class);
				parseFile("src/main/resources/Quests.json", Constant.QUEST_DATABASE, Quest[].class);
				parseFile("src/main/resources/Chunks.json", Constant.CHUNK_DATABASE, Chunk[].class);
				parseFile("src/main/resources/SkillingLocations.json", Constant.SKILLING_LOCATION_DATABASE, SkillingLocation[].class);
				parseFile("src/main/resources/EquippableItems.json", Constant.EQUIPPABLE_ITEM_DATABASE, EquippableItem[].class);
			}
			for (Chunk chunk : Constant.CHUNK_DATABASE.getAllElements()){
				chunk.addItemsToDB();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static <T extends NamedThing> void parseFile(String path, Database<T> database, Class<T[]> tClass) throws IOException {
		Gson gson = new Gson();
		String st, theFile = "";

		BufferedReader br = new BufferedReader(new FileReader(new File(path)));
		while ((st = br.readLine()) != null) {
			theFile = theFile + st + "\n";
		}
		T[] mobs = gson.fromJson(theFile, tClass);
		for (T obj : mobs){
			database.registerElement(obj);
		}
	}
}