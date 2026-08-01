package io.github.bluegit107.emberberry.block;

import io.github.bluegit107.emberberry.Emberberry;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

/**
 * 블록 등록 전용 클래스. 아이템(ModItems)과 패턴을 맞췄습니다.
 *
 * ⚠️ AbstractBlock.Settings 빌더 메서드 이름(noCollision, ticksRandomly 등)은
 * 버전마다 조금씩 달라질 수 있어요. 컴파일 에러 나면 바닐라 Blocks.java에서
 * WHEAT 또는 BEETROOTS 등록 부분을 찾아 정확한 체이닝을 대조해보세요.
 */
public class ModBlocks {

    public static final Block EMBER_BERRY_CROP = registerBlock(
            "ember_berry_crop",
            EmberBerryCropBlock::new,
            AbstractBlock.Settings.create()
                    .noCollision()
                    .ticksRandomly()
                    .breakInstantly()
                    .sounds(BlockSoundGroup.CROP)
                    .pistonBehavior(net.minecraft.block.piston.PistonBehavior.DESTROY)
    );

    private static Block registerBlock(
            String name,
            java.util.function.Function<AbstractBlock.Settings, Block> factory,
            AbstractBlock.Settings settings
    ) {
        RegistryKey<Block> key = RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(Emberberry.MOD_ID, name));
        Block block = factory.apply(settings);
        return Registry.register(Registries.BLOCK, key, block);
    }

    /** Emberberry#onInitialize() 에서 호출 (지금은 static 초기화만으로 충분해서 내용은 비어있어도 무방) */
    public static void registerBlocks() {
    }
}
