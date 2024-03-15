package kimit.pioneer.elixir;

public enum ElixirQuality
{
	LOW("low", 1),
	MEDIUM("medium", 2),
	HIGH("high", 3);


	public static final ElixirQuality[] VALUES = {LOW, MEDIUM, HIGH};
	private final String Id;
	private final int Quality;

	ElixirQuality(String id, int quality)
	{
		Id = id;
		Quality = quality;
	}

	public String getId()
	{
		return Id;
	}

	public int getQuality()
	{
		return Quality;
	}

	public static ElixirQuality fromId(int id)
	{
		if (id >= 1 && id <= VALUES.length)
			return VALUES[id - 1];
		else
			throw new IllegalArgumentException("No operation with value " + id);
	}
}
