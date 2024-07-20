package kr.eme.semiTerminal.managers

import com.comphenix.protocol.ProtocolLibrary
import kr.eme.semiTerminal.main
import kr.eme.semiTerminal.objects.SoundFilterPacketAdapter
import org.bukkit.entity.Player

object PacketManager {
    private val protocolManager = ProtocolLibrary.getProtocolManager()
    private val muteMap = mutableSetOf<Player>()
    fun init() {
        protocolManager.addPacketListener(SoundFilterPacketAdapter)
    }
    fun mutePlayer(player: Player) {
        muteMap.add(player)
        main.server.scheduler.runTaskLater(main, Runnable {
            muteMap.remove(player)
        }, 20L) //1초 후 (20 tick = 1 second)
    }
    fun shouldMute(player: Player): Boolean {
        return muteMap.contains(player)
    }
}