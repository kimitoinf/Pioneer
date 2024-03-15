package kimit.pioneer.datagen;

import kimit.pioneer.Pioneer;
import kimit.pioneer.registry.ItemRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.Models;

import java.util.Optional;

public class ModelProvider extends FabricModelProvider
{
	public ModelProvider(FabricDataOutput output)
	{
		super(output);
	}

	@Override
	public void generateBlockStateModels(BlockStateModelGenerator generator)
	{

	}

	@Override
	public void generateItemModels(ItemModelGenerator generator)
	{
		generator.register(ItemRegistry.ELIXIR_BOTTLE_ITEM, Models.GENERATED);
	}
}
