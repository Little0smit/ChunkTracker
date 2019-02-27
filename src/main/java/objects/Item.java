package objects;

public class Item extends NamedThing{
    private String name;

	public Item(String name) {
		this.name = name;
	}

	public String getName(){
        return name;
    }
}
