package kr.eme.semiTerminal.utils

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer

object ComponentUtils {
    private val plainSerializer = PlainTextComponentSerializer.plainText()

    fun convertComponentToString(component: Component?): String {
        return component?.let { plainSerializer.serialize(it) } ?: ""
    }
}