package com.example.sih2024.presentation.farmer.NewOrder

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.sih2024.R
import com.example.sih2024.presentation.customer.customerExplore.CategoryData

@Composable
fun AddItemSection(
    categories: List<CategoryData>,
    quantity: String,
) {
    var expanded = remember { mutableStateOf(false) }
    var selectedCategory by remember { mutableStateOf("") }
    var showDropdown by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .height(110.dp)
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = colorResource(id = R.color.additemCard)
                ),
                shape = RoundedCornerShape(16.dp),

                ) {

                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .background(color = Color.Transparent)
                ) {
                    Text(text = "CATEGORY")
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { showDropdown = true }
                            .border(2.dp, color = Color.Black, shape = CircleShape),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = selectedCategory,
                            modifier = Modifier.weight(1f),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Icon(Icons.Filled.ArrowDropDown, "contentDescription")
                    }

                    if (showDropdown) {
                        DropdownMenu(
                            expanded = !expanded.value,
                            onDismissRequest = {
                                showDropdown = false
                                expanded.value = false
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {

                        }

                        // ... rest of your composables (Buttons)
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .height(110.dp)
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = colorResource(id = R.color.additemCard)
                ),
                shape = RoundedCornerShape(16.dp),

                ) {

                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .background(color = Color.Transparent)
                ) {
                    Text(text = "NAME")
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { showDropdown = true }
                            .border(2.dp, color = Color.Black, shape = CircleShape),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = selectedCategory,
                            modifier = Modifier.weight(1f),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Icon(Icons.Filled.ArrowDropDown, "contentDescription")
                    }

                    if (showDropdown) {
                        DropdownMenu(
                            expanded = !expanded.value,
                            onDismissRequest = {
                                showDropdown = false
                                expanded.value = false
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {

                        }

                        // ... rest of your composables (Buttons)
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .height(110.dp)
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = colorResource(id = R.color.additemCard)
                ),
                shape = RoundedCornerShape(16.dp),

                ) {

                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .background(color = Color.Transparent)
                ) {
                    Text(text = "QUANTITY")
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { showDropdown = true }
                            .border(2.dp, color = Color.Black, shape = CircleShape),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = selectedCategory,
                            modifier = Modifier.weight(1f),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Icon(Icons.Filled.ArrowDropDown, "contentDescription")
                    }

                    if (showDropdown) {
                        DropdownMenu(
                            expanded = !expanded.value,
                            onDismissRequest = {
                                showDropdown = false
                                expanded.value = false
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {

                        }

                        // ... rest of your composables (Buttons)
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.farnerplus),
                    contentColor = Color.Black
                ),
                modifier = Modifier.width(120.dp)
            ) {
                Text(text = "Add")
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier.padding(bottom = 40.dp)
        ) {
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.farnerplus),
                    contentColor = Color.Black
                ),
                modifier = Modifier.width(200.dp)
            ) {
                Text(text = "upload", textAlign = TextAlign.End)

            }

        }
    }



}