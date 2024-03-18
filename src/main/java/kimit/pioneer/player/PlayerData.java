package kimit.pioneer.player;

import kimit.pioneer.player.abilities.PlayerAbilities;
import kimit.pioneer.player.classes.PlayerClass;
import kimit.pioneer.player.classes.PlayerClasses;
import net.minecraft.nbt.NbtCompound;

import java.util.HashMap;
import java.util.Map;

public class PlayerData
{
	public static final String ATTRIBUTES_KEY = "Attributes";
	public static final String ABILITIES_KEY = "Abilities";
	public static final String CLASS_KEY = "Class";
	public final Map<String, Float> Attributes = new HashMap<>();
	public final Map<String, Integer> Abilities = new HashMap<>();
	public PlayerClass Class = PlayerClasses.NONE;

	public PlayerData()
	{
		for (String loop : PlayerAbilities.ABILITIES)
			Abilities.put(loop, 0);
	}

	public NbtCompound getNbt()
	{
		NbtCompound player = new NbtCompound();

		NbtCompound attributes = new NbtCompound();
		Attributes.forEach(attributes::putFloat);
		player.put(ATTRIBUTES_KEY, attributes);

		NbtCompound abilities = new NbtCompound();
		Abilities.forEach(abilities::putInt);
		player.put(ABILITIES_KEY, abilities);

		player.putString(CLASS_KEY, Class.getId());

		return player;
	}

	public static PlayerData fromNbt(NbtCompound nbt)
	{
		PlayerData player = new PlayerData();

		NbtCompound attributes = nbt.getCompound(ATTRIBUTES_KEY);
		attributes.getKeys().forEach(key -> player.Attributes.put(key, attributes.getFloat(key)));

		NbtCompound abilities = nbt.getCompound(ABILITIES_KEY);
		abilities.getKeys().forEach(key -> player.Abilities.put(key, abilities.getInt(key)));

		player.Class = PlayerClasses.fromId(nbt.getString(CLASS_KEY));

		return player;
	}
}
