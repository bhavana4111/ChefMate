package com.chefmate.ui.main

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.navigation.NavController
import com.chefmate.R
import com.chefmate.application.AppConfiguration.Companion.filterList
import com.chefmate.application.AppConfiguration.Companion.list
import com.chefmate.application.AppConfiguration.Companion.ratingList
import com.chefmate.drawer.DrawerBody
import com.chefmate.drawer.DrawerHeader
import com.chefmate.drawer.TopBar
import com.chefmate.ui.model.ProductModel
import com.chefmate.routing.Screen
import com.chefmate.ui.chefmate_database.ChefmateDatabase
import com.chefmate.ui.theme.ChefmateAppTheme
import com.chefmate.ui.theme.blue
import com.chefmate.ui.theme.white
import com.chefmate.utils.CustomSearchView
import com.chefmate.utils.RoundedButton
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavController) {
    val context = LocalContext.current
    val preferenceManager = remember {
        ChefmateDatabase(context)
    }
    var filter by rememberSaveable { mutableStateOf(false) }
    var rate by rememberSaveable { mutableStateOf(false) }
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    var isLogout by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()
    var search by remember { mutableStateOf("") }
    var filterDropDown by remember { mutableStateOf("") }
    var ratingDropDown by remember { mutableStateOf("") }
    val icon = if (filter)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    LaunchedEffect(Unit) {
        search = ""
        filterDropDown = ""
        ratingDropDown = ""
    }
    ChefmateAppTheme {
        androidx.compose.material.Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopBar(
                    navController = navController,
                    onNavigationIconClick = {
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }
                )
            },
            modifier = Modifier.background(color = blue),
            drawerContent = {
                DrawerHeader()
                DrawerBody(closeNavDrawer = {
                    navController.navigate(Screen.ContactUsScreen.route)
                    scope.launch {
                        scaffoldState.drawerState.close()
                    }
                }, onLogout = {
                    isLogout = true
                    scope.launch {
                        scaffoldState.drawerState.close()
                    }
                })
            },
            backgroundColor = blue,
            contentColor = blue,
            drawerBackgroundColor = blue
        ) { paddingValues ->
            Modifier.padding(
                bottom = paddingValues.calculateBottomPadding()
            )
            Column(
                modifier = Modifier
                    .background(color = blue)
                    .verticalScroll(scrollState)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    CustomSearchView(search = search, onValueChange = {
                        search = it
                    }, onClick = {
                        if(search.isNotEmpty()) {
                            navController.navigate(Screen.SearchScreen.route+"/$search")
                        }else{
                            navController.navigate(Screen.SearchScreen.route+"/null")
                        }

                    })
                }
                OutlinedTextField(
                    value = if (filterDropDown != "") filterDropDown else "Select health factor",
                    onValueChange = { filterDropDown = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .clickable { filter = !filter },
                    enabled = false,
                    trailingIcon = {
                        Icon(
                            icon, "contentDescription",
                            tint = white
                            )
                    },
                    textStyle = TextStyle(color = white),
                    colors = androidx.compose.material3.TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = white,
                        unfocusedBorderColor = white,
                        disabledBorderColor = white
                    ),
                    shape = RoundedCornerShape(50.dp),
                    )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    if (filter) {
                        Popup(
                            alignment = Alignment.TopCenter,
                            properties = PopupProperties(
                                excludeFromSystemGesture = true,
                            ),
                            onDismissRequest = { filter = false }
                        ) {

                            Column(
                                modifier = Modifier
                                    .heightIn(max = 220.dp)
                                    .verticalScroll(state = scrollState)
                                    .padding(10.dp)
                                    .border(width = 1.dp, color = Color.Gray),
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {

                                filterList.onEachIndexed { index, item ->
                                    if (index != 0) {
                                        Divider(thickness = 1.dp, color = Color.LightGray)
                                    }
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .background(white)
                                            .padding(10.dp)
                                            .clickable {
                                                filterDropDown = item
                                                filter = !filter
                                            },
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(text = item ,style = TextStyle(color = Color.Black))
                                    }
                                }

                            }
                        }
                    }
                }
                Spacer(Modifier.height(10.dp))
                OutlinedTextField(
                    value = if (ratingDropDown != "") ratingDropDown else "Select rate",
                    onValueChange = { ratingDropDown = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .clickable { rate = !rate },
                    enabled = false,
                    trailingIcon = {
                        Icon(
                            icon, "contentDescription",
                            tint = white
                        )
                    },
                    textStyle = TextStyle(color = white),
                    colors = androidx.compose.material3.TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = white,
                        unfocusedBorderColor = white,
                        disabledBorderColor = white
                    ),
                    shape = RoundedCornerShape(50.dp),
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    if (rate) {
                        Popup(
                            alignment = Alignment.TopCenter,
                            properties = PopupProperties(
                                excludeFromSystemGesture = true,
                            ),
                            onDismissRequest = { rate = false }
                        ) {

                            Column(
                                modifier = Modifier
                                    .heightIn(max = 220.dp)
                                    .verticalScroll(state = scrollState)
                                    .padding(10.dp)
                                    .border(width = 1.dp, color = Color.Gray),
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {

                                ratingList.onEachIndexed { index, item ->
                                    if (index != 0) {
                                        Divider(thickness = 1.dp, color = Color.LightGray)
                                    }
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .background(white)
                                            .padding(10.dp)
                                            .clickable {
                                                ratingDropDown = item
                                                rate = !rate
                                            },
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(text = item ,style = TextStyle(color = Color.Black))
                                    }
                                }

                            }
                        }
                    }
                }
                Spacer(Modifier.height(10.dp))
                if (filterDropDown != "" || ratingDropDown != "") {
                    Row(
                        modifier = Modifier.padding(start = 10.dp, end = 10.dp)
                    ) {
                        RoundedButton(
                            text = "Search",
                            textColor = blue,
                            onClick = {
                                val filterData = if(filterDropDown.isNotEmpty()) filterDropDown else "null"
                                val rateData = if(ratingDropDown.isNotEmpty()) ratingDropDown else "null"
                                navController.navigate(Screen.SearchScreen.route+"/$filterData"+"/$rateData")
                            }
                        )
                    }
                }
                Spacer(Modifier.height(10.dp))
                list.forEachIndexed { index, productModel ->
                    Card(
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()
                            .height(200.dp)
                            .clickable {
                                navController.navigate(Screen.Detail.route+"/${productModel.name}"+"/${productModel.image}"+"/${productModel.price}"+"/${productModel.detail}")
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

            }

        }
        if (isLogout) {
            AlertDialog(
                onDismissRequest = {
                    isLogout = false
                },
                title = { Text(stringResource(id = R.string.app_name)) },
                text = { Text("Are you sure you want to logout ?") },
                confirmButton = {
                    RoundedButton(
                        text = "Cancel",
                        textColor = blue,
                        onClick = { isLogout = false }
                    )
                },
                dismissButton = {

                    RoundedButton(
                        text = "Logout",
                        textColor = blue,
                        onClick = {
                            preferenceManager.saveData("isLogin", false)
                            navController.navigate(
                                Screen.LoginScreen.route
                            ) {
                                popUpTo(Screen.MainScreen.route) {
                                    inclusive = true
                                }
                            }
                            isLogout = false
                        }
                    )

                }
            )
        }


    }

}
