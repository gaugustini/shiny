package com.gaugustini.shiny.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "decoration_material",
    primaryKeys = ["decoration_id", "material_id", "recipe"],
    foreignKeys = [
        ForeignKey(
            entity = DecorationEntity::class,
            parentColumns = ["id"],
            childColumns = ["decoration_id"],
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
data class DecorationMaterialEntity(
    @ColumnInfo(name = "decoration_id") val decorationId: Int,
    @ColumnInfo(name = "material_id") val materialId: Int,
    @ColumnInfo(name = "recipe") val recipe: Int,
    @ColumnInfo(name = "quantity") val quantity: Int,
)