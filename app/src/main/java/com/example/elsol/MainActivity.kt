package com.example.elsol

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.elsol.ui.theme.ElSolTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ElSolTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        BottomAppBar(
                            containerColor = Color.Red,
                            contentColor = Color.White,
                            actions = {
                                IconButton(
                                    onClick = {/* Hacer algo */}
                                ) {
                                    Icon(
                                        Icons.AutoMirrored.Default.ArrowBack,
                                        "Icono flecha izquierda"
                                        )
                                }
                                IconButton(
                                    onClick = {/* Hacer algo */}
                                ) {
                                    Icon(
                                        Icons.Default.Favorite,
                                        "Icono corazon izquierda"
                                    )
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
                 PantallaMainSol(modifier = Modifier
                     .padding(innerPadding)
                 )
                }
            }
        }
    }
}
