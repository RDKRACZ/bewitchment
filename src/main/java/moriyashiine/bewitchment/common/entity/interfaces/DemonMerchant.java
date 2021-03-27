package moriyashiine.bewitchment.common.entity.interfaces;

import moriyashiine.bewitchment.api.BewitchmentAPI;
import moriyashiine.bewitchment.common.entity.living.DemonEntity;
import moriyashiine.bewitchment.common.registry.BWPledges;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface DemonMerchant {
	List<DemonEntity.DemonTradeOffer> getOffers();
	
	LivingEntity getDemonTrader();
	
	default void trade(DemonEntity.DemonTradeOffer offer) {
		if (!getDemonTrader().world.isClient && offer.isUsable()) {
			offer.apply(this);
		}
	}
	
	void onSell(DemonEntity.DemonTradeOffer offer);
	
	void setCurrentCustomer(PlayerEntity customer);
	
	@Nullable PlayerEntity getCurrentCustomer();
	
	default boolean isDiscount() {
		return getCurrentCustomer() != null && BewitchmentAPI.isPledged(getCurrentCustomer().world, BWPledges.BAPHOMET, getCurrentCustomer().getUuid());
	}
	
	@Environment(EnvType.CLIENT)
	default void setDiscountClientside(boolean discount) {
	}
	
	@Environment(EnvType.CLIENT)
	default void setOffersClientside(List<DemonEntity.DemonTradeOffer> offers) {
	}
	
	@Environment(EnvType.CLIENT)
	default void setDemonTraderClientside(LivingEntity trader) {
	}
}
