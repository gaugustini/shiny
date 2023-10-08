package com.gaugustini.shiny.data.mapper

import com.gaugustini.shiny.data.entity.ArmorEntity
import com.gaugustini.shiny.data.entity.DecorationEntity
import com.gaugustini.shiny.data.entity.SkillEntity
import com.gaugustini.shiny.domain.model.Armor
import com.gaugustini.shiny.domain.model.Decoration
import com.gaugustini.shiny.domain.model.Skill

fun ArmorEntity.toArmor(): Armor {
    val skillsMap = mutableMapOf<Int, Int>()
    if (skillOne != null) {
        skillsMap[skillOne] = skillOnePoints!!
    }
    if (skillTwo != null) {
        skillsMap[skillTwo] = skillTwoPoints!!
    }
    if (skillThree != null) {
        skillsMap[skillThree] = skillThreePoints!!
    }
    if (skillFour != null) {
        skillsMap[skillFour] = skillFourPoints!!
    }
    if (skillFive != null) {
        skillsMap[skillFive] = skillFivePoints!!
    }
    return Armor(
        id = id,
        slots = slots,
        skills = skillsMap
    )
}

fun DecorationEntity.toDecoration(): Decoration {
    val skillsMap = mutableMapOf<Int, Int>()
    skillsMap[skillOne] = skillOnePoints
    if (skillTwo != null) {
        skillsMap[skillTwo] = skillTwoPoints!!
    }
    return Decoration(
        id = id,
        slots = slots,
        skills = skillsMap
    )
}

fun SkillEntity.toSkill(): Skill {
    return Skill(
        id = id,
        family = family,
        points = points
    )
}
