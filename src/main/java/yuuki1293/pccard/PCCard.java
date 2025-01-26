package yuuki1293.pccard;

import appeng.api.ids.AECreativeTabIds;
import appeng.api.upgrades.Upgrades;
import appeng.core.definitions.AEBlocks;
import appeng.core.definitions.AEParts;
import appeng.core.localization.GuiText;
import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

@Mod(PCCard.MODID)
public class PCCard {
    public static final String MODID = "pccard";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static final RegistryObject<Item> PROGRAMMED_CIRCUIT_CARD_ITEM = ITEMS.register("card_programmed_circuit", () -> Upgrades.createUpgradeCardItem(new Item.Properties()));

    public PCCard() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::onBuildCreativeModeTabContentsEvent);
        modEventBus.addListener(this::commonSetup);

        ITEMS.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(this::postRegistrationInitialization).whenComplete((res, err) -> {
            if (err != null) {
                LOGGER.warn(err.getMessage());
            }
        });
    }

    public void postRegistrationInitialization() {
        var patternProviderGroup = GuiText.CraftingInterface.getTranslationKey();
        var item = PROGRAMMED_CIRCUIT_CARD_ITEM.get();

        // AE2 Pattern Provider
        Upgrades.add(item, AEParts.PATTERN_PROVIDER, 1, patternProviderGroup);
        Upgrades.add(item, AEBlocks.PATTERN_PROVIDER, 1, patternProviderGroup);

        // Extended AE Pattern Provider
        var exPatternProviderGroup = "ME Extended Pattern Provider";
        var resourceExBE = new ResourceLocation("expatternprovider", "ex_pattern_provider");
        var resourceExPart = new ResourceLocation("expatternprovider", "ex_pattern_provider_part");
        var patternProviderExBE = ForgeRegistries.BLOCKS.getValue(resourceExBE);
        var patternProviderExPart = ForgeRegistries.ITEMS.getValue(resourceExPart);
        if (patternProviderExBE != null && patternProviderExPart != null) {
            Upgrades.add(item, patternProviderExBE, 1, exPatternProviderGroup);
            Upgrades.add(item, patternProviderExPart, 1, exPatternProviderGroup);
        }

        // Advanced AE Pattern Provider
        {
            var adPatternProviderGroup = "ME Advanced Pattern Provider";
            var namespaceAd = "advanced_ae";
            var resourceAdBE = new ResourceLocation(namespaceAd, "small_adv_pattern_provider");
            var resourceAdPart = new ResourceLocation(namespaceAd, "small_adv_pattern_provider_part");
            var resourceAdExBE = new ResourceLocation(namespaceAd, "adv_pattern_provider");
            var resourceAdExPart = new ResourceLocation(namespaceAd, "adv_pattern_provider_part");
            var patternProviderAdBE = ForgeRegistries.BLOCKS.getValue(resourceAdBE);
            var patternProviderAdPart = ForgeRegistries.ITEMS.getValue(resourceAdPart);
            var patternProviderAdExBE = ForgeRegistries.BLOCKS.getValue(resourceAdExBE);
            var patternProviderAdExPart = ForgeRegistries.ITEMS.getValue(resourceAdExPart);
            if (patternProviderAdBE != null && patternProviderAdPart != null && patternProviderAdExBE != null && patternProviderAdExPart != null) {
                Upgrades.add(item, patternProviderAdBE, 1, adPatternProviderGroup);
                Upgrades.add(item, patternProviderAdPart, 1, adPatternProviderGroup);
                Upgrades.add(item, patternProviderAdExBE, 1, adPatternProviderGroup);
                Upgrades.add(item, patternProviderAdExPart, 1, adPatternProviderGroup);
            }
        }
    }

    @SubscribeEvent
    public void onBuildCreativeModeTabContentsEvent(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey().equals(AECreativeTabIds.MAIN)) {
            event.accept(PROGRAMMED_CIRCUIT_CARD_ITEM);
            LOGGER.debug("Add Programmed Circuit Card in AE2 creative tab");
        }
    }
}
