package com.example.elsol

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaInfo() {

    var loading by remember {
        mutableStateOf(false)
    }

    var dateSelectorIsClicked by remember {
        mutableStateOf(false)
    }

    val datePickerState = rememberDatePickerState()

    val onDateSelected: (Long?) -> Unit = {
        dateSelectorIsClicked
    }
    val onDismiss: () -> Unit = {
        dateSelectorIsClicked = false
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(50, 100, 160)),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(Color(200, 200, 100)),
        ) {
            Button(
                onClick = {
                    loading = true
                }
            ) {
                Text(
                    text = "Download more info"
                )
            }

            if (!loading) return
            CositoQueGira()

            Button(
                onClick = {
                    dateSelectorIsClicked = !dateSelectorIsClicked
                }
            ) {
                Text(
                    text = "Visit planetarium. Select date"
                )
            }

            if (dateSelectorIsClicked) {
                DatePickerDialog(
                    onDismissRequest = onDismiss,
                    confirmButton = {
                        TextButton(
                            onClick = {
                                onDateSelected(datePickerState.selectedDateMillis)
                            }
                        ) {
                            Text("OK")
                        }
                    },
                    dismissButton = {
                        TextButton(
                            onClick = onDismiss
                        ) {
                            Text("Cancel")
                        }
                    }
                ) {
                    DatePicker(
                        state = datePickerState
                    )
                }
            }
        }
    }
}

@Composable
fun CositoQueGira() {
    CircularProgressIndicator(
        modifier = Modifier
            .width(64.dp),
        color = MaterialTheme.colorScheme.secondary,
        trackColor = MaterialTheme.colorScheme.surfaceVariant
    )
}