package databases;

import objects.NamedThing;

import java.util.Collection;
import java.util.HashMap;

public class Database<T extends NamedThing> {

	private HashMap<String, T> database = new HashMap<String, T>();

	public T getElement(String name) {
		return database.get(name);
	}

	public boolean contains(T element){ return database.containsValue(element); }

	public boolean contains(String key){ return database.containsKey(key); }

	public void registerElement(T obj){ if(!database.containsKey(obj.getName())) database.put(obj.getName(), obj); }

	public Collection<T> getAllElements(){ return database.values(); }

	public int getNoOfelements(){ return database.size(); }
}
