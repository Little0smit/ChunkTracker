package objects;

import java.util.ArrayList;

public class Mob extends NamedThing {
    String name;
    int combatLvl;
    ArrayList<String> drops;

    public Mob(String name, int combatLvl, ArrayList<String> drops) {
        this.name = name;
        this.combatLvl = combatLvl;
        this.drops = drops;
    }

    @Override
    public String getName() {
        return name;
    }

    public int getCombatLvl() {
        return combatLvl;
    }

    public ArrayList<String> getDrops() {
        return drops;
    }
}
