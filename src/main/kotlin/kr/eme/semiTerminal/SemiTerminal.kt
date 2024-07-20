package kr.eme.semiTerminal

import kr.eme.semiTerminal.commands.TerminalCommand
import kr.eme.semiTerminal.listeners.TerminalListener
import kr.eme.semiTerminal.managers.PacketManager
import org.bukkit.plugin.java.JavaPlugin


internal lateinit var main : JavaPlugin

class SemiTerminal : JavaPlugin() {
    override fun onEnable() {
        main = this
        PacketManager.init()
        server.pluginManager.registerEvents(TerminalListener,this)
        getCommand("terminal")?.setExecutor(TerminalCommand)
    }
    override fun onDisable() {
        super.onDisable()
    }
}