package kimit.pioneer.mixin;

import kimit.pioneer.player.PlayerData;
import kimit.pioneer.player.PlayerDataAccessor;
import kimit.pioneer.player.PlayerState;
import kimit.pioneer.registry.EntityRegistry;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin implements PlayerDataAccessor
{
	@Unique
	private static final String PLAYER_DATA_KEY = "PlayerData";
	@Unique
	private static final TrackedData<PlayerData> PLAYER_DATA = DataTracker.registerData(PlayerEntity.class, EntityRegistry.PLAYER_DATA_HANDLER);

	@Inject(method = "initDataTracker()V", at = @At("TAIL"))
	private void initPlayerData(CallbackInfo info)
	{
		((PlayerEntity)(Object) this).getDataTracker().startTracking(PLAYER_DATA, new PlayerData());
	}

	@Inject(method = "writeCustomDataToNbt(Lnet/minecraft/nbt/NbtCompound;)V", at = @At("TAIL"))
	private void writePlayerData(NbtCompound nbt, CallbackInfo info)
	{
		nbt.put(PLAYER_DATA_KEY, PlayerState.getPlayerData(((PlayerEntity)(Object) this)).getNbt());
	}

	@Inject(method = "readCustomDataFromNbt(Lnet/minecraft/nbt/NbtCompound;)V", at = @At("TAIL"))
	private void readPlayerData(NbtCompound nbt, CallbackInfo info)
	{
		this.setPlayerData(PlayerData.fromNbt(nbt.getCompound(PLAYER_DATA_KEY)));
	}

	@Override
	public PlayerData getPlayerData()
	{
		return ((PlayerEntity)(Object) this).getDataTracker().get(PLAYER_DATA);
	}

	@Override
	public void setPlayerData(PlayerData data)
	{
		((PlayerEntity)(Object) this).getDataTracker().set(PLAYER_DATA, data);
	}
}
