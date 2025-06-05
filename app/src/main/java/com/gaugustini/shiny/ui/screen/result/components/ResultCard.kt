package com.gaugustini.shiny.ui.screen.result.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gaugustini.shiny.R
import com.gaugustini.shiny.domain.model.Result

@Composable
fun ResultCard(result: Result) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        ),
        elevation = CardDefaults.elevatedCardElevation(),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp)
        ) {
            ResultItem(icon = R.drawable.icon_armor_head, text = result.head)
            ResultItem(icon = R.drawable.icon_armor_body, text = result.body)
            ResultItem(icon = R.drawable.icon_armor_arms, text = result.arms)
            ResultItem(icon = R.drawable.icon_armor_waist, text = result.waist)
            ResultItem(icon = R.drawable.icon_armor_legs, text = result.legs)
            Spacer(modifier = Modifier.height(16.dp))
            result.decorations.forEach { (decoration, amount) ->
                ResultItem(icon = R.drawable.icon_decoration, text = "$decoration x $amount")
            }
        }
    }
}

@Composable
fun ResultItem(icon: Int, text: String) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(icon),
            contentDescription = null,
            modifier = Modifier.size(28.dp)
        )
        Spacer(modifier = Modifier.width(18.dp))
        Text(
            text = text,
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )
    }
}