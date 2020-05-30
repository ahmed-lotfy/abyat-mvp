package com.abyat.model

class MVPCandidate {
    var nickname: String? = null
    var ratingPoints: Int = 0

    override fun equals(other: Any?): Boolean {
        if (nickname != (other as MVPCandidate).nickname) return false
        return true
    }
}
