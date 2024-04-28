package com.chefmate.ui.searchListing

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.chefmate.R
import com.chefmate.application.AppConfiguration.Companion.list
import com.chefmate.ui.model.ProductModel
import com.chefmate.routing.Screen
import com.chefmate.ui.theme.ChefmateAppTheme
import com.chefmate.ui.theme.blue
import com.chefmate.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchScreen(navController: NavController,search:String?,rate:String?,health:String?,recepie:String?) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    val newList = arrayListOf<ProductModel>()
    Log.e("TAG", "SearchScreen: ${list.size}", )
    Log.e("TAG", "search: ${search}", )
    Log.e("TAG", "health: ${health}", )
    if(list.size>0) {
        if(search.toString().isEmpty() && search.toString()==null && search.toString()=="null"&& rate.toString().isEmpty() && rate.toString()==null && rate.toString()=="null"&& health.toString().isEmpty() && health.toString()==null && recepie.toString()=="null"&& recepie.toString().isEmpty() && recepie.toString()==null && recepie.toString()=="null") {
            newList.addAll(list)
        }else{
            list.forEachIndexed { index, productModel ->
                Log.e("TAG", "SearchScreen: $health==${productModel.health}")
                if (search.toString().lowercase()==productModel.name.toString().lowercase() || rate.toString().lowercase()==productModel.rating.toString().lowercase() || health.toString().lowercase()==productModel.health.toString().lowercase() || recepie.toString().lowercase()==productModel.receipe.toString().lowercase()) {
                    newList.add(productModel)
                }

            }

        }

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
                if(newList.size>0) {
                    newList.forEachIndexed { index, productModel ->
                        Card(
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth()
                                .height(200.dp)
                                .clickable {
                                    navController.navigate(Screen.Detail.route + "/${productModel.name}" + "/${productModel.image}" + "/${productModel.price}" + "/${productModel.detail}")
                                },
                            shape = RoundedCornerShape(10.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
                        ) {
                            Image(
                                painter = painterResource(id = productModel.image),
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
                }else{
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .background(color = white), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "No Record Found.", modifier = Modifier
                            .fillMaxSize(), color = Color.Black, textAlign = TextAlign.Center)
                    }
                }


            }
        }


    }

}