package com.example.sih2024.presentation.farmer.account

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.sih2024.R

@Composable
fun DetailView(modifier: Modifier, string1: String, string2: String) {

        Text(
            text = string1,
            fontSize = 12.sp,
            fontStyle = FontStyle.Italic,
            color = colorResource(
                id = R.color.grey
            )
        )
        Text(
            text = string2,
            fontSize = 24.sp,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.SemiBold,
            color = colorResource(
                id = R.color.maingreen
            )
        )

}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun DetailViewPreview() {
    DetailView(string1 = "Hello", string2 = "World", modifier = Modifier)
}