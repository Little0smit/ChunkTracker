package objects;

import constants.EquipmentSlot;
import constants.WeaponType;

public class EquippableItem extends NamedThing{
	private String name;
	private int[] offensiveStats, //stab, slash, crush, magic, ranged
			defensiveStats, //stab, slash, crush, magic, ranged
			otherStats; //strength, ranged strength, magic strength, prayer, attack speed
	private WeaponType type;
	private EquipmentSlot slot;

	@Override
	public String getName() { return name; }

	public int[] getOffensiveStats() { return offensiveStats; }

	public int[] getDefensiveStats() { return defensiveStats; }

	public int[] getOtherStats() { return otherStats; }

	public WeaponType getType() { return type; }

	public EquipmentSlot getSlot() { return slot; }

	public int getBiSNumber() throws Exception{
		if (type == WeaponType.Magic) throw new Exception();
		return (Math.max(Math.max(offensiveStats[0], offensiveStats[1]), offensiveStats[2]) +
				((type == WeaponType.Melee) ? otherStats[0] : otherStats[1])) /
				otherStats[4];
	}
}
