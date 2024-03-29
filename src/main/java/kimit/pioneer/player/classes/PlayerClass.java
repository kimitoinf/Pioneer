package kimit.pioneer.player.classes;

import kimit.pioneer.player.PlayerDataAccessor;
import kimit.pioneer.player.PlayerState;
import kimit.pioneer.player.abilities.PlayerAbilities;
import kimit.pioneer.player.abilities.PlayerAbility;
import kimit.pioneer.player.abilities.PlayerAbilityModifier;
import kimit.pioneer.player.attributes.PlayerAttribute;
import kimit.pioneer.player.attributes.PlayerAttributes;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerClass
{
	public static final UUID CLASS_MODIFIER_UUID = UUID.fromString("d5106978-3bad-4d72-a529-85b644c1ae7a");
	public static final String CLASS_ABILITY_ID = "class";
	private final String Id;
	private final Map<PlayerAttribute, Float> Attributes = new HashMap<>();
	private final Map<String, PlayerAbilityModifier> Abilities = new HashMap<>();

	public PlayerClass(String id)
	{
		Id = id;

		for (PlayerAttribute loop : PlayerAttributes.ATTRIBUTES)
			Attributes.put(loop, 0.0f);
	}

	public String getId()
	{
		return Id;
	}

	public float getAttributeValue(PlayerAttribute attribute)
	{
		return Attributes.get(attribute);
	}

	public void apply(PlayerEntity player)
	{
		for (Map.Entry<PlayerAttribute, Float> loop : Attributes.entrySet())
			player.getAttributeInstance(loop.getKey().Attribute()).addPersistentModifier(new EntityAttributeModifier(CLASS_MODIFIER_UUID, "Class bonus", loop.getValue(), EntityAttributeModifier.Operation.ADDITION));
		for (PlayerAbility loop : PlayerAbilities.ABILITIES)
		{
			PlayerAbilityModifier modifier = Abilities.get(loop.getId());
			if (modifier != null && modifier.Value() != 0)
				PlayerState.getPlayerData(player).Abilities.get(loop.getId()).addModifier(modifier);
		}
		((PlayerDataAccessor) player).setPlayerData(PlayerState.getPlayerData(player));
		player.setHealth((float) player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).getValue());
	}

	public void clear(PlayerEntity player)
	{
		for (Map.Entry<PlayerAttribute, Float> loop : Attributes.entrySet())
			player.getAttributeInstance(loop.getKey().Attribute()).removeModifier(CLASS_MODIFIER_UUID);
		for (PlayerAbility loop : PlayerAbilities.ABILITIES)
			PlayerState.getPlayerData(player).Abilities.get(loop.getId()).removeModifier(CLASS_ABILITY_ID);
		((PlayerDataAccessor) player).setPlayerData(PlayerState.getPlayerData(player));
		player.setHealth((float) player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).getValue());
	}

	public PlayerClass health(float health)
	{
		Attributes.put(PlayerAttributes.HEALTH, health);
		return this;
	}

	public PlayerClass armor(float armor)
	{
		Attributes.put(PlayerAttributes.ARMOR, armor);
		return this;
	}

	public PlayerClass armorToughness(float armorToughness)
	{
		Attributes.put(PlayerAttributes.ARMOR_TOUGHNESS, armorToughness);
		return this;
	}

	public PlayerClass attackDamage(float attackDamage)
	{
		Attributes.put(PlayerAttributes.ATTACK_DAMAGE, attackDamage);
		return this;
	}

	public PlayerClass attackSpeed(float attackSpeed)
	{
		Attributes.put(PlayerAttributes.ATTACK_SPEED, attackSpeed);
		return this;
	}

	public PlayerClass knockbackResistance(float knockbackResistance)
	{
		Attributes.put(PlayerAttributes.KNOCKBACK_RESISTANCE, knockbackResistance);
		return this;
	}

	public PlayerClass luck(float luck)
	{
		Attributes.put(PlayerAttributes.LUCK, luck);
		return this;
	}

	public PlayerClass movementSpeed(float movementSpeed)
	{
		Attributes.put(PlayerAttributes.MOVEMENT_SPEED, movementSpeed);
		return this;
	}

	public PlayerClass strength(int strength)
	{
		Abilities.put(PlayerAbilities.STRENGTH.getId(), new PlayerAbilityModifier(CLASS_ABILITY_ID, strength));
		return this;
	}

	public PlayerClass dexterity(int dexterity)
	{
		Abilities.put(PlayerAbilities.DEXTERITY.getId(), new PlayerAbilityModifier(CLASS_ABILITY_ID, dexterity));
		return this;
	}

	public PlayerClass intelligence(int intelligence)
	{
		Abilities.put(PlayerAbilities.INTELLIGENCE.getId(), new PlayerAbilityModifier(CLASS_ABILITY_ID, intelligence));
		return this;
	}

	public PlayerClass wisdom(int wisdom)
	{
		Abilities.put(PlayerAbilities.WISDOM.getId(), new PlayerAbilityModifier(CLASS_ABILITY_ID, wisdom));
		return this;
	}
}
