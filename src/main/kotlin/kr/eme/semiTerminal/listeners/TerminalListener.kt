package kr.eme.semiTerminal.listeners

import kr.eme.semiTerminal.enums.Scene
import kr.eme.semiTerminal.managers.EntityPairManager.getEntityPair
import kr.eme.semiTerminal.managers.EntityPairManager.removeEntityPair
import kr.eme.semiTerminal.managers.EntityPairManager.updateTextDisplay
import kr.eme.semiTerminal.managers.PacketManager.mutePlayer
import kr.eme.semiTerminal.objects.EntityPair
import net.kyori.adventure.text.Component
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

        val key = entity.customName() ?: return
        val entityPair = getEntityPair(key) ?: return
        // 플레이어가 웅크린 후 우클릭을 한다면 확정함.
        if (player.isSneaking) {
            player.sendMessage("Shift + Right Click")

            val selectedOption = entityPair.selectedOption
            val sceneIndex = Scene.fromInt(entityPair.sceneIndex)
            when (sceneIndex) {
                Scene.MAIN -> {
                    when (selectedOption) {
                        0 -> { // YES 클릭
                            nextDisplay(entityPair, 1)
                            entityPair.textDisplay.text(Component.text("선택해주세요.\n§a[작물]§f[기타]"))
                        }
                        1 -> { // NO 클릭
                            removeEntityPair(key)
                        }
                    }
                }
                Scene.SHOP_SELECT -> {
                    when (selectedOption) {
                        0 -> { // 작물 클릭
                            nextDisplay(entityPair, 2)
                            entityPair.textDisplay.text(Component.text("구매할 작물을 선택해주세요.\n§a[봄]§f[여름][가을]"))
                        }
                    }
                }
                else -> return
            }
        }
        // 플레이어가 웅크리지 않고 우클릭을 한다면 메뉴를 오른쪽으로 옮김.
        else {
            player.sendMessage("Right Click")
            entityPair.selectedOption = (entityPair.selectedOption + 1) % 2
            updateTextDisplay(entityPair)
        }
    }
    @EventHandler
    fun onPlayerDamageEntity(event: EntityDamageByEntityEvent){
        val entity = event.entity
        val damager = event.damager
        if (entity is ArmorStand && damager is Player) {
            event.isCancelled = true
            mutePlayer(damager)
            damager.sendMessage("Left Click")

            val key = entity.customName() ?: return
            val entityPair = getEntityPair(key) ?: return

            entityPair.selectedOption = (entityPair.selectedOption + 1) % 2
            updateTextDisplay(entityPair)
        }
    }

    /**
     * Next display
     *
     * 다음 디스플레이로 넘어갈 떄, EntityPair 의 화면 번호를 증가시키고 선택한 메뉴를 0으로 설정함.
     *
     * @param entityPair
     */
    private fun nextDisplay(entityPair: EntityPair, sceneIndex: Int) {
        entityPair.sceneIndex = sceneIndex
        entityPair.selectedOption = 0
    }
    /**
     * Previous display
     *
     * 이전 디스플레이로 넘어갈 때, EntityPair 의 화면 번호를 감소시키고 선택한 메뉴를 0으로 설정함.
     *
     * @param entityPair
     */
    private fun previousDisplay(entityPair: EntityPair, sceneIndex: Int) {
        entityPair.sceneIndex = sceneIndex
        entityPair.selectedOption = 0
    }
}

