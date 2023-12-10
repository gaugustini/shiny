package com.gaugustini.shiny.data.mapper

import com.gaugustini.shiny.data.entity.ArmorEntity
import com.gaugustini.shiny.data.entity.DecorationEntity
import com.gaugustini.shiny.data.entity.ResultEntity
import com.gaugustini.shiny.data.entity.ResultWithLanguage
import com.gaugustini.shiny.data.entity.SkillWithLanguage
import com.gaugustini.shiny.domain.model.Armor
import com.gaugustini.shiny.domain.model.ArmorSet
import com.gaugustini.shiny.domain.model.Decoration
import com.gaugustini.shiny.domain.model.Result
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
        nameID = name,
        category = category,
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
        nameID = name,
        slots = slots,
        skills = skillsMap
    )
}

fun SkillWithLanguage.toSkill(): Skill {
    return Skill(
        id = skillID,
        name = name,
        familyID = family,
        points = points
    )
}

fun ArmorSet.toResultEntity(): ResultEntity {
    // Map decorations to a Pair(Decoration Name ID, Amount)
    val decorationsPair =
        decorations.groupingBy { it }.eachCount().mapKeys { it.key.nameID }
            .map { it.key to it.value }

    return ResultEntity(
        head = head.nameID,
        body = body.nameID,
        arms = arms.nameID,
        waist = waist.nameID,
        legs = legs.nameID,
        decorationOne = decorationsPair.elementAtOrNull(0)?.first,
        decorationOneAmount = decorationsPair.elementAtOrNull(0)?.second,
        decorationTwo = decorationsPair.elementAtOrNull(1)?.first,
        decorationTwoAmount = decorationsPair.elementAtOrNull(1)?.second,
        decorationThree = decorationsPair.elementAtOrNull(2)?.first,
        decorationThreeAmount = decorationsPair.elementAtOrNull(2)?.second,
        decorationFour = decorationsPair.elementAtOrNull(3)?.first,
        decorationFourAmount = decorationsPair.elementAtOrNull(3)?.second,
        decorationFive = decorationsPair.elementAtOrNull(4)?.first,
        decorationFiveAmount = decorationsPair.elementAtOrNull(4)?.second,
        decorationSix = decorationsPair.elementAtOrNull(5)?.first,
        decorationSixAmount = decorationsPair.elementAtOrNull(5)?.second,
        decorationSeven = decorationsPair.elementAtOrNull(6)?.first,
        decorationSevenAmount = decorationsPair.elementAtOrNull(6)?.second,
        decorationEight = decorationsPair.elementAtOrNull(7)?.first,
        decorationEightAmount = decorationsPair.elementAtOrNull(7)?.second,
        decorationNine = decorationsPair.elementAtOrNull(8)?.first,
        decorationNineAmount = decorationsPair.elementAtOrNull(8)?.second,
        decorationTen = decorationsPair.elementAtOrNull(9)?.first,
        decorationTenAmount = decorationsPair.elementAtOrNull(9)?.second
    )
}

fun ResultWithLanguage.toResult(): Result {
    val decorationsMap = buildMap {
        decorationOne?.let { name ->
            decorationOneAmount?.let { amount ->
                put(name, amount)
            }
        }
        decorationTwo?.let { name ->
            decorationTwoAmount?.let { amount ->
                put(name, amount)
            }
        }
        decorationThree?.let { name ->
            decorationThreeAmount?.let { amount ->
                put(name, amount)
            }
        }
        decorationFour?.let { name ->
            decorationFourAmount?.let { amount ->
                put(name, amount)
            }
        }
        decorationFive?.let { name ->
            decorationFiveAmount?.let { amount ->
                put(name, amount)
            }
        }
        decorationSix?.let { name ->
            decorationSixAmount?.let { amount ->
                put(name, amount)
            }
        }
        decorationSeven?.let { name ->
            decorationSevenAmount?.let { amount ->
                put(name, amount)
            }
        }
        decorationEight?.let { name ->
            decorationEightAmount?.let { amount ->
                put(name, amount)
            }
        }
        decorationNine?.let { name ->
            decorationNineAmount?.let { amount ->
                put(name, amount)
            }
        }
        decorationTen?.let { name ->
            decorationTenAmount?.let { amount ->
                put(name, amount)
            }
        }
    }
    return Result(
        head = head,
        body = body,
        arms = arms,
        waist = waist,
        legs = legs,
        decorations = decorationsMap
    )

}