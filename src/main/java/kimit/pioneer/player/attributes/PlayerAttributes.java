package kimit.pioneer.player.attributes;

import net.minecraft.entity.attribute.EntityAttributes;

import java.util.HashSet;
import java.util.Set;

public class PlayerAttributes
{
	public static final String PREFIX = "attribute";
	public static final Set<PlayerAttribute> ATTRIBUTES = new HashSet<>();
	public static final PlayerAttribute ARMOR = register(new PlayerAttribute("armor", EntityAttributes.GENERIC_ARMOR));
	public static final PlayerAttribute ARMOR_TOUGHNESS = register(new PlayerAttribute("armor_toughness", EntityAttributes.GENERIC_ARMOR_TOUGHNESS));
	public static final PlayerAttribute ATTACK_DAMAGE = register(new PlayerAttribute("attack_damage", EntityAttributes.GENERIC_ATTACK_DAMAGE));
	public static final PlayerAttribute ATTACK_SPEED = register(new PlayerAttribute("attack_speed", EntityAttributes.GENERIC_ATTACK_SPEED));
	public static final PlayerAttribute KNOCKBACK_RESISTANCE = register(new PlayerAttribute("knockback_resistance", EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE));
	public static final PlayerAttribute LUCK = register(new PlayerAttribute("luck", EntityAttributes.GENERIC_LUCK));
	public static final PlayerAttribute HEALTH = register(new PlayerAttribute("health", EntityAttributes.GENERIC_MAX_HEALTH));
	public static final PlayerAttribute MOVEMENT_SPEED = register(new PlayerAttribute("movement_speed", EntityAttributes.GENERIC_MOVEMENT_SPEED));

	public static PlayerAttribute register(PlayerAttribute attribute)
	{
		ATTRIBUTES.add(attribute);
		return attribute;
	}
}
