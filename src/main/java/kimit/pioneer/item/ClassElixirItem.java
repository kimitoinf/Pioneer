package kimit.pioneer.item;

import kimit.pioneer.elixir.ClassElixir;
import kimit.pioneer.elixir.ClassElixirUtil;
import kimit.pioneer.player.PlayerDataAccessor;
import kimit.pioneer.player.PlayerState;
import kimit.pioneer.registry.ItemRegistry;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class ClassElixirItem extends Item
{
	private static final int MAX_USE_TIME = 64;

	public ClassElixirItem(Settings settings)
	{
		super(settings);
	}

	@Override
	public int getMaxUseTime(ItemStack stack)
	{
		return MAX_USE_TIME;
	}

	@Override
	public UseAction getUseAction(ItemStack stack)
	{
		return UseAction.DRINK;
	}

	@Override
	public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user)
	{
		if (user instanceof PlayerEntity player)
		{
			if (player instanceof ServerPlayerEntity serverPlayer)
				Criteria.CONSUME_ITEM.trigger(serverPlayer, stack);

			player.incrementStat(Stats.USED.getOrCreateStat(this));
			if (!player.getAbilities().creativeMode)
			{
				stack.decrement(1);
				player.getInventory().insertStack(new ItemStack(ItemRegistry.ELIXIR_BOTTLE_ITEM));
			}

			if (!world.isClient)
			{
				ClassElixir elixir = ClassElixirUtil.getClassElixir(stack);
				if (elixir != null)
				{
					PlayerState.getPlayerData(player).Class = elixir.Class();
					((PlayerDataAccessor) user).setPlayerData(PlayerState.getPlayerData(user));
					elixir.Class().clear(player);
					elixir.Class().apply(player);
				}
			}

			user.emitGameEvent(GameEvent.DRINK);
		}
		return stack;
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand)
	{
		return ItemUsage.consumeHeldItem(world, user, hand);
	}

	@Override
	public String getTranslationKey(ItemStack stack)
	{
		ClassElixir elixir = ClassElixirUtil.getClassElixir(stack);
		if (elixir != null)
			return this.getTranslationKey() + "." + elixir.Class().getId();
		else
			return this.getTranslationKey();
	}
}
