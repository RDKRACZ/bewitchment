package moriyashiine.bewitchment.common.entity.component;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import moriyashiine.bewitchment.common.registry.BWComponents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;

import java.util.Optional;

public class BroomUserComponent implements AutoSyncedComponent {
	private final PlayerEntity obj;
	private boolean pressingForward = false;
	
	public BroomUserComponent(PlayerEntity obj) {
		this.obj = obj;
	}
	
	@Override
	public void readFromNbt(NbtCompound tag) {
		setPressingForward(tag.getBoolean("PressingForward"));
	}
	
	@Override
	public void writeToNbt(NbtCompound tag) {
		tag.putBoolean("PressingForward", isPressingForward());
	}
	
	public boolean isPressingForward() {
		return pressingForward;
	}
	
	public void setPressingForward(boolean pressingForward) {
		this.pressingForward = pressingForward;
		BWComponents.BROOM_USER_COMPONENT.sync(obj);
	}
	
	public static BroomUserComponent get(PlayerEntity obj) {
		return BWComponents.BROOM_USER_COMPONENT.get(obj);
	}
	
	public static Optional<BroomUserComponent> maybeGet(PlayerEntity obj) {
		return BWComponents.BROOM_USER_COMPONENT.maybeGet(obj);
	}
}
