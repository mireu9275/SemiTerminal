package kr.eme.semiTerminal.objects

import org.bukkit.entity.ArmorStand
import org.bukkit.entity.TextDisplay

data class EntityPair(
    val armorStand: ArmorStand,
    val textDisplay: TextDisplay,
    var selectedOption : Int = 0, // 0: 네, 1: 아니요
    var sceneIndex: Int = 0
)

