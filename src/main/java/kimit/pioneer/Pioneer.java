package kimit.pioneer;

import kimit.pioneer.registry.EntityRegistry;
import kimit.pioneer.registry.EventRegistry;
import kimit.pioneer.registry.ItemRegistry;
import kimit.pioneer.registry.ScheduleRegistry;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Pioneer implements ModInitializer
{
	public static final String NAME = "pioneer";
    public static final Logger LOGGER = LoggerFactory.getLogger(NAME);

	@Override
	public void onInitialize()
	{
		LOGGER.info("Pioneer.");

		ItemRegistry.init();
		ScheduleRegistry.init();
		EntityRegistry.init();
		EventRegistry.init();
	}

	public static Identifier getId(String name)
	{
		return new Identifier(NAME, name);
	}
}