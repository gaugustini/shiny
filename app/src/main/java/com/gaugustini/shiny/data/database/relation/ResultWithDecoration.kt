package com.gaugustini.shiny.data.database.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.gaugustini.shiny.data.database.entity.DecorationEntity
import com.gaugustini.shiny.data.database.entity.ResultDecorationEntity

data class ResultWithDecoration(
    @Embedded val result: ResultDecorationEntity,
    @Relation(
        parentColumn = "decoration_id",
        entityColumn = "id"
    )
    val decoration: DecorationEntity,
)