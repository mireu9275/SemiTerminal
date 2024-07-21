package kr.eme.semiTerminal.enums

enum class Scene(val value: Int) {
    MAIN(0),              //상점 사용 유무 선택 화면
    SHOP_SELECT(1);       //상점 선택 화면

    companion object {
        fun fromInt(value: Int): Scene? = entries.find { it.value == value }
    }
}