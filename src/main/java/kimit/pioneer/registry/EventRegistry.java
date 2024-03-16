package kimit.pioneer.registry;

import kimit.pioneer.elixir.Elixirs;
import kimit.pioneer.player.PlayerDataAccessor;
import kimit.pioneer.player.PlayerData;
import kimit.pioneer.player.PlayerState;
import net.fabricmc.fabric.api.entity.FakePlayer;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.minecraft.entity.attribute.EntityAttributeModifier;

public class EventRegistry
{
	public static void init()
	{
		ServerPlayerEvents.AFTER_RESPAWN.register(((oldPlayer, newPlayer, alive) ->
		{
			PlayerData data = PlayerState.getPlayerData(newPlayer);
			((PlayerDataAccessor) newPlayer).setPlayerData(data);
			data.Attributes.forEach((id, value) ->
					newPlayer.getAttributeInstance(Elixirs.getElixir(id).Attribute().Attribute()).addPersistentModifier(new EntityAttributeModifier("Elixir bonus", value, EntityAttributeModifier.Operation.ADDITION)));
			data.Class.apply(newPlayer);
		}));
	}
}
