package kimit.pioneer.elixir;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

public class ClassElixirUtil
{
	public static final String CLASS_ELIXIR_KEY = "ClassElixir";

	public static ItemStack setClassElixir(ItemStack stack, ClassElixir elixir)
	{
		stack.getOrCreateNbt().putString(CLASS_ELIXIR_KEY, elixir.Class().getId());
		return stack;
	}

	public static ClassElixir getClassElixir(ItemStack stack)
	{
		NbtCompound nbt = stack.getNbt();
		if (nbt != null)
			return ClassElixirs.fromId(nbt.getString(CLASS_ELIXIR_KEY));
		return null;
	}
}
