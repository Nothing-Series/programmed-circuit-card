package yuuki1293.pccard.mixins;

import appeng.api.parts.IPartItem;
import appeng.api.upgrades.IUpgradeInventory;
import appeng.api.upgrades.IUpgradeableObject;
import appeng.parts.AEBasePart;
import net.pedroksl.advanced_ae.common.logic.AdvPatternProviderLogic;
import net.pedroksl.advanced_ae.common.parts.AdvPatternProviderPart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = AdvPatternProviderPart.class, remap = false)
public abstract class AdvPatternProviderPartMixin extends AEBasePart implements IUpgradeableObject {
    @Shadow
    public abstract AdvPatternProviderLogic getLogic();

    public AdvPatternProviderPartMixin(IPartItem<?> partItem) {
        super(partItem);
    }

    @Override
    public IUpgradeInventory getUpgrades() {
        return ((IUpgradeableObject) getLogic()).getUpgrades();
    }
}
