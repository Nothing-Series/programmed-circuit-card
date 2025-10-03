package yuuki1293.pccard.mixins.common;

import appeng.api.upgrades.IUpgradeInventory;
import appeng.api.upgrades.IUpgradeableObject;
import appeng.helpers.patternprovider.PatternProviderLogic;
import appeng.helpers.patternprovider.PatternProviderLogicHost;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = PatternProviderLogicHost.class, remap = false)
public interface MixinPatternProviderLogicHost extends IUpgradeableObject {
    @Shadow
    PatternProviderLogic getLogic();

    default IUpgradeInventory getUpgrades() {
        return ((IUpgradeableObject) this.getLogic()).getUpgrades();
    }
}
