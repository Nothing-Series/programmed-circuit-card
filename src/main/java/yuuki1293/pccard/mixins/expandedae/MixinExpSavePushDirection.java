package yuuki1293.pccard.mixins.expandedae;

import appeng.helpers.patternprovider.PatternProviderLogic;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import yuuki1293.pccard.wrapper.IPatternProviderLogicMixin;

@Mixin(value = PatternProviderLogic.class, remap = false, priority = 1100)
public abstract class MixinExpSavePushDirection implements IPatternProviderLogicMixin {
    @Inject(method = "pushPattern", at = @At(value = "INVOKE", target = "Lappeng/helpers/patternprovider/PatternProviderLogic;onPushPatternSuccess(Lappeng/api/crafting/IPatternDetails;)V"), require = 4)
    private void saveDirection(CallbackInfoReturnable<Boolean> cir, @Local(ordinal = 0) Direction direction) {
        pCCard$setSendDirection(direction);
    }
}

