package kr.eme.semiTerminal.managers

import kr.eme.semiTerminal.objects.EntityPair
import kr.eme.semiTerminal.utils.ComponentUtils.convertComponentToString
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.TextComponent
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.TextDisplay

object EntityPairManager {
    private val entityMap = HashMap<Component, EntityPair>()
    fun registerEntityPair(key: Component, armorStand: ArmorStand, textDisplay: TextDisplay) {
        entityMap[key] = EntityPair(armorStand, textDisplay)
    }
    fun getKey(key: Component): EntityPair? = entityMap[key]
    fun getArmorStand(armorStand: ArmorStand): String = convertComponentToString(armorStand.customName())
    fun getEntityPair(key: Component): EntityPair? = entityMap[key]
    fun updateTextDisplay(entityPair: EntityPair) {
        var text :TextComponent = Component.text("")
        when (entityPair.sceneIndex) {
            0 -> {
                text = when (entityPair.selectedOption) {
                    0 -> Component.text("상점을 이용하시겠습니까?\n§a[네]§f[아니요]") // '네' 선택
                    1 -> Component.text("상점을 이용하시겠습니까?\n[네]§a[아니요]") // '아니요' 선택
                    else -> Component.text("상점을 이용하시겠습니까?\n§a[네]§f[아니요]") // 기본값
                }
            }
            1 -> {
                text = when (entityPair.selectedOption) {
                    0 -> Component.text("선택해주세요.\n§a[작물]§f[기타]") // '작물' 선택
                    1 -> Component.text("선택해주세요.\n§f[작물]§a[기타]") // '기타' 선택
                    else -> Component.text("선택해주세요.\n§a[작물]§f[기타]") // 기본값
                }
            }
            2 -> {
                text = when (entityPair.selectedOption) {
                    0 -> Component.text("구매할 작물을 선택해주세요.\n§a[봄]§f[여름][가을]") // '작물' 선택
                    1 -> Component.text("구매할 작물을 선택해주세요.\n§f[봄]§a[여름]§f[가을]") // '작물' 선택
                    2 -> Component.text("구매할 작물을 선택해주세요.\n§a[봄][여름]§a[가을]") // '작물' 선택
                    else -> Component.text("구매할 작물을 선택해주세요.\n§a[봄]§f[여름][가을]") // 기본값
                }
            }
        }
        if (text == Component.text("")) return
        entityPair.textDisplay.text(text)
    }
    fun removeEntityPair(key: Component) {
        val entityPair = entityMap.remove(key) ?: return
        entityPair.armorStand.remove()
        entityPair.textDisplay.remove()
    }
}