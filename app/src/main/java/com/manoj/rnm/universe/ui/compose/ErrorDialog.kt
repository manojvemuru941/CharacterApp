package com.manoj.rnm.universe.ui.compose

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import com.manoj.rnm.universe.R

@Composable
fun ErrorDialog(
    title: String? = null,
    message: String,
    positiveOnclick: () -> Unit,
    negativeOnclick: () -> Unit
) {
    val openDialog = remember { mutableStateOf(true) }
    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            title = {
                Text(text = title ?: stringResource(R.string.something_went_wrong))
            },
            text = {
                Text(message)
            },
            confirmButton = {
                Button(
                    onClick = {
                        positiveOnclick.invoke()
                        openDialog.value = false
                    }) {
                    Text(stringResource(id = R.string.retry))
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        negativeOnclick.invoke()
                        openDialog.value = false
                    }) {
                    Text(stringResource(id = R.string.ok))
                }
            }
        )
    }
}