package kimit.pioneer.datagen.lang;

import kimit.pioneer.registry.ItemRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryKey;

import java.io.IOException;

public class EnglishLangProvider extends FabricLanguageProvider
{
	public EnglishLangProvider(FabricDataOutput dataOutput)
	{
		super(dataOutput, "en_us");
	}

	@Override
	public void generateTranslations(TranslationBuilder builder)
	{
		builder.add(ItemRegistry.ELIXIR_BOTTLE_ITEM, "Elixir Bottle");
		builder.add(ItemRegistry.ELIXIR_ITEM, "Elixir");

		try
		{
			builder.add(dataOutput.getModContainer().findPath("assets/pioneer/lang/en_us.existing.json").get());
		}
		catch (IOException e)
		{
			throw new RuntimeException(e);
		}
	}
}
