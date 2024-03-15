package kimit.pioneer.client.mixin;

import kimit.pioneer.Pioneer;
import kimit.pioneer.player.attributes.PlayerAttribute;
import kimit.pioneer.player.attributes.PlayerAttributes;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InventoryScreen.class)
public abstract class InventoryScreenMixin extends HandledScreen<PlayerScreenHandler>
{
	@Unique
	private static final String KEY = "gui.pioneer.playerdata";
	@Unique
	private static final Identifier PLAYERDATA = Pioneer.getId("textures/gui/playerdata.png");
	@Unique
	private static final PlayerAttribute[] ATTRIBUTES = {
			PlayerAttributes.HEALTH,
			PlayerAttributes.ARMOR,
			PlayerAttributes.ARMOR_TOUGHNESS,
			PlayerAttributes.ATTACK_DAMAGE,
			PlayerAttributes.ATTACK_SPEED,
			PlayerAttributes.KNOCKBACK_RESISTANCE,
			PlayerAttributes.LUCK,
			PlayerAttributes.MOVEMENT_SPEED
	};
	@Unique
	private static final float TEXT_SCALE = 0.75f;
	@Unique
	private static final float POS_MULTIPLIER = (float) (1.0 / TEXT_SCALE);
	@Unique
	private Text[] Attributes = new Text[ATTRIBUTES.length];

	public InventoryScreenMixin(PlayerScreenHandler handler, PlayerInventory inventory, Text title)
	{
		super(handler, inventory, title);
	}

	@Inject(method = "init()V", at = @At("HEAD"))
	private void initPlayerData(CallbackInfo info)
	{
		for (int loop = 0; loop < Attributes.length; loop++)
			Attributes[loop] = Text.translatable(KEY + "." + PlayerAttributes.PREFIX + "." + ATTRIBUTES[loop].Id(),
					String.format("%.2f", Math.round(client.player.getAttributeInstance(ATTRIBUTES[loop].Attribute()).getValue() * 1000) / 1000.0));
	}

	@Inject(method = "drawBackground(Lnet/minecraft/client/gui/DrawContext;FII)V", at = @At("HEAD"))
	private void drawPlayerData(DrawContext context, float delta, int mouseX, int mouseY, CallbackInfo info)
	{
		context.drawTexture(PLAYERDATA, 5, this.y, 0, 0, 110, 166);

		MatrixStack stack = context.getMatrices();
		stack.push();
		stack.scale(TEXT_SCALE, TEXT_SCALE, TEXT_SCALE);

		context.drawText(this.textRenderer, Text.translatable(KEY + "." + PlayerAttributes.PREFIX),
				(int) (POS_MULTIPLIER * 10), (int) (POS_MULTIPLIER * (this.y + 5)), 4210752, false);
		for (int loop = 0; loop < Attributes.length; loop++)
			context.drawText(this.textRenderer, Attributes[loop], (int) (POS_MULTIPLIER * 10), (int) (POS_MULTIPLIER * (this.y + 5 + 10 * (loop + 1))), 4210752, false);

		stack.pop();
	}
}
