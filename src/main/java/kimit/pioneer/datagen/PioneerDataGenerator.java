package kimit.pioneer.datagen;

import kimit.pioneer.datagen.lang.EnglishLangProvider;
import kimit.pioneer.datagen.lang.KoreanLangProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class PioneerDataGenerator implements DataGeneratorEntrypoint
{
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator)
	{
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(ModelProvider::new);
		pack.addProvider(EnglishLangProvider::new);
		pack.addProvider(KoreanLangProvider::new);
	}
}
