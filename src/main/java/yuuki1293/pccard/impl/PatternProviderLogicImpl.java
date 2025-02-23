package yuuki1293.pccard.impl;

import appeng.api.crafting.IPatternDetails;
import appeng.api.crafting.PatternDetailsHelper;
import com.gregtechceu.gtceu.api.machine.SimpleTieredMachine;
import com.gregtechceu.gtceu.api.machine.feature.IHasCircuitSlot;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableItemStackHandler;
import com.gregtechceu.gtceu.common.item.IntCircuitBehaviour;
import com.mojang.logging.LogUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import org.slf4j.Logger;
import yuuki1293.pccard.CompetitionFixer;
import yuuki1293.pccard.IPatternProviderLogicMixin;
import yuuki1293.pccard.TagUtils;
import yuuki1293.pccard.wrapper.IAEPattern;

import java.util.Arrays;

public class PatternProviderLogicImpl {
    private static final Logger LOGGER = LogUtils.getLogger();

    public static IPatternDetails updatePatterns(IPatternProviderLogicMixin self, IPatternDetails detail, ItemStack stack) {
        if (self.pCCard$hasPCCard()) {
            var newStack = stack.copy();
            var inputs = TagUtils.getInputsFromPattern(newStack);

            if (inputs.isPresent()) {
                var number = TagUtils.getCircuitNumber(inputs.get()).orElse(0);
                TagUtils.removeCircuit(inputs.get());

                detail = PatternDetailsHelper.decodePattern(newStack, self.pCCard$getBlockEntity().getLevel());
                if (detail instanceof IAEPattern wrapper) {
                    wrapper.pCCard$setNumber(number);
                }
            }
        }

        return detail;
    }

    public static void setPCNumber(IPatternProviderLogicMixin self, IPatternDetails patternDetails) {
        if (self.pCCard$hasPCCard() && patternDetails instanceof IAEPattern patternDetailsW) {
            var be = self.pCCard$getBlockEntity();
            var level = be.getLevel();
            if (level == null) return;

            var blockPos = self.pCCard$getSendPos();
            var gtMachine = SimpleTieredMachine.getMachine(level, blockPos);
            if (gtMachine == null) return; // filter gtMachine

            if (gtMachine instanceof IHasCircuitSlot machine) {
                var inv = machine.getCircuitInventory();
                setInvNumber(inv, patternDetailsW);
            }
        }
    }

    private static void setInvNumber(NotifiableItemStackHandler inv, IAEPattern details) {
        var machineStack = CompetitionFixer.PC.get().asStack();

        var number = details.pCCard$getNumber();
        IntCircuitBehaviour.setCircuitConfiguration(machineStack, number);
        inv.setStackInSlot(0, machineStack);
    }

    /**
     * support MAE2 pattern p2p
     */
    public static BlockPos getSendPos(IPatternProviderLogicMixin self, Class<?> parent) {
        try {
            // stone.mae2.mixins.PatternProviderLogicMixin.pushPattern
            if (Arrays.stream(parent.getDeclaredFields()).anyMatch(f -> f.getName().equals("sendPos"))) {
                var posFiled = parent.getDeclaredField("sendPos");
                posFiled.setAccessible(true);
                var pos = posFiled.get(self);

                if (pos != null)
                    return (BlockPos) posFiled.get(self);
            }

            var be = self.pCCard$getBlockEntity();

            return be.getBlockPos().relative(self.pCCard$getSendDirection());
        } catch (Exception e) {
            LOGGER.error("Error while getting sendPos", e);
            return BlockPos.ZERO;
        }
    }
}
