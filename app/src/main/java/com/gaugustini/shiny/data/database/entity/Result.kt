package com.gaugustini.shiny.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "result",
    primaryKeys = ["id"],
    foreignKeys = [
        ForeignKey(
            entity = ArmorEntity::class,
            parentColumns = ["id"],
            childColumns = ["head_id"]
        ),
        ForeignKey(
            entity = ArmorEntity::class,
            parentColumns = ["id"],
            childColumns = ["body_id"]
        ),
        ForeignKey(
            entity = ArmorEntity::class,
            parentColumns = ["id"],
            childColumns = ["arms_id"]
        ),
        ForeignKey(
            entity = ArmorEntity::class,
            parentColumns = ["id"],
            childColumns = ["waist_id"]
        ),
        ForeignKey(
            entity = ArmorEntity::class,
            parentColumns = ["id"],
            childColumns = ["legs_id"]
        )
    ],
    indices = [
        Index(value = ["head_id"]),
        Index(value = ["body_id"]),
        Index(value = ["arms_id"]),
        Index(value = ["waist_id"]),
        Index(value = ["legs_id"])
    ]
)
data class ResultEntity(
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "head_id") val headId: Int,
    @ColumnInfo(name = "body_id") val bodyId: Int,
    @ColumnInfo(name = "arms_id") val armsId: Int,
    @ColumnInfo(name = "waist_id") val waistId: Int,
    @ColumnInfo(name = "legs_id") val legsId: Int,
)

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
    @ColumnInfo(name = "result_id") val resultId: Int,
    @ColumnInfo(name = "decoration_id") val decorationId: Int,
    @ColumnInfo(name = "quantity") val quantity: Int,
)
