package kr.eme.semiTerminal.objects

import com.comphenix.protocol.PacketType
import com.comphenix.protocol.events.ListenerPriority
import com.comphenix.protocol.events.PacketAdapter
import com.comphenix.protocol.events.PacketEvent
import kr.eme.semiTerminal.main
import kr.eme.semiTerminal.managers.PacketManager.shouldMute

object SoundFilterPacketAdapter : PacketAdapter(
    main,
    ListenerPriority.NORMAL,
    PacketType.Play.Server.NAMED_SOUND_EFFECT
) {
    override fun onPacketSending(event: PacketEvent?) {
        val player = event!!.player
        val packet = event.packet
        val soundName = packet.soundEffects.read(0).name
        if (soundName.startsWith("ENTITY_PLAYER_ATTACK_NODAMAGE") && shouldMute(player)){
            event.isCancelled = true
        }
    }
}