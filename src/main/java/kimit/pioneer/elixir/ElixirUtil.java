package kimit.pioneer.elixir;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Pair;

public class ElixirUtil
{
	public static final String ELIXIR_KEY = "Elixir";
	public static final String ELIXIR_ID_KEY = "Id";
	public static final String ELIXIR_QUALITY_KEY = "Quality";

	public static ItemStack setElixir(ItemStack stack, Elixir elixir, ElixirQuality quality)
	{
		NbtCompound nbt = new NbtCompound();
		nbt.putString(ELIXIR_ID_KEY, elixir.Attribute().Id());
		nbt.putInt(ELIXIR_QUALITY_KEY, quality.getQuality());
		stack.getOrCreateNbt().put(ELIXIR_KEY, nbt);
		return stack;
	}

	public static Pair<Elixir, ElixirQuality> getElixir(ItemStack stack)
	{
		NbtCompound nbt = stack.getNbt();
		if (nbt != null)
		{
			NbtCompound elixir = nbt.getCompound(ELIXIR_KEY);
			return new Pair<>(Elixirs.getElixir(elixir.getString(ELIXIR_ID_KEY)), ElixirQuality.fromId(elixir.getInt(ELIXIR_QUALITY_KEY)));
		}
		return null;
	}
}
