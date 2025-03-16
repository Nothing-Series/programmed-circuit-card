package yuuki1293.pccard;

import appeng.api.crafting.IPatternDetails;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntity;

import java.util.List;

public interface IPatternProviderLogicMixin {
    /**
     *  NOTE: call after {@code pushPattern}
     */
    void pCCard$setPCNumber(IPatternDetails patternDetails);

    boolean pCCard$hasPCCard();

    /**
     * @return machine block pos
     */
    List<BlockPos> pCCard$getSendPos();

    /**
     * @return send direction
     */
    Direction pCCard$getSendDirection();

    /**
     * @return host's BlockEntity
     */
    BlockEntity pCCard$getBlockEntity();
}
