package com.gaugustini.shiny.util

enum class Game(val value: Int) {
    MHF1(0),
    MHFU(1),
    MHP3(2),
    MH3U(3),
    MH4(4),
    MH4U(5),
    MHGEN(6),
    MHGU(7);

    companion object {
        fun fromIntToGame(value: Int): Game {
            return when (value) {
                0 -> MHF1
                1 -> MHFU
                2 -> MHP3
                3 -> MH3U
                4 -> MH4
                5 -> MH4U
                6 -> MHGEN
                7 -> MHGU
                else -> MHF1
            }
        }
    }
}

enum class HunterType(val value: Int) {
    BLADEMASTER(1),
    GUNNER(2);

    companion object {
        fun fromIntToHunterType(value: Int): HunterType {
            return when (value) {
                1 -> BLADEMASTER
                2 -> GUNNER
                else -> BLADEMASTER
            }
        }
    }
}

enum class Gender(val value: Int) {
    MALE(1),
    FEMALE(2);

    companion object {
        fun fromIntToGender(value: Int): Gender {
            return when (value) {
                1 -> MALE
                2 -> FEMALE
                else -> MALE
            }
        }
    }
}

enum class ArmorCategory(val value: Int) {
    HEAD(0),
    BODY(1),
    ARMS(2),
    WAIST(3),
    LEGS(4);

    companion object {
        fun fromIntToArmorCategory(value: Int): ArmorCategory {
            return when (value) {
                0 -> HEAD
                1 -> BODY
                2 -> ARMS
                3 -> WAIST
                4 -> LEGS
                else -> HEAD
            }
        }
    }
}