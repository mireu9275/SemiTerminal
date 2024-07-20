package kr.eme.semiTerminal.managers

import kr.eme.semiTerminal.objects.EntityPair
import kr.eme.semiTerminal.utils.ComponentUtils.convertComponentToString
import net.kyori.adventure.text.Component
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.TextDisplay

object EntityPairManager {
    private val entityMap = HashMap<Component, EntityPair>()
    fun registerEntityPair(key: Component, armorStand: ArmorStand, textDisplay: TextDisplay) {
        entityMap[key] = EntityPair(armorStand, textDisplay)
    }
    fun getKey(key: Component): EntityPair? = entityMap[key]
    fun getArmorStand(armorStand: ArmorStand): String = convertComponentToString(armorStand.customName())
}