package com.example.zadumite_frontend.ui.add_word

fun Char.isCyrillic(): Boolean {
    return this in 'А'..'Я' || this in 'а'..'я'
}

fun Char.isAllowedSymbol(): Boolean {
    return this.isWhitespace() || this in setOf('.', ',', '!', '?', '-', '—', ':', ';', '(', ')', '"')
}
