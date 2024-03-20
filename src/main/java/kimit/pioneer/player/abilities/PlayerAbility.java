package kimit.pioneer.player.abilities;

import net.minecraft.nbt.NbtCompound;

import java.util.HashMap;
import java.util.Map;

public class PlayerAbility
{
	public static final String ID_KEY = "Id";
	public static final String BASE_VALUE_KEY = "BaseValue";
	public static final String MODIFIERS_KEY = "Modifiers";
	private final String Id;
	private int BaseValue;
	private final Map<String, PlayerAbilityModifier> Modifiers;

	public PlayerAbility(String id)
	{
		Id = id;
		Modifiers = new HashMap<>();
		BaseValue = 0;
	}

	public PlayerAbility(String id, int baseValue)
	{
		this(id);
		BaseValue = baseValue;
	}

	public PlayerAbility(PlayerAbility ability)
	{
		Id = ability.Id;
		BaseValue = ability.BaseValue;
		Modifiers = new HashMap<>(ability.Modifiers);
	}

	public NbtCompound getNbt()
	{
		NbtCompound nbt = new NbtCompound();

		nbt.putString(ID_KEY, Id);
		nbt.putInt(BASE_VALUE_KEY, BaseValue);

		NbtCompound modifiers = new NbtCompound();
		Modifiers.forEach((key, value) -> modifiers.putInt(key, value.Value()));
		nbt.put(MODIFIERS_KEY, modifiers);

		return nbt;
	}

	public static PlayerAbility fromNbt(NbtCompound nbt)
	{
		PlayerAbility ability = new PlayerAbility(nbt.getString(ID_KEY), nbt.getInt(BASE_VALUE_KEY));
		NbtCompound modifiers = nbt.getCompound(MODIFIERS_KEY);
		modifiers.getKeys().forEach(key -> ability.addModifier(new PlayerAbilityModifier(key, modifiers.getInt(key))));

		return ability;
	}

	public void addModifier(PlayerAbilityModifier modifier)
	{
		Modifiers.put(modifier.Id(), modifier);
	}

	public void removeModifier(String id)
	{
		Modifiers.remove(id);
	}

	public void removeModifier(PlayerAbilityModifier modifier)
	{
		Modifiers.remove(modifier.Id());
	}

	public String getId()
	{
		return Id;
	}

	public int getBaseValue()
	{
		return BaseValue;
	}

	public void addBaseValue(int value)
	{
		BaseValue += value;
	}

	public int getValue()
	{
		return BaseValue + Modifiers.values().stream().mapToInt(PlayerAbilityModifier::Value).sum();
	}
}
