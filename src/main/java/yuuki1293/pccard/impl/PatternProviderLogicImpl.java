package yuuki1293.pccard.impl;

import appeng.api.crafting.IPatternDetails;
import appeng.crafting.pattern.AEProcessingPattern;
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
import yuuki1293.pccard.wrapper.AEPatternWrapper;

import java.util.Arrays;
import java.util.Objects;

public class PatternProviderLogicImpl {
    private static final Logger LOGGER = LogUtils.getLogger();

    public static IPatternDetails updatePatterns(IPatternProviderLogicMixin self, IPatternDetails detail) {
        if (self.pCCard$hasPCCard()) {
            if (detail instanceof AEProcessingPattern) {
                final var definition = detail.getDefinition();
                final var originalInputs = detail.getInputs();
                final var originalOutputs = detail.getOutputs();

                var inputs = Arrays.stream(originalInputs)
                    .filter(Objects::nonNull)
                    .filter(x -> !x.getPossibleInputs()[0].what().getId().equals(CompetitionFixer.PC.get().getId())) // Check item
                    .toArray(IPatternDetails.IInput[]::new);

                if (!Arrays.equals(inputs, originalInputs)) {
                    var recipeStack = Arrays.stream(originalInputs)
                        .filter(Objects::nonNull)
                        .filter(x -> x.getPossibleInputs()[0].what().getId().equals(CompetitionFixer.PC.get().getId()))
                        .findFirst()
                        .map(x -> x.getPossibleInputs()[0].what().wrapForDisplayOrFilter())
                        .orElse(ItemStack.EMPTY);
                    var number = IntCircuitBehaviour.getCircuitConfiguration(recipeStack);

                    return new AEPatternWrapper(definition, inputs, originalOutputs, number);
                }
            }
        }

        return detail;
    }

    public static void setPCNumber(IPatternProviderLogicMixin self, IPatternDetails patternDetails) {
        if (self.pCCard$hasPCCard() && patternDetails instanceof AEPatternWrapper patternDetailsW) {
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

    private static void setInvNumber(NotifiableItemStackHandler inv, AEPatternWrapper details) {
        var machineStack = CompetitionFixer.PC.get().asStack();

        var number = details.getNumber();
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
