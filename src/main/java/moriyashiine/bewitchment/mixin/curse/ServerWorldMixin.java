package moriyashiine.bewitchment.mixin.curse;

import moriyashiine.bewitchment.api.component.CursesComponent;
import moriyashiine.bewitchment.common.registry.BWCurses;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Box;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerWorld.class)
public abstract class ServerWorldMixin {
	@Inject(method = "spawnEntity", at = @At("HEAD"))
	private void spawnEntity(Entity entity, CallbackInfoReturnable<Boolean> callbackInfo) {
		if (entity instanceof LightningEntity) {
			LivingEntity closest = null;
			for (LivingEntity foundLiving : entity.world.getEntitiesByClass(LivingEntity.class, new Box(entity.getBlockPos()).expand(256), currentLiving -> currentLiving.isAlive() && CursesComponent.get(currentLiving).hasCurse(BWCurses.LIGHTNING_ROD))) {
				if (closest == null || foundLiving.distanceTo(entity) < closest.distanceTo(entity)) {
					closest = foundLiving;
				}
			}
			if (closest != null) {
				entity.updatePositionAndAngles(closest.getX(), closest.getY(), closest.getZ(), entity.world.random.nextFloat() * 360, 0);
			}
		}
	}
}
