package kimit.pioneer.registry;

import kimit.pioneer.Pioneer;
import kimit.pioneer.entity.VillagerEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class EntityRegistry
{
	public static final EntityType<VillagerEntity> VILLAGER = Registry.register(Registries.ENTITY_TYPE, Pioneer.getId("villager"),
			FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, VillagerEntity::new).dimensions(EntityDimensions.fixed(0.75f, 2f)).build());

	public static void init()
	{
		FabricDefaultAttributeRegistry.register(VILLAGER, VillagerEntity.createVillagerAttributes());
	}
}
