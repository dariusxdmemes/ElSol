package com.example.elsol

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.elsol.ui.theme.ElSolTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ElSolTheme {

                var itemCount by remember { mutableIntStateOf(0) }
                val snackbarHostState = remember { SnackbarHostState() }
                val corutina = rememberCoroutineScope()
                val navController = rememberNavController()
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val listaBotones = getInfoBotones()
                val buttonSelected = false

                ModalNavigationDrawer(
                    modifier = Modifier
                        .statusBarsPadding(),
                    drawerState = drawerState,
                    drawerContent = {
                        ModalDrawerSheet(
                            modifier = Modifier
                                .fillMaxWidth(0.8f)
                        ) {
                            Image(
                                painter = painterResource(R.drawable.erupcionsolar),
                                contentDescription = "imagen cajon",
                                contentScale = ContentScale.Crop,
                            )
                            Column(
                                modifier = Modifier
                                    .padding(start = 10.dp)
                            ) {
                                Spacer(
                                    modifier = Modifier
                                        .size(10.dp)
                                )
                                listaBotones.forEach { boton ->
                                    NavigationDrawerItem(
                                        label = { Row(
                                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                                        ) {
                                            Icon(
                                                boton.iconoBoton,
                                                "iconoBoton"
                                            )
                                            Text(
                                                boton.textoBoton
                                            )
                                        }
                                                },
                                        selected = buttonSelected,
                                        onClick = {
                                            when (boton.textoBoton) {
                                                "Build" -> {}
                                                "Info" -> {
                                                    navController.navigate("PantallaInfo")
                                                    corutina.launch {
                                                        drawerState.close()
                                                    }
                                                }
                                                "Email" -> {}
                                            }
                                        }
                                    )

                                }
                            }
                        }
                    }
                ) {
                    Scaffold(
                        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
                        modifier = Modifier.fillMaxSize(),
                        bottomBar = {
                            BottomAppBar(
                                containerColor = Color.Red,
                                contentColor = Color.White,
                                actions = {
                                    IconButton(
                                        onClick = {
                                            corutina.launch {
                                                drawerState.open()
                                            }
                                        }
                                    ) {
                                        Icon(
                                            Icons.AutoMirrored.Default.ArrowBack,
                                            "Icono flecha izquierda"
                                        )
                                    }
                                    BadgedBox(
                                        badge = {
                                            if (itemCount > 0) {
                                                Badge(
                                                    containerColor = Color(174, 35, 30, 255),
                                                    contentColor = Color.White
                                                ) {
                                                    Text(
                                                        text = "$itemCount"
                                                    )
                                                }
                                            }
                                        }
                                    ) {
                                        IconButton(
                                            onClick = { itemCount++ }
                                        ) {
                                            Icon(
                                                Icons.Default.Favorite,
                                                "Icono corazon izquierda"
                                            )
                                        }
                                    }
                                },
                                floatingActionButton = {
                                    FloatingActionButton(
                                        onClick = {/* Accion del FAB */},
                                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                                        contentColor = Color(0, 0, 0)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Filled.Add,
                                            contentDescription = "FAB añadir"
                                        )
                                    }
                                }
                            )
                        }
                    ) { innerPadding ->
                        NavHost(
                            navController = navController,
                            startDestination = "PantallaMainSol",
                            modifier = Modifier
                                .padding(innerPadding)
                        ) {
                            composable("PantallaMainSol") {
                                PantallaMainSol(
                                    modifier = Modifier,
                                    snackbarHostState = snackbarHostState,
                                    coroutineScope = corutina
                                )
                            }
                            composable("PantallaInfo") {
                                PantallaInfo()
                            }
                        }
                    }
                }
            }
        }
    }
}

fun getInfoBotones(): List<BotonesCajon> {
    return listOf(
        BotonesCajon(
            "Build",
            Icons.Default.Build
        ),
        BotonesCajon(
            "Info",
            Icons.Default.Info
        ),
        BotonesCajon(
            "Email",
            Icons.Default.Email
        )
    )
}