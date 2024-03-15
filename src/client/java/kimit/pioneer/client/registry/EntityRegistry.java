package kimit.pioneer.client.registry;

import kimit.pioneer.client.entity.render.VillagerEntityRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class EntityRegistry
{
	public static void init()
	{
		EntityRendererRegistry.register(kimit.pioneer.registry.EntityRegistry.VILLAGER, VillagerEntityRenderer::new);
	}
}
