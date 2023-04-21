package com.mooooong.fastwash.navigation

sealed class NavGroup(val group: String) {

    object Washing : NavGroup("washing") {
        const val RESERVE = "reserve"
        const val ASSIGN = "assign"
    }
}
