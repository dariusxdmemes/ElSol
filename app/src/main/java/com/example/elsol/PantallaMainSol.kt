package com.example.elsol

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun PantallaMainSol(modifier: Modifier, coroutineScope: CoroutineScope, snackbarHostState: SnackbarHostState) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
    ) {
        LazyVerticalGridFotosSol(coroutineScope, snackbarHostState)
    }
}

@Composable
fun LazyVerticalGridFotosSol(coroutineScope: CoroutineScope, snackbarHostState: SnackbarHostState) {
    val listaFotos = remember {
        mutableStateListOf<ImagenSol>().apply {
            addAll(getImagenesSol())
        }
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(12.dp),
        content = {
            items(listaFotos.size) { i ->
                var expandedMenu by remember { mutableStateOf(false) }
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(3.dp),
                    onClick = {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar(listaFotos[i].textoImagen)
                        }
                    }
                ) {
                    Column {
                        Image(
                            painter = painterResource(listaFotos[i].rutaImagen),
                            contentDescription = listaFotos[i].descripImagen,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .height(200.dp)
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color(228, 218, 253, 255))
                                .padding(start = 12.dp, end = 12.dp)
                        ) {
                            Text(
                                text = listaFotos[i].textoImagen
                            )
                            IconButton(
                                onClick = {
                                    expandedMenu = true
                                }
                            ) {
                                Icon(
                                    Icons.Default.MoreVert,
                                    "Icono tres puntos dropdown"
                                )
                            }
                            DropdownMenu(
                                expanded = expandedMenu,
                                onDismissRequest = { expandedMenu = false }
                            ) {
                                DropdownMenuItem(
                                    text = { Text("Copiar") },
                                    onClick = {
                                        val item = listaFotos[i]
                                        listaFotos.add(item.copy(nombreImagen = item.nombreImagen + " copia"))
                                        expandedMenu = false
                                    },
                                    leadingIcon = { Icon(Icons.Default.Add, "mas") }
                                )
                                DropdownMenuItem(
                                    text = { Text("Eliminar") },
                                    onClick = {
                                        val item = listaFotos[i]
                                        listaFotos.remove(item)
                                        expandedMenu = false
                                    },
                                    leadingIcon = { Icon(Icons.Default.Delete, "borrar") }
                                )
                            }
                        }
                    }
                }
            }
        }
    )
}

fun getImagenesSol(): MutableList<ImagenSol> {
    return mutableListOf(
        ImagenSol("imagenCorona1",
            "imagen corona solar",
            R.drawable.corona_solar,
            "Corona Solar"
            ),
        ImagenSol(
            "imagenErupcion2",
            "imagen erupcion solar",
            R.drawable.erupcionsolar,
            "Erupción Solar"
        ),
        ImagenSol(
            "imagenEspiculas3",
            "imagen espiculas",
            R.drawable.espiculas,
            "Espiculas"
        ),
        ImagenSol("imagenFilamentos4",
            "imagen filamentos",
            R.drawable.filamentos,
            "Filamentos"),
        ImagenSol(
            "imagenMagnetosfera5",
            "imagen magnetosfera",
            R.drawable.magnetosfera,
            "Magnetosfera"
        ),
        ImagenSol(
            "imagenManchaSolar6",
            "imagen mancha solar",
            R.drawable.manchasolar,
            "Mancha Solar"
        )
    )
}