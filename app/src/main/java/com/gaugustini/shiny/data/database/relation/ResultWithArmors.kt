package com.gaugustini.shiny.data.database.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.gaugustini.shiny.data.database.entity.ArmorEntity
import com.gaugustini.shiny.data.database.entity.ResultEntity

data class ResultWithArmors(
    @Embedded val result: ResultEntity,
    @Relation(
        parentColumn = "head_id",
        entityColumn = "id"
    )
    val head: ArmorEntity,
    @Relation(
        parentColumn = "body_id",
        entityColumn = "id"
    )
    val body: ArmorEntity,
    @Relation(
        parentColumn = "arms_id",
        entityColumn = "id"
    )
    val arms: ArmorEntity,
    @Relation(
        parentColumn = "waist_id",
        entityColumn = "id"
    )
    val waist: ArmorEntity,
    @Relation(
        parentColumn = "legs_id",
        entityColumn = "id"
    )
    val legs: ArmorEntity,
)