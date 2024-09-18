package com.example.sih2024.presentation.farmer.shipment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sih2024.presentation.farmer.FarmerTransactionModel

@Composable
fun ShipmentSection(modifier: Modifier = Modifier , shipmentList : MutableList<FarmerTransactionModel?>) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {

        Box(modifier = Modifier.fillMaxWidth(),
        ) {
            Column(modifier = Modifier
                .padding(horizontal = 16.dp)
            ) {
                Text(text = "Shipments", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(16.dp))
                LazyColumn {
                    items(shipmentList){singleShipment->
                        if(singleShipment!=null){
                            Shipment(singleShipment)
                        }
                    }
                }

            }

        }
    }
}