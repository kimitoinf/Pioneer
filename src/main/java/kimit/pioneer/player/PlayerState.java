package kimit.pioneer.player;

import kimit.pioneer.Pioneer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.PersistentState;
import net.minecraft.world.PersistentStateManager;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerState extends PersistentState
{
	public static final String PLAYERS_KEY = "Players";
	private final Map<UUID, PlayerData> PLAYERS = new HashMap<>();
	private static final Type<PlayerState> TYPE = new Type<>(PlayerState::new, PlayerState::createFromNbt, null);

	@Override
	public NbtCompound writeNbt(NbtCompound nbt)
	{
		NbtCompound players = new NbtCompound();
		PLAYERS.forEach(((uuid, playerData) -> players.put(uuid.toString(), playerData.getNbt())));
		nbt.put(PLAYERS_KEY, players);
		return nbt;
	}

	public static PlayerState createFromNbt(NbtCompound nbt)
	{
		PlayerState state = new PlayerState();
		NbtCompound players = nbt.getCompound(PLAYERS_KEY);
		players.getKeys().forEach(key -> state.PLAYERS.put(UUID.fromString(key), PlayerData.fromNbt(players.getCompound(key))));
		return state;
	}

	public static PlayerState getServerState(MinecraftServer server)
	{
		PlayerState state = server.getWorld(World.OVERWORLD).getPersistentStateManager().getOrCreate(TYPE, Pioneer.NAME);
		state.markDirty();
		return state;
	}

	public static PlayerData getPlayerData(LivingEntity player)
	{
		return getServerState(player.getWorld().getServer()).PLAYERS.computeIfAbsent(player.getUuid(), uuid -> new PlayerData());
	}
}
