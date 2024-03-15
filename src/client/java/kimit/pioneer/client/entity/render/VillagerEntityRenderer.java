package kimit.pioneer.client.entity.render;

import kimit.pioneer.entity.VillagerEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.util.Identifier;

public class VillagerEntityRenderer extends MobEntityRenderer<VillagerEntity, PlayerEntityModel<VillagerEntity>>
{
	public VillagerEntityRenderer(EntityRendererFactory.Context context)
	{
		super(context, new PlayerEntityModel<>(context.getPart(EntityModelLayers.PLAYER), false), 0.5f);
	}

	@Override
	public Identifier getTexture(VillagerEntity entity)
	{
		return new Identifier("minecraft", "textures/entity/player/wide/steve.png");
	}
}
