package com.example.weatherupdate

import android.graphics.Color.rgb
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun composeTest() {

//    Column(modifier = Modifier
//        .fillMaxSize()
//        .background(color = Color.Black),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        OutlinedTextField(
//            value = textState,
//            shape = RoundedCornerShape(30.dp),
//            onValueChange = { textState = it },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp)
//                .height(56.dp),
//            placeholder = { Text(text = "Search...", color = Color.White, fontSize = 16.sp) },
//
//            leadingIcon = {
//                Icon(
//                    imageVector = Icons.Filled.Search,
//                    contentDescription = "Search Icon",
//                    tint = Color.White
//                )
//            },
//            trailingIcon = {
//                if (textState.text.isNotEmpty()) {
//                    IconButton(onClick = { textState = TextFieldValue("") }) {
//                        Icon(
//                            imageVector = Icons.Filled.Clear,
//                            contentDescription = "Clear Text",
//                            tint = Color.Gray
//                        )
//                    }
//                }
//            },
//            colors = OutlinedTextFieldDefaults.colors(
//                focusedBorderColor = Color.Transparent,
//                unfocusedBorderColor = Color.Transparent,
//                focusedContainerColor = Color(rgb(26, 26, 26)),
//                unfocusedContainerColor = Color(rgb(26, 26, 26)),
//                focusedTextColor = Color.White,
//                unfocusedTextColor = Color.White,
//                cursorColor = Color.White
//            ),
////            shape = MaterialTheme.shapes.large // Rounded corners
//        )
//    }
    }


@Preview
@Composable
fun sdofjaf() {
    composeTest()
}