package kimit.pioneer.player;

import net.minecraft.entity.data.TrackedDataHandler;
import net.minecraft.network.PacketByteBuf;

public class PlayerDataHandler implements TrackedDataHandler<PlayerData>
{
	@Override
	public void write(PacketByteBuf buf, PlayerData value)
	{
		buf.writeNbt(value.getNbt());
	}

	@Override
	public PlayerData read(PacketByteBuf buf)
	{
		return PlayerData.fromNbt(buf.readNbt());
	}

	@Override
	public PlayerData copy(PlayerData value)
	{
		return value;
	}
}
