package com.gaugustini.shiny.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "result",
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
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long = 0,
    @ColumnInfo(name = "head_id") val headId: Int,
    @ColumnInfo(name = "body_id") val bodyId: Int,
    @ColumnInfo(name = "arms_id") val armsId: Int,
    @ColumnInfo(name = "waist_id") val waistId: Int,
    @ColumnInfo(name = "legs_id") val legsId: Int,
)