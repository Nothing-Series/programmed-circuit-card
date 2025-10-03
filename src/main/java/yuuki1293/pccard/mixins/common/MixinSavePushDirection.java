package yuuki1293.pccard.mixins.common;

import appeng.api.crafting.IPatternDetails;
import appeng.api.upgrades.IUpgradeableObject;
import appeng.helpers.patternprovider.PatternProviderLogic;
import appeng.helpers.patternprovider.PatternProviderLogicHost;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import yuuki1293.pccard.PCCard;
import yuuki1293.pccard.impl.PatternProviderLogicImpl;
import yuuki1293.pccard.wrapper.IPatternProviderLogicMixin;

import java.util.List;

@Mixin(value = PatternProviderLogic.class, remap = false)
public abstract class MixinSavePushDirection implements IUpgradeableObject, IPatternProviderLogicMixin {
    @Unique
    private static Direction pCCard$sendDirection;

    @Inject(method = "pushPattern", at = @At(value = "INVOKE", target = "Lappeng/helpers/patternprovider/PatternProviderLogic;onPushPatternSuccess(Lappeng/api/crafting/IPatternDetails;)V", ordinal = 0), require = 1)
    private void pushPatternCraftingMachine(CallbackInfoReturnable<Boolean> cir, @Local(ordinal = 0) Direction direction) {
        pCCard$sendDirection = direction;
    }

    @Inject(method = "pushPattern", at = @At(value = "INVOKE", target = "Lappeng/helpers/patternprovider/PatternProviderLogic;onPushPatternSuccess(Lappeng/api/crafting/IPatternDetails;)V", ordinal = 1), require = 1)
    private void pushPatternProcessingMachine(CallbackInfoReturnable<Boolean> cir, @Local(ordinal = 0) Direction direction) {
        pCCard$sendDirection = direction;
    }
}

