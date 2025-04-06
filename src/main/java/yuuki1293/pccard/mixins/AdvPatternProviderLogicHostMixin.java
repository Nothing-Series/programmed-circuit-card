package yuuki1293.pccard.mixins;

import appeng.api.upgrades.IUpgradeInventory;
import appeng.api.upgrades.IUpgradeableObject;
import net.pedroksl.advanced_ae.common.logic.AdvPatternProviderLogic;
import net.pedroksl.advanced_ae.common.logic.AdvPatternProviderLogicHost;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = AdvPatternProviderLogicHost.class, remap = false)
public interface AdvPatternProviderLogicHostMixin extends IUpgradeableObject {
    @Shadow
    AdvPatternProviderLogic getLogic();

    default IUpgradeInventory getUpgrades() {
        return ((IUpgradeableObject) this.getLogic()).getUpgrades();
    }
}
