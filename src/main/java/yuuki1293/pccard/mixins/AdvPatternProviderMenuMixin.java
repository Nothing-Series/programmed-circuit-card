package yuuki1293.pccard.mixins;

import appeng.api.upgrades.IUpgradeInventory;
import appeng.api.upgrades.IUpgradeableObject;
import appeng.menu.AEBaseMenu;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MenuType;
import net.pedroksl.advanced_ae.common.logic.AdvPatternProviderLogicHost;
import net.pedroksl.advanced_ae.gui.advpatternprovider.AdvPatternProviderMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import yuuki1293.pccard.xmod.CompetitionFixer;
import yuuki1293.pccard.IPatternProviderMenuMixin;

@Mixin(value = AdvPatternProviderMenu.class, remap = false)
public class AdvPatternProviderMenuMixin extends AEBaseMenu implements IPatternProviderMenuMixin {
    @Unique
    private IUpgradeableObject pCCard$host;

    public AdvPatternProviderMenuMixin(MenuType<?> menuType, int id, Inventory playerInventory, Object host) {
        super(menuType, id, playerInventory, host);
    }

    @Inject(method = "<init>(ILnet/minecraft/world/entity/player/Inventory;Lnet/pedroksl/advanced_ae/common/logic/AdvPatternProviderLogicHost;)V", at = @At("TAIL"))
    private void init(int id, Inventory playerInventory, AdvPatternProviderLogicHost host, CallbackInfo ci) {
        if (CompetitionFixer.existAppflux.get()) return;

        this.pCCard$host = (IUpgradeableObject) host;
        this.pCCard$setupUpgrades();
    }

    @Unique
    public void pCCard$setupUpgrades() {
        setupUpgrades(this.pCCard$getHost().getUpgrades());
    }

    @Unique
    public IUpgradeableObject pCCard$getHost() {
        return this.pCCard$host;
    }

    @Unique
    public IUpgradeInventory pCCard$getUpgrades() {
        return pCCard$getHost().getUpgrades();
    }
}
