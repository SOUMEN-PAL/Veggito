package com.example.sih2024.presentation.farmer.Transaction

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
import com.example.sih2024.presentation.farmer.TransactionSection

@Composable
fun TransactionScreen(){
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
            TopBarSection("Transactions")
            TransactionSection("History")
        }

    }
}