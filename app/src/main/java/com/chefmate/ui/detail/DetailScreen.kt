package com.chefmate.ui.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.chefmate.R
import com.chefmate.ui.theme.ChefmateAppTheme
import com.chefmate.ui.theme.blue
import com.chefmate.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailScreen(navController: NavController) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    ChefmateAppTheme {
        Scaffold {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = blue)
                    .padding(top = 40.dp)
                    .height(IntrinsicSize.Max)
            ) {
                SmallTopAppBar(
                    title = {
                        Text(
                            text = "Detail", color = Color.White,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
                        )
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                navController.navigateUp()
                            }
                        ) {
                            Icon(imageVector = Icons.Rounded.ArrowBack,
                                tint = Color.White,
                                contentDescription = "Back")
                        }
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = blue,
                        titleContentColor = Color.White
                    )
                )

                Column(modifier = Modifier.fillMaxSize().fillMaxHeight().background(color = white)) {
                    Card(
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()
                            .height(180.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_chefmat),
                            contentDescription = "Image",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(180.dp)
                        )
                    }
                    Text(
                        "Test Product",
                        fontSize = 14.sp,
                        color = Color.Black,
                        modifier = Modifier
                            .padding(vertical = 5.dp, horizontal = 10.dp)
                    )
                    Text(
                        "140.00",
                        fontSize = 14.sp,
                        color = Color.Black,
                        modifier = Modifier
                            .padding(vertical = 5.dp, horizontal = 10.dp)
                    )
                    Text(
                        "Detail",
                        fontSize = 14.sp,
                        color = Color.Black,
                        modifier = Modifier
                            .padding(vertical = 5.dp, horizontal = 10.dp)
                    )
                    Card(
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()
                            .height(250.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
                    ) {
                        Text(
                            "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                            fontSize = 14.sp,
                            color = Color.Black,
                            modifier = Modifier
                                .padding(vertical = 5.dp, horizontal = 10.dp)
                        )
                    }

                }
            }
        }


    }

}