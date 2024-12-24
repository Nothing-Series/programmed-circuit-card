package yuuki1293.pccard.mixins;

import appeng.api.parts.IPartItem;
import appeng.api.upgrades.IUpgradeInventory;
import appeng.api.upgrades.IUpgradeableObject;
import appeng.helpers.patternprovider.PatternProviderLogic;
import appeng.parts.AEBasePart;
import appeng.parts.crafting.PatternProviderPart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = PatternProviderPart.class, remap = false)
public abstract class PatternProviderPartMixin extends AEBasePart implements IUpgradeableObject {
    @Shadow public abstract PatternProviderLogic getLogic();

    public PatternProviderPartMixin(IPartItem<?> partItem) {
        super(partItem);
    }

    @Override
    public IUpgradeInventory getUpgrades() {
        return ((IUpgradeableObject) getLogic()).getUpgrades();
    }
}
