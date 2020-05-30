package com.abyat.model

class Player {
    var name: String? = null
    var nickname: String? = null
    var number: Int? = null
    var team: String? = null
    var position: Any? = null

    override fun equals(other: Any?): Boolean {
        if (nickname != (other as Player).nickname) return false
        return true
    }

    override fun toString(): String {
        return "Player(name=$name, nickname=$nickname, number=$number, team=$team, position=$position)"
    }
}
