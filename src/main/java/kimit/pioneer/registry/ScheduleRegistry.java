package kimit.pioneer.registry;

import kimit.pioneer.Pioneer;
import net.minecraft.entity.ai.brain.Activity;
import net.minecraft.entity.ai.brain.Schedule;
import net.minecraft.entity.ai.brain.ScheduleBuilder;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ScheduleRegistry
{
	public static final Schedule VILLAGER = new ScheduleBuilder(Registry.register(Registries.SCHEDULE, Pioneer.getId("villager"), new Schedule()))
			.withActivity(0, Activity.IDLE)
			.withActivity(2000, Activity.WORK)
			.withActivity(12000, Activity.REST)
			.build();

	public static void init()
	{

	}
}
