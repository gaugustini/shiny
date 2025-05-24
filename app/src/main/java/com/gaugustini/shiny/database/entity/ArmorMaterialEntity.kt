package com.gaugustini.shiny.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "armor_material",
    primaryKeys = ["armor_id", "material_id"],
    foreignKeys = [
        ForeignKey(
            entity = ArmorEntity::class,
            parentColumns = ["id"],
            childColumns = ["armor_id"],
        ),
        ForeignKey(
            entity = MaterialEntity::class,
            parentColumns = ["id"],
            childColumns = ["material_id"],
        )
    ],
    indices = [
        Index(value = ["material_id"])
    ]
)
data class ArmorMaterialEntity(
    @ColumnInfo(name = "armor_id") val armorId: Int,
    @ColumnInfo(name = "material_id") val materialId: Int,
    @ColumnInfo(name = "quantity") val quantity: Int,
)