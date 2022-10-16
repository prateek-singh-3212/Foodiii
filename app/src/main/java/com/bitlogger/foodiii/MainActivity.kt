package com.bitlogger.foodiii

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bitlogger.foodiii.ui.data.plateTypes
import com.bitlogger.foodiii.ui.data.resturantsDetails
import com.bitlogger.foodiii.ui.theme.FoodiiiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeScreenUI { onClick() }
        }
    }

    private fun onClick() {
        val intent = Intent(this, DetailActivity::class.java)
        startActivity(intent)
    }
}

@Composable
fun HomeScreenUI(orderClick: () -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        item {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(text = "Order Again!!!", fontSize = 22.sp, fontWeight = FontWeight(900))
                Spacer(modifier = Modifier.height(10.dp))
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    items(5) {
                        OrderAgain(orderClick)
                    }
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Eat what makes you happy",
                fontSize = 22.sp,
                fontWeight = FontWeight(900)
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
        items(plateTypes.windowed(4, 4, partialWindows = false)) { items ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Spacer(modifier = Modifier.height(5.dp))
                FoodPlate(items[0].first, items[0].second, orderClick)
                Spacer(modifier = Modifier.height(5.dp))
                FoodPlate(items[1].first, items[1].second, orderClick)
                Spacer(modifier = Modifier.height(5.dp))
                FoodPlate(items[2].first, items[2].second, orderClick)
                Spacer(modifier = Modifier.height(5.dp))
                FoodPlate(items[3].first, items[3].second, orderClick)
            }
        }
        item {
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Nearby Restaurants",
                fontSize = 22.sp,
                fontWeight = FontWeight(900)
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
        items(resturantsDetails) { items ->
            NearbyRestaurants(items, orderClick)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NearbyRestaurants(triple: Triple<String, Double, Int>, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .padding(10.dp)
            .clip(RoundedCornerShape(10.dp))
    ) {
        Column {
            Image(
                painter = painterResource(id = triple.third),
                contentDescription = "Rest",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(5.dp))
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            ) {
                Text(
                    text = triple.first,
                    fontSize = 20.sp,
                    fontWeight = FontWeight(500),
                    textAlign = TextAlign.Start
                )
                Spacer(Modifier.weight(1f))
                Card(
                    modifier = Modifier
                        .padding(5.dp)
                        .wrapContentSize(Alignment.Center)
                        .clip(RoundedCornerShape(CornerSize(5.dp)))
                ) {
                    Text(
                        text = "${triple.second}⭐",
                        fontSize = 15.sp,
                        modifier = Modifier.background(Color(0xD57FDA54)),
                        fontWeight = FontWeight(500),
                        color = Color.White
                    )
                }
            }
            Text(
                text = "Rolls, Pizza and Italian",
                fontSize = 15.sp,
                modifier = Modifier
                    .padding(5.dp),
                color = Color(0xFF909090),
                fontWeight = FontWeight(500),
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun OrderAgain(onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .wrapContentSize()
    ) {
        Row(modifier = Modifier.height(IntrinsicSize.Max)) {
            Image(
                painter = painterResource(id = R.drawable.food),
                contentDescription = "Food",
                modifier = Modifier
                    .width(70.dp)
                    .height(70.dp)
                    .padding(5.dp)
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .padding(start = 5.dp)
                    .fillMaxHeight()
            ) {
                Text(text = "Burger Farm", fontSize = 18.sp, fontWeight = FontWeight(900))
                Row {
                    Image(
                        painter = painterResource(id = R.drawable.ic_baseline_timer_24),
                        contentDescription = "timer",
                        modifier = Modifier
                            .width(20.dp)
                            .height(20.dp),
                        contentScale = ContentScale.Crop
                    )
                    Text(text = "27 min", fontSize = 12.sp, fontWeight = FontWeight(300))
                }
                Text(
                    text = "20% off",
                    fontSize = 14.sp,
                    fontWeight = FontWeight(600),
                    color = Color.Blue
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FoodPlate(image: Int, name: String, onClick: () -> Unit) {
    Card(
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .width(100.dp)
                .wrapContentHeight()
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = "Food",
                modifier = Modifier
                    .height(85.dp)
                    .width(85.dp)
                    .clip(RoundedCornerShape(50.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = name,
                fontSize = 16.sp,
                fontWeight = FontWeight(500),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FoodiiiTheme {
//        HomeScreenUI()
    }
}