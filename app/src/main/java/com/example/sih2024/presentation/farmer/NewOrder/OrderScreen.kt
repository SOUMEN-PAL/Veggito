package com.example.sih2024.presentation.farmer.NewOrder

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.sih2024.presentation.farmer.BottomSection
import com.example.sih2024.presentation.farmer.TopBarSection

@Composable
fun OrderScreen(){
    Scaffold(
        bottomBar = { BottomSection() }
    ){pv->
        Column(
            Modifier
                .fillMaxSize()
                .padding(pv),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            TopBarSection("New Order")
            AddItemSection(categories = emptyList(), quantity = 1.toString())
        }

    }
}