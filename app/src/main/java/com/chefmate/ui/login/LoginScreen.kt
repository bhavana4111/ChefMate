package com.chefmate.ui.login

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.chefmate.R
import com.chefmate.routing.Screen
import com.chefmate.ui.chefmate_database.ChefmateDatabase
import com.chefmate.ui.theme.ChefmateAppTheme
import com.chefmate.ui.theme.blue
import com.chefmate.ui.theme.white
import com.chefmate.utils.OutlineFormField
import com.chefmate.utils.RoundedButton
import com.chefmate.utils.isValidEmail

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(navController: NavController) {
    val context = LocalContext.current
    val preferenceManager = remember {
        ChefmateDatabase(context)
    }
    val scrollState = rememberScrollState()
    var userId by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val db = Firebase.firestore
    var showDialog by remember { mutableStateOf(false) }
    ChefmateAppTheme {
        Scaffold {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(blue)
                    .padding(10.dp)
            ) {
                Spacer(modifier = Modifier.height(60.dp))
                Image(
                    painter = painterResource(id = R.drawable.ic_chefmat),
                    contentDescription = "",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .width(120.dp)
                        .height(120.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    "Sign in to Get Started",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = TextStyle(color = white)
                )

                Spacer(modifier = Modifier.height(30.dp))
                Text(
                    "User Id",
                    modifier = Modifier.fillMaxWidth(),
                    style = TextStyle(color = white)
                )
                Spacer(modifier = Modifier.height(10.dp))
                OutlineFormField(
                    value = userId,
                    onValueChange = { text ->
                        userId = text
                    },
                    placeholder = "Enter user id",
                    keyboardType = KeyboardType.Text,
                )

                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    "Password",
                    modifier = Modifier.fillMaxWidth(),
                    style = TextStyle(color = white)
                )
                Spacer(modifier = Modifier.height(10.dp))
                OutlineFormField(
                    value = password,
                    onValueChange = { text ->
                        password = text
                    },
                    placeholder = "Enter Password",
                    keyboardType = KeyboardType.Password,
                    visualTransformation = PasswordVisualTransformation(),
                )

                Spacer(modifier = Modifier.height(30.dp))
                Row(
                    modifier = Modifier
                ) {
                    RoundedButton(
                        text = "Login",
                        textColor = blue,
                        onClick = {
                            if (userId.isNotEmpty()) {
                                if (password.isNotEmpty()) {
                                    showDialog = true
                                    db.collection("users")
                                        .get()
                                        .addOnSuccessListener { result ->
                                            if (result.isEmpty) {

                                                Toast.makeText(
                                                    context,
                                                    "Invalid user.",
                                                    Toast.LENGTH_LONG
                                                ).show()
                                                showDialog = false
                                                return@addOnSuccessListener
                                            } else {
                                                for (document in result) {
                                                    Log.e(
                                                        "TAG",
                                                        "setOnClick: $document"
                                                    )
                                                    if (document.data["userId"] == userId.lowercase() &&
                                                        document.data["password"] == password
                                                    ) {
                                                        preferenceManager.saveData(
                                                            "isLogin",
                                                            true
                                                        )
                                                        Toast.makeText(
                                                            context,
                                                            "Login successfully.",
                                                            Toast.LENGTH_LONG
                                                        ).show()
                                                        navController.navigate(
                                                            Screen.MainScreen.route
                                                        ) {
                                                            popUpTo(Screen.LoginScreen.route) {
                                                                inclusive = true
                                                            }
                                                        }
                                                        showDialog = false
                                                    } else if (document.data["userId"] == userId.lowercase() &&
                                                        document.data["password"] != password
                                                    ) {
                                                        Toast.makeText(
                                                            context,
                                                            "Invalid password.",
                                                            Toast.LENGTH_LONG
                                                        ).show()
                                                        showDialog = false
                                                        return@addOnSuccessListener
                                                    } else {
                                                        Toast.makeText(
                                                            context,
                                                            "Invalid user.",
                                                            Toast.LENGTH_LONG
                                                        ).show()
                                                        showDialog = false
                                                        return@addOnSuccessListener
                                                    }
                                                }
                                            }

                                        }
                                        .addOnFailureListener { exception ->
                                            Toast.makeText(
                                                context,
                                                exception.message.toString(),
                                                Toast.LENGTH_LONG
                                            ).show()
                                            showDialog = false
                                        }
                                } else {
                                    Toast.makeText(
                                        context,
                                        "Please enter password.",
                                        Toast.LENGTH_LONG
                                    ).show()

                                }
                            } else {
                                Toast.makeText(
                                    context,
                                    "Please enter user id.",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    )
                }


            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 20.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Don't have an account?",
                        textAlign = TextAlign.End,
                        style = TextStyle(color = white)
                    )

                    Text(
                        " Register", modifier = Modifier.clickable {
                            navController.navigate(Screen.RegisterScreen.route)
                        }, textAlign = TextAlign.End,
                        style = TextStyle(color = white)
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
            if (showDialog) {
                Dialog(
                    onDismissRequest = {  },
                    DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
                ) {
                    Box(
                        contentAlignment= Alignment.Center,
                        modifier = Modifier
                            .size(100.dp)
                            .background(white, shape = RoundedCornerShape(8.dp))
                    ) {
                        CircularProgressIndicator(color = blue)
                    }
                }
            }


        }
    }
}