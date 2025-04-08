package yuuki1293.pccard.wrapper;

import appeng.api.upgrades.IUpgradeInventory;
import appeng.api.upgrades.IUpgradeableObject;
import appeng.menu.ToolboxMenu;
import org.spongepowered.asm.mixin.Unique;

public interface IPatternProviderMenuMixin {
    @Unique
    void pCCard$setupUpgrades();

    @Unique
    IUpgradeableObject pCCard$getHost();

    @Unique
    IUpgradeInventory pCCard$getUpgrades();

    @Unique
    ToolboxMenu pCCard$getToolbox();
}
