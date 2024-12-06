package com.example.weatherupdate

import android.graphics.Color.rgb
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.weatherupdate.api.NetworkResponse

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HomeScreen(viewModel: weatherViewModel) {

    var city by remember {
        mutableStateOf("")
    }

    val weatherResult = viewModel.weatherResult.observeAsState()
    val keyboardContrller = LocalSoftwareKeyboardController.current

    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.Black)
        .padding(top = 18.dp)) {
        Column(modifier = Modifier.padding(8.dp)) {
            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center

            ) {
//                OutlinedTextField(value = city,
//                    onValueChange = {city = it},
//                    label = { Text(text = "Search")}
//                )

                OutlinedTextField(
                    value = city,
                    singleLine = true,
                    shape = RoundedCornerShape(30.dp),
                    onValueChange = { city = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .height(56.dp),
                    placeholder = { Text(text = "Search...", color = Color.White, fontSize = 16.sp) },

                    leadingIcon = {
                        androidx.compose.material.Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Search Icon",
                            tint = Color.White,
                            modifier = Modifier.clickable {
                                viewModel.getCity(city)
                                keyboardContrller?.hide()
                            }
                        )
                    },
                    trailingIcon = {
                        if (city.isNotEmpty()) {
                            androidx.compose.material.IconButton(onClick = {
                                city = ""
                            }) {
                                androidx.compose.material.Icon(
                                    imageVector = Icons.Filled.Clear,
                                    contentDescription = "Clear Text",
                                    tint = Color.Gray
                                )
                            }
                        }
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        focusedContainerColor = Color(android.graphics.Color.rgb(26, 26, 26)),
                        unfocusedContainerColor = Color(android.graphics.Color.rgb(26, 26, 26)),
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        cursorColor = Color.White
                    ),
                )
            }

            when(val result = weatherResult.value) {
                is NetworkResponse.Error -> {
                    Column(modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally, 
                        verticalArrangement = Arrangement.Center
                        ) {
//                        Text(text = result.message)
                        Text(text = "Something went wrong, try again", color = Color.White, fontSize = 22.sp)
                    }
                }
                NetworkResponse.Loading -> {
                    Column(modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                        ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(50.dp),
                            color = Color.White
                        )
                        Text(text = "Loading.....")
                    }
                }
                is NetworkResponse.Success -> {
//                        SuccessUI()
                    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Bottom) {
                        Icon(imageVector = Icons.Default.LocationOn,
                            contentDescription = "location icon",
                            tint = Color.White,
                            modifier = Modifier.size(30.dp)
                        )

                        Text(text = "${result.data.location.name}. ", color = Color.White, fontSize = 25.sp, fontFamily = FontFamily.Serif)
//                        Text(text = result.data.location.country, color = Color.Gray, fontSize = 25.sp)
                    }
                    Text(text = result.data.location.country, color = Color.Gray, fontSize = 20.sp, modifier = Modifier.padding(start = 32.dp))

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Spacer(modifier = Modifier.padding(top = 25.dp))
//                        Text(text = "Image Here", color = Color.White)
                        AsyncImage(
                            model = "https:${result.data.current.condition.icon}".replace("64x64", "128x128"),
                            contentDescription = "condition icon",
                            modifier = Modifier.size(200.dp)
                        )
                        Text(
                            text = result.data.current.condition.text,
                            color = Color.White,
                            fontSize = 25.sp,
                            fontFamily = FontFamily.Serif
                        )
                        Text(text = "${result.data.current.temp_c} °c",
                            color = Color.White,
                            fontSize = 55.sp,
                            fontStyle = FontStyle.Italic
                        )
                    }

                    Column(modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 12.dp, start = 4.dp, end = 4.dp)) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Card(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(4.dp)
                                        .weight(1f),
                                    colors = CardDefaults.cardColors(
                                        containerColor = Color(rgb(20, 20, 20))
                                    )
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .padding(8.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center
                                    ) {
                                        Image(
                                            modifier = Modifier.size(80.dp),
                                            painter = painterResource(id = R.drawable.humidity),
                                            contentDescription = "humid"
                                        )
                                        Text(text = "${result.data.current.humidity}%", color = Color.White, fontSize = 20.sp)
                                        Text(text = "Humidity", color = Color.White, fontSize = 30.sp)
                                    }
                                }

                                Card(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(4.dp)
                                        .weight(1f),
                                    colors = CardDefaults.cardColors(
                                            containerColor = Color(rgb(20, 20, 20))
                                    )
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .padding(8.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center
                                    ) {
                                        Image(
                                            modifier = Modifier.size(80.dp),
                                            painter = painterResource(id = R.drawable.wind),
                                            contentDescription = "humid"
                                        )
                                        Text(text = "${result.data.current.wind_kph} Km/h", color = Color.White, fontSize = 20.sp)
                                        Text(text = "Wind", color = Color.White, fontSize = 30.sp)
                                    }
                                }

                            }

//                        Spacer(modifier = Modifier.padding(top = 15.dp))

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Card(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(4.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = Color(rgb(20, 20, 20))
                                )
                            ) {
                                Row( modifier = Modifier
                                    .fillMaxSize()
                                    .padding(8.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                    ) {

                                    Card(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .padding(4.dp)
                                            .weight(1f),
                                        colors = CardDefaults.cardColors(
                                            containerColor = Color(rgb(20, 20, 20))
                                        )
                                    ) {
                                        Column(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .padding(8.dp),
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            verticalArrangement = Arrangement.Center
                                        ) {
                                            Image(
                                                modifier = Modifier.size(50.dp),
                                                painter = painterResource(id = R.drawable.barometer),
                                                contentDescription = "humid"
                                            )
                                            Text(text = "${result.data.current.pressure_mb} mb", color = Color.White, fontSize = 12.sp)
                                            Text(text = "Pressure", color = Color.White, fontSize = 20.sp)
                                        }
                                    }

                                    Card(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .padding(4.dp)
                                            .weight(1f),
                                        colors = CardDefaults.cardColors(
                                            containerColor = Color(rgb(20, 20, 20))
                                        )
                                    ) {
                                        Column(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .padding(8.dp),
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            verticalArrangement = Arrangement.Center
                                        ) {
                                            Image(
                                                modifier = Modifier.size(50.dp),
                                                painter = painterResource(id = R.drawable.gust),
                                                contentDescription = "humid"
                                            )
                                            Text(text = "${result.data.current.gust_kph} Km/h", color = Color.White, fontSize = 12.sp)
                                            Text(text = "Gust", color = Color.White, fontSize = 20.sp)
                                        }
                                    }

                                    Card(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .padding(4.dp)
                                            .weight(1f),
                                        colors = CardDefaults.cardColors(
                                            containerColor = Color(rgb(20, 20, 20))
                                        )
                                    ) {
                                        Column(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .padding(8.dp),
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            verticalArrangement = Arrangement.Center
                                        ) {
                                            Image(
                                                modifier = Modifier.size(50.dp),
                                                painter = painterResource(id = R.drawable.uv),
                                                contentDescription = "humid"
                                            )
                                            Text(text = "${result.data.current.uv}", color = Color.White, fontSize = 12.sp)
                                            Text(text = "UV", color = Color.White, fontSize = 20.sp)
                                        }
                                    }

                                }
                            }
                        }

                    }

                }

                null -> {}
            }

        }
        Spacer(modifier = Modifier.padding(top = 20.dp))
    }
}

@Composable
fun SuccessUI() {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.Black)
        .padding(16.dp)) {
        Row(
            modifier = Modifier.weight(0.5f)
        ) {
            Card(
                modifier = Modifier
                    .weight(0.5f)
                    .clip(shape = RoundedCornerShape(10.dp)),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
            }

            Card(
                modifier = Modifier
                    .weight(0.5f)
                    .clip(shape = RoundedCornerShape(10.dp)),
                colors = CardDefaults.cardColors(
                    containerColor = Color(rgb(26, 26, 26))
                )
            ) {
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun idsfajsf() {
    SuccessUI()
}


















