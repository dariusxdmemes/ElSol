package com.example.elsol

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun PantallaMainSol(modifier: Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

    }
}

@Composable
fun StaggeredGridFotosSol() {

}

fun getImagenesSol(): List<ImagenSol> {
    return listOf(
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
        ImagenSol("imagenFilamentos",
            "imagen filamentos",
            R.drawable.filamentos,
            "Filamentos"),
        ImagenSol(
            "imagenMagnetosfera",
            "imagen magnetosfera",
            R.drawable.magnetosfera,
            "Magnetosfera"
        ),
        ImagenSol(
            "imagenManchaSolar",
            "imagen mancha solar",
            R.drawable.manchasolar,
            "Mancha Solar"
        )
    )
}