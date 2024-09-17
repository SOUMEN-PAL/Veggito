package com.example.sih2024.presentation.customerHome


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sih2024.R

//TODO:Fix the search Bar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MySearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    active: Boolean,
    onActiveChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    SearchBar(
        query = query,
        onQueryChange = onQueryChange,
        onSearch = onSearch,
        active = active,
        onActiveChange = onActiveChange,
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = SearchBarDefaults.colors(
            containerColor = colorResource(id = R.color.SearchBar),
            dividerColor = colorResource(id = R.color.SearchBar),
            inputFieldColors= TextFieldDefaults.colors(
                focusedTextColor = colorResource(id = R.color.SearchBarText),
                unfocusedTextColor = colorResource(id = R.color.SearchBarText),
            )
        ),
        leadingIcon = {
            Image(painter = painterResource(id = R.drawable.search),
                contentDescription = null,
                modifier= Modifier.size(18.dp)
            )
        },
        placeholder = {
            Text(text = "Search Store")
        }

    ){
        // content of the searchbar when active
    }
}

@Preview(showBackground = true)
@Composable
fun MySearchBarPreview() {
    MySearchBar(
        query = "",
        onQueryChange = {},
        onSearch = {},
        active = false,
        onActiveChange = {},
        modifier = Modifier
    )
}