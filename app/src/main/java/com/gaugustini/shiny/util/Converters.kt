package com.gaugustini.shiny.util

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter

@ProvidedTypeConverter
class Converters {

    @TypeConverter
    fun fromGameToInt(game: Game): Int {
        return game.value
    }

    @TypeConverter
    fun fromIntToGame(value: Int): Game {
        return Game.fromIntToGame(value)
    }

    @TypeConverter
    fun fromHunterTypeToInt(hunterType: HunterType): Int {
        return hunterType.value
    }

    @TypeConverter
    fun fromIntToHunterType(value: Int): HunterType {
        return HunterType.fromIntToHunterType(value)
    }

    @TypeConverter
    fun fromGenderToInt(gender: Gender): Int {
        return gender.value
    }

    @TypeConverter
    fun fromIntToGender(value: Int): Gender {
        return Gender.fromIntToGender(value)
    }

    @TypeConverter
    fun fromArmorCategoryToInt(armorCategory: ArmorCategory): Int {
        return armorCategory.value
    }

    @TypeConverter
    fun fromIntToArmorCategory(value: Int): ArmorCategory {
        return ArmorCategory.fromIntToArmorCategory(value)
    }

}