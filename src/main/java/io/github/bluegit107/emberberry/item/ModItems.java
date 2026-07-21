package io.github.bluegit107.emberberry.item;

import io.github.bluegit107.emberberry.Emberberry;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;

/**
 * 모드에 등록되는 아이템들을 모아두는 클래스.
 * (Minecraft 1.21.1 / Yarn 매핑 기준)
 */
public class ModItems {

    // ── 임시 스탯 ─────────────────────────────
    // nutrition: 포만감 회복량 (사과 = 4)
    // saturationModifier: 포화도 계수 (사과 = 0.3)
    // 15% 확률로 8초간 화상 대신 "따뜻한" 컨셉으로 재생(REGENERATION) 부여 — 나중에 밸런싱 조정
    public static final FoodComponent EMBER_BERRY_FOOD = new FoodComponent.Builder()
            .nutrition(4)
            .saturationModifier(0.3f)
            .statusEffect(
                    new StatusEffectInstance(StatusEffects.REGENERATION, 100, 0),
                    0.15f
            )
            .build();

    public static final Item EMBER_BERRY = registerItem(
            "ember_berry",
            settings -> new EmberBerryItem(settings.food(EMBER_BERRY_FOOD))
    );

    private static Item registerItem(String name, Function<Item.Settings, Item> factory) {
        RegistryKey<Item> key = RegistryKey.of(
                RegistryKeys.ITEM,
                Identifier.of(Emberberry.MOD_ID, name)
        );
        Item item = factory.apply(new Item.Settings());
        return Registry.register(Registries.ITEM, key, item);
    }

    /** Emberberry#onInitialize() 에서 호출 */
    public static void registerItems() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK)
                .register(entries -> entries.add(EMBER_BERRY));
    }
}
