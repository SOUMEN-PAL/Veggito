package com.example.sih2024.presentation.customerHome.Banner

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun Homebanner(
    banner: Banner,
){
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {


        Image(painter = painterResource(id = banner.image),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 42.dp, end = 64.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = banner.title, fontSize = 20.sp)
            Text(text = banner.description, fontSize = 14.sp)
        }

    }
}

@Preview(showBackground = true)
@Composable
fun HomebannerPreview(){
    Homebanner(
        banner = banners[0]
    )
}