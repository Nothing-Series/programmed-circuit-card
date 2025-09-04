package yuuki1293.pccard.mixins;

import net.minecraftforge.fml.loading.FMLLoader;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PCCardMixinPlugin implements IMixinConfigPlugin {

    private static final String COMMON_MIXIN_PACKAGE = "yuuki1293.pccard.mixins.common.";
    private static final Map<String, Set<String>> LOAD_WHEN_MOD_PRESENT = new HashMap<>();
    private static final Map<String, Set<String>> EXCLUDE_WHEN_MOD_PRESENT = new HashMap<>();

    static {
        LOAD_WHEN_MOD_PRESENT.put("expandedae", Set.of(
        ));

        EXCLUDE_WHEN_MOD_PRESENT.put("expandedae", Set.of(
        ));
    }

    @Override
    public void onLoad(String mixinPackage) {
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        // Check if mixin should only load when specific mod is present
        for (Map.Entry<String, Set<String>> entry : LOAD_WHEN_MOD_PRESENT.entrySet()) {
            String requiredModId = entry.getKey();
            Set<String> mixinsForMod = entry.getValue();
            if (mixinsForMod.contains(mixinClassName)) {
                return FMLLoader.getLoadingModList().getModFileById(requiredModId) != null;
            }
        }
        
        // Check if mixin should be excluded when specific mod is present
        for (Map.Entry<String, Set<String>> entry : EXCLUDE_WHEN_MOD_PRESENT.entrySet()) {
            String conflictingModId = entry.getKey();
            Set<String> mixinsToExclude = entry.getValue();
            if (mixinsToExclude.contains(mixinClassName)) {
                return FMLLoader.getLoadingModList().getModFileById(conflictingModId) == null;
            }
        }
        
        // Default: only load common mixins
        return mixinClassName.startsWith(COMMON_MIXIN_PACKAGE);
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }
}
