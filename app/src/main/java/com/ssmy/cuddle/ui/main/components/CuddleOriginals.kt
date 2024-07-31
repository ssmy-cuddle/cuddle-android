package com.ssmy.cuddle.ui.main.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ssmy.cuddle.R

/**
 * doc 주석
 * @author wookjin
 * @since 7/17/24
 **/
@Composable
fun CuddleOriginals() {
//    val items = listOf(
//        CuddleItem("Title 1", "Description 1", R.drawable.sample_image),
//        CuddleItem("Title 2", "Description 2", R.drawable.sample_image)
//    )
//
//    LazyRow(
//        contentPadding = PaddingValues(horizontal = 18.dp),
//        horizontalArrangement = Arrangement.spacedBy(12.dp)
//    ) {
//        items(items) { item ->
//            CuddleItemCard(item)
//        }
//    }
}

@Composable
fun CuddleItemCard(item: CuddleItem) {
//    Column(modifier = Modifier.width(226.dp)) {
//        Image(
//            painter = painterResource(id = item.imageRes),
//            contentDescription = null,
//            modifier = Modifier
//                .size(width = 226.dp, height = 156.dp)
//                .background(Color(0xFFB5DBFF), RoundedCornerShape(8.dp))
//        )
//        Spacer(modifier = Modifier.height(6.dp))
//        Text(item.title, fontSize = 14.sp, color = Color.Black)
//        Text(item.description, fontSize = 10.sp, color = Color.Black)
//    }
}

data class CuddleItem(val title: String, val description: String, val imageRes: Int)
