package yuuki1293.pccard.mixins;

import appeng.api.upgrades.IUpgradeInventory;
import appeng.api.upgrades.IUpgradeableObject;
import appeng.blockentity.AEBaseBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.pedroksl.advanced_ae.common.entities.AdvPatternProviderEntity;
import net.pedroksl.advanced_ae.common.logic.AdvPatternProviderLogicHost;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = AdvPatternProviderEntity.class, remap = false)
public abstract class AdvPatternProviderEntityMixin extends AEBaseBlockEntity implements AdvPatternProviderLogicHost, IUpgradeableObject {
    public AdvPatternProviderEntityMixin(BlockEntityType<?> blockEntityType, BlockPos pos, BlockState blockState) {
        super(blockEntityType, pos, blockState);
    }

    @Override
    public IUpgradeInventory getUpgrades() {
        return ((IUpgradeableObject) getLogic()).getUpgrades();
    }
}
