package yuuki1293.pccard.mixins;

import appeng.api.parts.IPartItem;
import appeng.api.upgrades.IUpgradeInventory;
import appeng.api.upgrades.IUpgradeableObject;
import net.pedroksl.advanced_ae.common.parts.AdvPatternProviderPart;
import net.pedroksl.advanced_ae.common.parts.SmallAdvPatternProviderPart;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = SmallAdvPatternProviderPart.class, remap = false)
public abstract class SmallAdvPatternProviderPartMixin extends AdvPatternProviderPart implements IUpgradeableObject {
    public SmallAdvPatternProviderPartMixin(IPartItem<?> partItem) {
        super(partItem);
    }

    @Override
    public IUpgradeInventory getUpgrades() {
        return ((IUpgradeableObject) getLogic()).getUpgrades();
    }
}
