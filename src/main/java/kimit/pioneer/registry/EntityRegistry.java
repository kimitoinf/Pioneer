package kimit.pioneer.registry;

import kimit.pioneer.Pioneer;
import kimit.pioneer.entity.VillagerEntity;
import kimit.pioneer.player.PlayerData;
import kimit.pioneer.player.PlayerDataHandler;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.data.TrackedDataHandler;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class EntityRegistry
{
	public static final TrackedDataHandler<PlayerData> PLAYER_DATA_HANDLER = new PlayerDataHandler();
	public static final EntityType<VillagerEntity> VILLAGER = Registry.register(Registries.ENTITY_TYPE, Pioneer.getId("villager"),
			FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, VillagerEntity::new).dimensions(EntityDimensions.fixed(0.75f, 2f)).build());

	public static void init()
	{
		TrackedDataHandlerRegistry.register(PLAYER_DATA_HANDLER);
		FabricDefaultAttributeRegistry.register(VILLAGER, VillagerEntity.createVillagerAttributes());
	}
}
