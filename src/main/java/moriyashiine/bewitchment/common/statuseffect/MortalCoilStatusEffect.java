package moriyashiine.bewitchment.common.statuseffect;

import moriyashiine.bewitchment.common.registry.BWDamageSources;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

@SuppressWarnings("ConstantConditions")
public class MortalCoilStatusEffect extends StatusEffect {
	public MortalCoilStatusEffect(StatusEffectCategory category, int color) {
		super(category, color);
	}
	
	@Override
	public boolean canApplyUpdateEffect(int duration, int amplifier) {
		return true;
	}
	
	@Override
	public void applyUpdateEffect(LivingEntity entity, int amplifier) {
		if (entity.getStatusEffect(this).getDuration() == 1) {
			entity.damage(BWDamageSources.DEATH, Float.MAX_VALUE);
		}
	}
}
