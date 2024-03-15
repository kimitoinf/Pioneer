package kimit.pioneer.elixir;

import kimit.pioneer.player.attributes.PlayerAttributes;

import java.util.HashMap;
import java.util.Map;

public class Elixirs
{
	public static final Map<String, Elixir> ELIXIRS = new HashMap<>();
	public static final Elixir ARMOR = register(new Elixir(PlayerAttributes.ARMOR, 1.0f, 16776960));
	public static final Elixir ARMOR_TOUGHNESS = register(new Elixir(PlayerAttributes.ARMOR_TOUGHNESS, 0.5f, 8323199));
	public static final Elixir ATTACK_DAMAGE = register(new Elixir(PlayerAttributes.ATTACK_DAMAGE, 1.0f, 32639));
	public static final Elixir ATTACK_SPEED = register(new Elixir(PlayerAttributes.ATTACK_SPEED, 0.1f, 65535));
	public static final Elixir KNOCKBACK_RESISTANCE = register(new Elixir(PlayerAttributes.KNOCKBACK_RESISTANCE, 0.05f, 16711935));
	public static final Elixir LUCK = register(new Elixir(PlayerAttributes.LUCK, 1.0f, 65280));
	public static final Elixir HEALTH = register(new Elixir(PlayerAttributes.HEALTH, 1.0f, 16711680));
	public static final Elixir MOVEMENT_SPEED = register(new Elixir(PlayerAttributes.MOVEMENT_SPEED, 0.01f, 255));

	private static Elixir register(Elixir elixir)
	{
		ELIXIRS.put(elixir.Attribute().Id(), elixir);
		return elixir;
	}

	public static Elixir getElixir(String id)
	{
		return ELIXIRS.get(id);
	}
}
