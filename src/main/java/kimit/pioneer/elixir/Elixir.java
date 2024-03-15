package kimit.pioneer.elixir;

import kimit.pioneer.player.attributes.PlayerAttribute;

public record Elixir(PlayerAttribute Attribute, float Value, int Color)
{
	public int getColor(int tintIndex)
	{
		return tintIndex == 1 ? Color : -1;
	}
}
