package kimit.pioneer.item;

import kimit.pioneer.elixir.Elixir;
import kimit.pioneer.elixir.ElixirQuality;
import kimit.pioneer.elixir.ElixirUtil;
import kimit.pioneer.player.PlayerState;
import kimit.pioneer.registry.ItemRegistry;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

public class ElixirItem extends Item
{
	private static final int MAX_USE_TIME = 64;

	public ElixirItem(Settings settings)
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
				Pair<Elixir, ElixirQuality> elixir = ElixirUtil.getElixir(stack);
				if (elixir != null)
				{
					Map<String, Float> attributes = PlayerState.getPlayerData(user).Attributes;
					float value = elixir.getRight().getQuality() * elixir.getLeft().Value();
					attributes.put(elixir.getLeft().Attribute().Id(), attributes.getOrDefault(elixir.getLeft().Attribute().Id(), 0.0f) + value);
					player.getAttributeInstance(elixir.getLeft().Attribute().Attribute()).addPersistentModifier(new EntityAttributeModifier("Elixir bonus", value, EntityAttributeModifier.Operation.ADDITION));
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
		Pair<Elixir, ElixirQuality> elixir = ElixirUtil.getElixir(stack);
		if (elixir != null)
			return this.getTranslationKey() + "." + elixir.getLeft().Attribute().Id();
		else
			return this.getTranslationKey();
	}

	@Override
	public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context)
	{
		Pair<Elixir, ElixirQuality> elixir = ElixirUtil.getElixir(stack);
		if (elixir != null)
			tooltip.add(Text.translatable(this.getTranslationKey() + ".tooltip.quality_" + elixir.getRight().getId()).formatted(Formatting.LIGHT_PURPLE));
	}
}
