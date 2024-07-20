package kr.eme.semiTerminal.listeners

import kr.eme.semiTerminal.managers.PacketManager.mutePlayer
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.player.PlayerInteractAtEntityEvent

object TerminalListener : Listener {
    @EventHandler
    fun onPlayerInteractAtEntity(event: PlayerInteractAtEntityEvent){
        val entity = event.rightClicked
        val player = event.player
        if (entity !is ArmorStand) return
        // 플레이어가 웅크린 후 우클릭을 한다면 확정함.
        if (player.isSneaking) player.sendMessage("Shift + Right Click")
        // 플레이어가 웅크리지 않고 우클릭을 한다면 메뉴를 오른쪽으로 옮김.
        else player.sendMessage("Right Click")
    }
    @EventHandler
    fun onPlayerDamageEntity(event: EntityDamageByEntityEvent){
        val entity = event.entity
        val damager = event.damager
        if (entity is ArmorStand && damager is Player) {
            event.isCancelled = true
            mutePlayer(damager)
            damager.sendMessage("Left Click")
        }
    }
}