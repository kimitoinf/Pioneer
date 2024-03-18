package kimit.pioneer.datagen.lang;

import kimit.pioneer.registry.ItemRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

import java.io.IOException;

public class KoreanLangProvider extends FabricLanguageProvider
{
	public KoreanLangProvider(FabricDataOutput dataOutput)
	{
		super(dataOutput, "ko_kr");
	}

	@Override
	public void generateTranslations(FabricLanguageProvider.TranslationBuilder builder)
	{
		builder.add(ItemRegistry.ELIXIR_BOTTLE_ITEM, "엘릭서 병");
		builder.add(ItemRegistry.ELIXIR_ITEM, "엘릭서");

		try
		{
			builder.add(dataOutput.getModContainer().findPath("assets/pioneer/lang/ko_kr.existing.json").get());
		}
		catch (IOException e)
		{
			throw new RuntimeException(e);
		}
	}
}
