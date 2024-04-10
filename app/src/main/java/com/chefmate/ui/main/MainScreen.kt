package com.chefmate.ui.main

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.chefmate.R
import com.chefmate.model.ProductModel
import com.chefmate.routing.Screen
import com.chefmate.ui.theme.ChefmateAppTheme
import com.chefmate.ui.theme.blue
import com.chefmate.ui.theme.white
import com.chefmate.utils.CustomSearchView
import com.chefmate.utils.OutlineFormField

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavController) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    var search by remember { mutableStateOf("") }
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
                            text = "Home", color = Color.White,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
                        )
                    },
                    actions = {
                        IconButton(
                            onClick = {
                            }
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_contact_us),
                                contentDescription = "Image",
                                contentScale = ContentScale.Fit,
                                colorFilter = ColorFilter.tint(Color.White),
                                modifier = Modifier
                                    .width(35.dp)
                                    .height(35.dp)
                                    .clickable {
                                        navController.navigate(Screen.ContactUsScreen.route)
                                    }
                            )
                        }
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = blue,
                        titleContentColor = Color.White
                    )
                )
                Spacer(Modifier.height(10.dp))
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    CustomSearchView(search = search, onValueChange = {
                        search = it
                    }, onClick = {
                        navController.navigate(Screen.SearchScreen.route)
                    })
                }
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