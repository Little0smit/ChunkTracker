package JSON;

import com.google.gson.Gson;
import constants.Constant;
import databases.Database;
import objects.Process;
import objects.*;
import objects.quests.Quest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class DataParser {

	public static void parse (boolean dummy){
        String filepath = "src/main/resources/realData/";
        if (dummy) {
            filepath = "src/main/resources/dummyData/";
			System.out.println("Program is running on Dummy data");
        }
		try {
            parseFile(filepath + "Mobs.json", Constant.MOB_DATABASE, Mob[].class);
            parseFile(filepath + "Shops.json", Constant.SHOP_DATABASE, Shop[].class);
            parseFile(filepath + "Processes.json", Constant.PROCESS_DATABASE, Process[].class);
            parseFile(filepath + "Quests.json", Constant.QUEST_DATABASE, Quest[].class);
            parseFile(filepath + "Chunks.json", Constant.CHUNK_DATABASE, Chunk[].class);
            parseFile(filepath + "SkillingLocations.json", Constant.SKILLING_LOCATION_DATABASE, SkillingLocation[].class);
            parseFile(filepath + "EquippableItems.json", Constant.EQUIPPABLE_ITEM_DATABASE, EquippableItem[].class);
            parseFile(filepath + "Items.json", Constant.ITEM_DATABASE, Item[].class);

			/*
			N.B. Not needed as Items.json contains all items, and only want to populate the unlocked items.
			for (Chunk chunk : Constant.CHUNK_DATABASE.getAllElements()){
				chunk.addItemsToDB();
			}
			*/

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static <T extends NamedThing> void parseFile(String path, Database<T> database, Class<T[]> tClass) throws IOException {
		Gson gson = new Gson();
		String st;
		StringBuilder stringBuilder = new StringBuilder();

		BufferedReader br = new BufferedReader(new FileReader(new File(path)));
		while ((st = br.readLine()) != null) {
			stringBuilder.append(st);
		}
		T[] mobs = gson.fromJson(stringBuilder.toString(), tClass);
		for (T obj : mobs){
			database.registerElement(obj);
		}
	}
}