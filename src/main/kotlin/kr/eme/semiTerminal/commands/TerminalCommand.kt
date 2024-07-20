package kr.eme.semiTerminal.commands

import kr.eme.semiTerminal.managers.EntityPairManager.registerEntityPair
import net.kyori.adventure.text.Component
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.*

object TerminalCommand : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) {
            sender.sendMessage("콘솔에서는 이 명령어를 사용할 수 없습니다.")
            return true
        }
        if (args.isEmpty()) {
            usage(sender)
            return true
        }
        val key : Component = Component.text(args[0])
        createArmorStandAndTextDisplay(sender, key)
        return true
    }
    fun usage(player: Player) {
        player.sendMessage("usage: /terminal [key]")
    }

    private fun createArmorStandAndTextDisplay(player: Player, key: Component) {
        val location = player.location
        val world = player.world
        val armorStand = world.spawnEntity(location, EntityType.ARMOR_STAND) as ArmorStand
        armorStand.apply {
            isVisible = false
            isCustomNameVisible = false
            customName((key)) //이름
            setGravity(false) //중력
            isInvulnerable = true //무적
            isMarker = false
        }

        val textDisplay = world.spawn(location.add(0.0, 1.0, 0.0), TextDisplay::class.java)
        textDisplay.apply {
            isCustomNameVisible = false // 이름 비활성화
            customName(key)
            text(Component.text("상점을 이용하시겠습니까?\n[네][아니요]"))
            billboard = (Display.Billboard.CENTER) //모든 방향에서 보임
        }
        registerEntityPair(key, armorStand, textDisplay)
    }
}