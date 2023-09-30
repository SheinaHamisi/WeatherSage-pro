package com.sheinahamisi.weathersagepro.presentation.components

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.sheinahamisi.weathersagepro.presentation.ui.theme.WeathersageProTheme

@Composable
fun ErrorDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    text: String,
    action: () -> Unit,
    actionText: String
) {
    if (showDialog) {
        Dialog(onDismissRequest = onDismiss) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colorScheme.background
                    )
                    .padding(horizontal = 18.dp, vertical = 18.dp)
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = text,
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(24.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    TextButton(onClick = onDismiss) {
                        Text(text = "Dismiss")
                    }
                    TextButton(onClick = action) {
                        Text(text = actionText)
                    }
                }
            }
        }
    }
}


@Preview(uiMode = UI_MODE_NIGHT_NO)
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ErrorDialogPreview() {
    WeathersageProTheme {
        ErrorDialog(
            showDialog = true,
            onDismiss = { },
            text = "An unexpected error occurred!",
            action = {

            },
            actionText = "Go to settings"
        )
    }
}