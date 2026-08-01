package io.github.bluegit107.emberberry.block;

import io.github.bluegit107.emberberry.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.item.Item;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;

/**
 * 엠버베리 작물 블록.
 *
 * 바닐라 BeetrootsBlock과 구조가 거의 동일합니다 (0~3, 4단계 성장).
 * CropBlock을 상속하는 것만으로 다음이 "공짜로" 딸려옵니다:
 *  - 농지(Farmland) 위에서만 심을 수 있는 제약
 *  - 빛 조건에 따른 랜덤 틱 성장 로직
 *  - 뼛가루(Fertilizable) 반응
 *
 * 우리가 직접 정의해야 하는 건 "이 작물이 몇 단계까지 자라는지"와
 * "부쉈을 때 기본으로 돌려줄 씨앗이 뭔지" 뿐입니다.
 *
 * ⚠️ 버전 확인 팁: IntelliJ에서 Ctrl+클릭으로 CropBlock, BeetrootsBlock
 * 소스로 들어가서 아래 메서드들의 정확한 시그니처(public/protected 여부,
 * 반환 타입)를 한 번 대조해보세요. 지금까지 겪으신 것처럼 마이너 버전마다
 * 미세하게 달라질 수 있는 지점입니다.
 */
public class EmberBerryCropBlock extends CropBlock {

    // Properties.AGE_3 = 0~3 범위의 age 프로퍼티 (바닐라 사탕무와 동일한 4단계)
    public static final IntProperty AGE = Properties.AGE_3;

    public EmberBerryCropBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected IntProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return 3;
    }

    @Override
    protected Item getSeedsItem() {
        return ModItems.EMBER_BERRY_SEEDS;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
}
