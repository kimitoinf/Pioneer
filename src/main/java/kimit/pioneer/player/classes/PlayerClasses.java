package kimit.pioneer.player.classes;

import java.util.HashMap;
import java.util.Map;

public class PlayerClasses
{
	public static final String PREFIX = "class";
	public static final Map<String, PlayerClass> CLASSES = new HashMap<>();
	public static final PlayerClass NONE = register(new PlayerClass("none"));
	public static final PlayerClass FIGHTER = register(new PlayerClass("fighter").health(5.0f).attackDamage(1.0f).knockbackResistance(0.05f).movementSpeed(-0.01f).strength(10));
	public static final PlayerClass ROGUE = register(new PlayerClass("rogue").health(-5.0f).attackSpeed(2.0f).luck(2.0f).movementSpeed(0.01f).dexterity(10));
	public static final PlayerClass WIZARD = register(new PlayerClass("wizard").health(-2.5f).intelligence(10));
	public static final PlayerClass CLERIC = register(new PlayerClass("cleric").health(2.5f).wisdom(10));

	public static PlayerClass register(PlayerClass player)
	{
		CLASSES.put(player.getId(), player);
		return player;
	}

	public static PlayerClass fromId(String id)
	{
		return CLASSES.get(id);
	}
}
