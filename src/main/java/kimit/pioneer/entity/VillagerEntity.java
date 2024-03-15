package kimit.pioneer.entity;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.entity.ai.brain.sensor.SensorType;
import net.minecraft.entity.ai.pathing.MobNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.GlobalPos;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.poi.PointOfInterestType;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.function.BiPredicate;

public class VillagerEntity extends PathAwareEntity
{
	private static final ImmutableList<MemoryModuleType<?>> MEMORY_MODULES = ImmutableList.of(MemoryModuleType.HOME, MemoryModuleType.JOB_SITE, MemoryModuleType.POTENTIAL_JOB_SITE);
	private static final ImmutableList<SensorType<? extends Sensor<? super net.minecraft.entity.passive.VillagerEntity>>> SENSORS = ImmutableList.of();
	public static final Map<MemoryModuleType<GlobalPos>, BiPredicate<net.minecraft.entity.passive.VillagerEntity, RegistryEntry<PointOfInterestType>>> POINTS_OF_INTEREST = ImmutableMap.of();

	public VillagerEntity(EntityType<? extends PathAwareEntity> entityType, World world)
	{
		super(entityType, world);
		((MobNavigation) getNavigation()).setCanPathThroughDoors(true);
		getNavigation().setCanSwim(true);
	}

	@Nullable
	@Override
	public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound entityNbt)
	{
		return super.initialize(world, difficulty, spawnReason, entityData, entityNbt);
	}

	public static DefaultAttributeContainer.Builder createVillagerAttributes()
	{
		return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 20.0).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.5);
	}

	@Override
	public Brain.Profile<?> createBrainProfile()
	{
		return super.createBrainProfile();
	}
}
