package yuuki1293.pccard.mixins;

import appeng.api.parts.IPartItem;
import appeng.api.upgrades.IUpgradeInventory;
import appeng.api.upgrades.IUpgradeableObject;
import appeng.helpers.patternprovider.PatternProviderLogic;
import appeng.parts.AEBasePart;
import com.glodblock.github.extendedae.common.parts.PartExPatternProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = PartExPatternProvider.class, remap = false)
public abstract class PartExPatternProviderMixin extends AEBasePart implements IUpgradeableObject {
    @Shadow
    public abstract PatternProviderLogic getLogic();

    public PartExPatternProviderMixin(IPartItem<?> partItem) {
        super(partItem);
    }

    @Override
    public IUpgradeInventory getUpgrades() {
        return ((IUpgradeableObject) getLogic()).getUpgrades();
    }
}
