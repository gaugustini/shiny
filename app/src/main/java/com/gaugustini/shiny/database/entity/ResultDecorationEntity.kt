package com.gaugustini.shiny.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "result_decoration",
    primaryKeys = ["result_id", "decoration_id"],
    foreignKeys = [
        ForeignKey(
            entity = ResultEntity::class,
            parentColumns = ["id"],
            childColumns = ["result_id"]
        ),
        ForeignKey(
            entity = DecorationEntity::class,
            parentColumns = ["id"],
            childColumns = ["decoration_id"]
        )
    ],
    indices = [
        Index(value = ["decoration_id"])
    ]
)
data class ResultDecorationEntity(
    @ColumnInfo(name = "result_id") val resultId: Long,
    @ColumnInfo(name = "decoration_id") val decorationId: Int,
    @ColumnInfo(name = "quantity") val quantity: Int,
)