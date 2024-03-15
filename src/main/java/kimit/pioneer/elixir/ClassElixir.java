package kimit.pioneer.elixir;

import kimit.pioneer.player.classes.PlayerClass;

public record ClassElixir(PlayerClass Class, int Color)
{
	public int getColor(int tintIndex)
	{
		return tintIndex == 1 ? Color : -1;
	}
}
