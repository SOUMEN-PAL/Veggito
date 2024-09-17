package com.example.sih2024.presentation.customerHome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

//TODO:Fix the search Bar
@Composable
fun SearchSection() {
    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        MySearchBar(
            query = text,
            onQueryChange = {text = it},
            onSearch = {//callback function
                //search operation to perform
                active = false
            },
            active = active,
            onActiveChange ={active = it},
            modifier = Modifier.heightIn(max = 64.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}


@Preview(showBackground = true, device = Devices.PIXEL_7)
@Composable
fun LocationSearchSectionPreview(){
    SearchSection()
}