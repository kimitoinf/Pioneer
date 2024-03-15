package kimit.pioneer.client.registry;

import kimit.pioneer.elixir.ClassElixirUtil;
import kimit.pioneer.elixir.ElixirUtil;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.util.Colors;

public class ItemRegistry
{
	public static void init()
	{
		ColorProviderRegistry.ITEM.register(((stack, tintIndex) -> ElixirUtil.getElixir(stack).getLeft().getColor(tintIndex)), kimit.pioneer.registry.ItemRegistry.ELIXIR_ITEM);
		ColorProviderRegistry.ITEM.register(((stack, tintIndex) -> ClassElixirUtil.getClassElixir(stack).getColor(tintIndex)), kimit.pioneer.registry.ItemRegistry.CLASS_ELIXIR_ITEM);
	}
}
