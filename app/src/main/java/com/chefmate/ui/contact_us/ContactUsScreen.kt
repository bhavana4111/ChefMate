package com.chefmate.ui.contact_us

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.chefmate.R
import com.chefmate.routing.Screen
import com.chefmate.ui.theme.ChefmateAppTheme
import com.chefmate.ui.theme.blue
import com.chefmate.ui.theme.white
import com.chefmate.utils.OutlineFormField
import com.chefmate.utils.RoundedButton
import com.chefmate.utils.isValidEmail

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ContactUsScreen(navController: NavController) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    var name by remember { mutableStateOf("") }
    var isBooked by remember { mutableStateOf(false) }
    var email by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    ChefmateAppTheme {
        Scaffold {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = blue)
                    .padding(top = 40.dp)
                    .verticalScroll(scrollState)
            ) {
                SmallTopAppBar(
                    title = {
                        Text(
                            text = "Contact Us", color = Color.White,
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
                            Icon(
                                imageVector = Icons.Rounded.ArrowBack,
                                tint = Color.White,
                                contentDescription = "Back"
                            )
                        }
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = blue,
                        titleContentColor = Color.White
                    )
                )

                Column(modifier = Modifier.background(color = blue)) {
                    Text(
                        "Send us a message and We will contact you as soon as possible!",
                        fontSize = 14.sp,
                        color = white,
                        modifier = Modifier
                            .padding(vertical = 10.dp, horizontal = 15.dp),

                        )
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(20.dp)
                    ) {

                        Spacer(modifier = Modifier.height(10.dp))

                        OutlineFormField(
                            value = name,
                            onValueChange = { text ->
                                name = text
                            },
                            placeholder = "Enter name",
                            keyboardType = KeyboardType.Text,
                        )

                        Spacer(modifier = Modifier.height(10.dp))
                        OutlineFormField(
                            value = email,
                            onValueChange = { text ->
                                email = text
                            },
                            placeholder = "Enter email",
                            keyboardType = KeyboardType.Email,
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        OutlineFormField(
                            value = message,
                            onValueChange = { text ->
                                message = text
                            },
                            placeholder = "Enter message",
                            keyboardType = KeyboardType.Text,
                        )

                        Spacer(modifier = Modifier.height(20.dp))
                        RoundedButton(
                            text = "Submit",
                            textColor = blue,
                            onClick = {
                                if (name.isNotEmpty()) {
                                    if (email.isNotEmpty()) {
                                        if (!isValidEmail(email.toString().trim())) {
                                            if (message.isNotEmpty()) {
                                                isBooked = true

                                            } else {
                                                Toast.makeText(
                                                    context,
                                                    "Please enter message.",
                                                    Toast.LENGTH_LONG
                                                ).show()
                                            }
                                        } else {
                                            Toast.makeText(
                                                context,
                                                "Please enter email.",
                                                Toast.LENGTH_LONG
                                            ).show()
                                        }
                                    } else {
                                        Toast.makeText(
                                            context,
                                            "Please enter valid email.",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                } else {
                                    Toast.makeText(
                                        context,
                                        "Please enter name.",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))

                }
            }
        }
        if (isBooked) {
            AlertDialog(
                onDismissRequest = {
                    isBooked = false
                },
                title = { Text(stringResource(id = R.string.app_name)) },
                text = { Text("We will contact you soon.") },
                confirmButton = {
                    RoundedButton(
                        text = "Ok",
                        textColor = blue,
                        onClick = {
                            navController.navigateUp()
                            isBooked = false
                        }
                    )
                },
                dismissButton = {}
            )
        }


    }
}