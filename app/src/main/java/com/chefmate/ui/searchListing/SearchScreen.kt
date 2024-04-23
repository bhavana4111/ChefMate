package com.chefmate.ui.searchListing

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.chefmate.ui.model.ProductModel
import com.chefmate.routing.Screen
import com.chefmate.ui.theme.ChefmateAppTheme
import com.chefmate.ui.theme.blue

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchScreen(navController: NavController) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    val list = arrayListOf<ProductModel>().apply {
        add(ProductModel(id = "", image = "", name = "Test Product"))
        add(ProductModel(id = "", image = "", name = "Test Product"))
        add(ProductModel(id = "", image = "", name = "Test Product"))
        add(ProductModel(id = "", image = "", name = "Test Product"))
        add(ProductModel(id = "", image = "", name = "Test Product"))
        add(ProductModel(id = "", image = "", name = "Test Product"))
        add(ProductModel(id = "", image = "", name = "Test Product"))
        add(ProductModel(id = "", image = "", name = "Test Product"))
        add(ProductModel(id = "", image = "", name = "Test Product"))
        add(ProductModel(id = "", image = "", name = "Test Product"))
        add(ProductModel(id = "", image = "", name = "Test Product"))
    }
    ChefmateAppTheme {
        Scaffold {
            Column(
                modifier = Modifier
                    .background(color = blue)
                    .padding(top = 40.dp)
                    .verticalScroll(scrollState)
            ) {
                SmallTopAppBar(
                    title = {
                        Text(
                            text = "Search Product", color = Color.White,
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
                Spacer(Modifier.height(10.dp))
                list.forEachIndexed { index, productModel ->
                    Card(
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()
                            .height(200.dp)
                            .clickable {
                                navController.navigate(Screen.Detail.route)
                            },
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
                                .height(150.dp)
                        )
                        Text(
                            productModel.name ?: "",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            style = TextStyle(color = Color.Black)
                        )
                        Spacer(modifier = Modifier.height(10.dp))

                    }
                }

            }
        }


    }

}