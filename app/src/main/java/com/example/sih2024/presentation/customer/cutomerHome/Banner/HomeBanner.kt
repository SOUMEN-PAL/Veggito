package com.example.sih2024.presentation.customerHome.Banner

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch


@Composable
fun HomeBanner(
    banners: List<Banner>
){
    val pagerState = rememberPagerState(initialPage = 0) {
        banners.size
    }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxWidth()
    ){
        Spacer(modifier = Modifier.height(16.dp))
        HorizontalPager(state = pagerState, modifier = Modifier.fillMaxWidth()) { index ->
            Homebanner(banner = banners[index])

        }
        Spacer(modifier = Modifier.height(16.dp))
    }

    LaunchedEffect(key1 = pagerState.currentPage) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            scope.launch {
                kotlinx.coroutines.delay(4000)
                pagerState.animateScrollToPage(
                    page = pagerState.currentPage + 1
                )
            }

        }

    }


}


@Preview(showBackground = true)
@Composable
fun HomeBannercategoryPreview(){
    HomeBanner(banners = banners)
}