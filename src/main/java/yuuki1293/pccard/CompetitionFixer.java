package yuuki1293.pccard;

import com.google.common.base.Suppliers;
import com.gregtechceu.gtceu.api.item.ComponentItem;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.mojang.logging.LogUtils;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.forgespi.language.IModInfo;
import org.slf4j.Logger;

import java.util.Arrays;
import java.util.function.Supplier;

public class CompetitionFixer {
    private static final Logger LOGGER = LogUtils.getLogger();

    public static Supplier<Boolean> existAppflux = Suppliers.memoize(CompetitionFixer::hasPatternProviderUpgrade);
    public static Supplier<ItemEntry<ComponentItem>> PC = Suppliers.memoize(CompetitionFixer::getPC);

    private static boolean hasPatternProviderUpgrade() {
        ModList modList = ModList.get();

        return modList.getMods().stream()
            .map(IModInfo::getModId)
            .anyMatch(id -> id.equals("appflux")); // detect Applied Flux
    }

    /**
     * GTM 1.6+ GTItems.PROGRAMMED_CIRCUIT
     * older GTItems.INTEGRATED_CIRCUIT
     */
    private static ItemEntry<ComponentItem> getPC() {
        var clazz = GTItems.class;
        var field = Arrays.stream(clazz.getDeclaredFields())
            .filter(f -> f.getName().equals("INTEGRATED_CIRCUIT")
                || f.getName().equals("PROGRAMMED_CIRCUIT"))
            .findFirst();

        try {
            //noinspection unchecked,OptionalGetWithoutIsPresent
            return (ItemEntry<ComponentItem>) field.get().get(null);
        } catch (Exception e) {
            LOGGER.error("Could not get Programmed Circuit");
            throw new RuntimeException(e);
        }
    }
}
