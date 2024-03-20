package kimit.pioneer.player.abilities;

import java.util.HashSet;
import java.util.Set;

public class PlayerAbilities
{
	public static final String PREFIX = "ability";
	public static final Set<PlayerAbility> ABILITIES = new HashSet<>();
	public static final PlayerAbility STRENGTH = register(new PlayerAbility("strength"));
	public static final PlayerAbility DEXTERITY = register(new PlayerAbility("dexterity"));
	public static final PlayerAbility INTELLIGENCE = register(new PlayerAbility("intelligence"));
	public static final PlayerAbility WISDOM = register(new PlayerAbility("wisdom"));

	public static PlayerAbility register(PlayerAbility ability)
	{
		ABILITIES.add(ability);
		return ability;
	}
}
