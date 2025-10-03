package yuuki1293.pccard.mixins.expandedae;

import appeng.api.upgrades.IUpgradeableObject;
import appeng.helpers.patternprovider.PatternProviderLogic;
import com.bawnorton.mixinsquared.TargetHandler;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import yuuki1293.pccard.wrapper.IPatternProviderLogicMixin;

@Mixin(value = PatternProviderLogic.class, remap = false, priority = 1100)
public abstract class MixinExpSavePushDirection implements IUpgradeableObject, IPatternProviderLogicMixin {
    @Unique
    private static Direction pCCard$sendDirection;

    @TargetHandler(
        mixin = "lu.kolja.expandedae.mixin.patternprovider.MixinPatternProviderLogic",
        name = "Llu/kolja/expandedae/mixin/patternprovider/MixinPatternProviderLogic;pushPattern(Lappeng/api/crafting/IPatternDetails;[Lappeng/api/stacks/KeyCounter;)Z"
    )
    @Inject(
        method = "@MixinSquared:Handler",
        at = @At(value = "INVOKE", target = "Llu/kolja/expandedae/mixin/patternprovider/MixinPatternProviderLogic;onPushPatternSuccess(Lappeng/api/crafting/IPatternDetails;)V"), require = 4
    )
    private void pushPatternCraftingMachine(CallbackInfoReturnable<Boolean> cir, @Local(ordinal = 0) Direction direction) {
        pCCard$sendDirection = direction;
    }
}

