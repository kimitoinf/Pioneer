package kimit.pioneer.client;

import kimit.pioneer.client.registry.EntityRegistry;
import kimit.pioneer.client.registry.ItemRegistry;
import net.fabricmc.api.ClientModInitializer;

public class PioneerClient implements ClientModInitializer
{
	@Override
	public void onInitializeClient()
	{
		ItemRegistry.init();
		EntityRegistry.init();
	}
}