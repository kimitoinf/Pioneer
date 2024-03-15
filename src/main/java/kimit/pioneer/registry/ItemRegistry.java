package kimit.pioneer.registry;

import kimit.pioneer.Pioneer;
import kimit.pioneer.elixir.*;
import kimit.pioneer.item.ClassElixirItem;
import kimit.pioneer.item.ElixirBottleItem;
import kimit.pioneer.item.ElixirItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Rarity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ItemRegistry
{
	public static final ElixirBottleItem ELIXIR_BOTTLE_ITEM = new ElixirBottleItem(new FabricItemSettings());
	public static final ElixirItem ELIXIR_ITEM = new ElixirItem(new FabricItemSettings().maxCount(1));
	public static final ClassElixirItem CLASS_ELIXIR_ITEM = new ClassElixirItem(new FabricItemSettings().maxCount(1).rarity(Rarity.RARE));
	private static final List<ItemStack> ITEMS = new ArrayList<>();
	public static final ItemGroup PIONEER_GROUP = FabricItemGroup.builder()
			.icon(() -> new ItemStack(ELIXIR_BOTTLE_ITEM))
			.displayName(Text.translatable("itemGroup.pioneer.group"))
			.entries(((displayContext, entries) -> entries.addAll(ITEMS)))
			.build();

	public static void init()
	{
		Registry.register(Registries.ITEM, Pioneer.getId("elixir_bottle"), ELIXIR_BOTTLE_ITEM);
		Registry.register(Registries.ITEM, Pioneer.getId("elixir"), ELIXIR_ITEM);
		Registry.register(Registries.ITEM, Pioneer.getId("class_elixir"), CLASS_ELIXIR_ITEM);

		ITEMS.add(new ItemStack(ELIXIR_BOTTLE_ITEM));
		for (Map.Entry<String, Elixir> loop : Elixirs.ELIXIRS.entrySet())
			for (ElixirQuality loop2 : ElixirQuality.VALUES)
				ITEMS.add(ElixirUtil.setElixir(new ItemStack(ELIXIR_ITEM), loop.getValue(), loop2));
		for (Map.Entry<String, ClassElixir> loop : ClassElixirs.CLASS_ELIXIRS.entrySet())
			ITEMS.add(ClassElixirUtil.setClassElixir(new ItemStack(CLASS_ELIXIR_ITEM), loop.getValue()));

		Registry.register(Registries.ITEM_GROUP, Pioneer.getId("group"), PIONEER_GROUP);
	}
}
