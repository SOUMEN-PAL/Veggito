package com.example.sih2024.presentation.farmer.shipment

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sih2024.R
import com.example.sih2024.presentation.farmer.FarmerTransactionModel

@Composable
fun Shipment(
    shipmentData: FarmerTransactionModel
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(onClick = { /*TODO*/ },
            modifier = Modifier
                .height(100.dp)
                .padding(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = colorResource(id = R.color.transactioncard),
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.transaction),
                        contentDescription = null,
                        modifier = Modifier.size(40.dp)
                    )
                    Column {
                        Text(text = shipmentData.name, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                        Text(text = shipmentData.date, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                    }
                }
                Box(
                    modifier = Modifier
                        .wrapContentSize(Alignment.CenterEnd)
                        .background(
                            color = if (shipmentData.status == "Done") colorResource(id = R.color.maingreen)
                            else if (shipmentData.status == "On The Way") colorResource(id = R.color.ontheway) else colorResource(
                                id = R.color.pending
                            ),
                            shape = RoundedCornerShape(20.dp)
                        )
                        .padding(horizontal = 20.dp, vertical = 5.dp)

                ) {
                    Column(
                    ) {
                        //TODO: items ka price ka sum
                        Text(text = shipmentData.status)

                    }


                }

            }
        }
    }

}