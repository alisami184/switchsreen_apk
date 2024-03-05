package com.example.switchscreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.switchscreen.ui.theme.SwitchscreenTheme
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue


import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SwitchscreenTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "screen1") {
                        composable("screen1") { Screen1(navController) }
                        composable(
                            route = "screen2/{currentContact}",
                            arguments = listOf(navArgument("currentContact") { type = NavType })
                        ) { backStackEntry ->
                            Screen2(
                                navController,
                                currentContact = backStackEntry.arguments?.get
                            )
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun Screen1(navController: NavHostController)
{
    val noms = listOf("Ali", "Wiam","Maeva", "Mathilde","Yassmine")
    val contacts = listOf<Contact>(Contact("Ali",12,"single","étudiant"), Contact("Wiam",22,"single","étudiant"),
        Contact("Maeva",18,"single","étudiant"),Contact("Mathilde",30,"mariée","caissiere"),
        Contact("Yassmine",30,"divorcé avec 10 enfants","psychopate"))

    var nbr by rememberSaveable { mutableStateOf(1) }

    var currentContact by remember { mutableStateOf(contacts.random()) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Spacer(modifier = Modifier.height(100.dp))

        Box(modifier = Modifier
            .size(150.dp)
            .height(100.dp)
        )
        {
            Text(text = " ${currentContact.name}",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp
                )
            )

        }

        Spacer(modifier = Modifier.height(50.dp))

        Box(modifier = Modifier
            .size(200.dp)
            .padding(10.dp))

        {
            Button(
                onClick = { nbr = nbr+1; currentContact = contacts.random() },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)
            )
            {
                Text(

                    "numero $nbr ",
                    color = Color.Blue,
                    style = TextStyle
                        (
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Medium
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(50.dp))

        Box(modifier = Modifier
            .size(200.dp)
            .padding(10.dp))

        {
            Button(onClick = { navController.navigate("screen2/${currentContact}") }) {
                Text(text = "Details")
            }
        }
    }
}

@Composable
fun Screen2(navController: NavHostController, currentContact: Contact) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Spacer(modifier = Modifier.height(100.dp))

        Box(modifier = Modifier
            .size(150.dp)
            .height(100.dp)
        )

        {
            Text(
                text = " ${currentContact.name}",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp
                )
            )
        }

        Spacer(modifier = Modifier.height(100.dp))

        Box(modifier = Modifier
            .size(150.dp)
            .height(100.dp)
        )

        {
            Text(
                text = " ${currentContact.age}",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp
                )
            )
            Spacer(modifier = Modifier.height(100.dp))
            Text(
                text = " ${currentContact.metier}",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp
                )
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate("screen1") }) {
            Text(text = "Go back")
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    SwitchscreenTheme {
//        Surface(
//            color = MaterialTheme.colors.background,
//            modifier = Modifier.fillMaxSize()
//        ) {
//            val navController = rememberNavController()
//            NavHost(navController = navController, startDestination = "screen1") {
//                composable("screen1") { Screen1(navController) }
//                composable(
//                    route = "screen2/{Contact}",
//                    arguments = listOf(navArgument("name") { type = NavType.StringType })
//                ) { backStackEntry ->
//                    Screen2(
//                        navController,
//                        Contact.name = backStackEntry.arguments?.getString("name") ?: ""
//                    )
//                }
//            }
//        }
//    }
//}