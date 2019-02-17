package objects;

import constants.Skills;

import java.util.EnumMap;

//Wrapper class for skill requirements
public class SkillRequirements {
	EnumMap<Skills, Integer> skills;

	public SkillRequirements (){
		skills = new EnumMap<Skills, Integer>(Skills.class);
		for (Skills skill : Skills.values()){
			skills.put(skill, 1);
		}
	}

	public EnumMap<Skills, Integer> getSkills() {
		return skills;
	}

	public boolean fulfillsRequirements(EnumMap<Skills, Integer> currentSkills){
		for (Skills skill : Skills.values()) {
			if(currentSkills.get(skill) < skills.get(skill)) return false;
		}
		return true;
	}
}
