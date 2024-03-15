package kimit.pioneer.elixir;

import kimit.pioneer.player.classes.PlayerClasses;

import java.util.HashMap;
import java.util.Map;

public class ClassElixirs
{
	public static final Map<String, ClassElixir> CLASS_ELIXIRS = new HashMap<>();
	public static final ClassElixir FIGHTER = register(new ClassElixir(PlayerClasses.FIGHTER, 6250335));
	public static final ClassElixir ROGUE = register(new ClassElixir(PlayerClasses.ROGUE, 14096408));
	public static final ClassElixir WIZARD = register(new ClassElixir(PlayerClasses.WIZARD, 24575));
	public static final ClassElixir CLERIC = register(new ClassElixir(PlayerClasses.CLERIC, 8378127));

	public static ClassElixir register(ClassElixir elixir)
	{
		CLASS_ELIXIRS.put(elixir.Class().getId(), elixir);
		return elixir;
	}

	public static ClassElixir fromId(String id)
	{
		return CLASS_ELIXIRS.get(id);
	}
}
