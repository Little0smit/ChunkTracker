package JSON;

import com.google.gson.Gson;
import constants.Constant;
import databases.Database;
import objects.Chunk;
import objects.Mob;
import objects.NamedThing;
import objects.Process;
import objects.Shop;
import objects.quests.Quest;

import java.io.*;

public class DataParser {

	public static void parse (){
		try {
			parseFile("src/main/resources/Mobs.json", Constant.MOB_DATABASE, Mob[].class);
			parseFile("src/main/resources/Chunks.json", Constant.CHUNK_DATABASE, Chunk[].class);
			parseFile("src/main/resources/Shops.json", Constant.SHOP_DATABASE, Shop[].class);
			parseFile("src/main/resources/Processes.json", Constant.PROCESS_DATABASE, Process[].class);
			parseFile("src/main/resources/Quests.json", Constant.QUEST_DATABASE, Quest[].class);
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