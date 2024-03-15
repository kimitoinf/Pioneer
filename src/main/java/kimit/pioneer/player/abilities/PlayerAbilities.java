package kimit.pioneer.player.abilities;

import java.util.HashSet;
import java.util.Set;

public class PlayerAbilities
{
	public static final String PREFIX = "ability";
	public static final Set<String> ABILITIES = new HashSet<>();
	public static final String STRENGTH = register("strength");
	public static final String DEXTERITY = register("dexterity");
	public static final String INTELLIGENCE = register("intelligence");
	public static final String WISDOM = register("wisdom");

	public static String register(String ability)
	{
		ABILITIES.add(ability);
		return ability;
	}
}
