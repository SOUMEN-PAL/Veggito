package com.example.sih2024.presentation.customer.customerExplore

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CategoryCard(
    category: CategoryData?,
    categoryName : String,
    onCardClicked:() -> Unit

){
    Card(onClick = { onCardClicked()},
        modifier = Modifier
            .padding(8.dp)
            .size(width = 173.dp, height = 200.dp),
        colors = CardDefaults.cardColors(
            containerColor = category!!.color
        ),
        border = BorderStroke(1.dp, color = category.border),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(painter = painterResource(id = category.image),
                contentDescription = null,
                modifier = Modifier
                    .aspectRatio(16f / 9f)
            )
            Spacer(Modifier.height(20.dp))
            Text(text = categoryName, fontSize = 16.sp)

        }
    }
}

