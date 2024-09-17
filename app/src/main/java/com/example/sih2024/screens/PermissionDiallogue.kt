package com.example.sih2024.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun PermissionDialog(
    modifier: Modifier = Modifier,
    permissionTextProvider: PermissionTextProvider,
    isPermanentlyDeclined : Boolean,
    onDismiss : () -> Unit,
    onOkClick : () -> Unit,
    onGoToAppSettings : () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                HorizontalDivider()
                Text(
                    text = if (isPermanentlyDeclined) {
                        "Grant Permission"
                    }
                    else{
                        "Ok"
                    },
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            if (isPermanentlyDeclined) {
                                onGoToAppSettings()
                            } else {
                                onOkClick()
                            }
                        }
                        .padding(16.dp)
                )
            }
        },
        title = {
            Text(text = "Permission required")
        },
        text = {
            Text(text = permissionTextProvider.getDescription(isPermanentlyDeclined))
        },
        modifier = modifier
    )
}

interface PermissionTextProvider{
    fun getDescription(isPermanentlyDeclined: Boolean) : String
}

class CameraPermissionTextProvider : PermissionTextProvider{
    override fun getDescription(isPermanentlyDeclined: Boolean): String {
        return if(isPermanentlyDeclined){
            "It Seems you permanently decline Camera permission " + "you cn go to the app settings to grant it"
        }
        else{
            "This app requires camera permission to monitor your plants to tell about their health"
        }
    }
}

class RecordPermissionTextProvider : PermissionTextProvider{
    override fun getDescription(isPermanentlyDeclined: Boolean): String {
        return if(isPermanentlyDeclined){
            "It Seems you permanently decline microphone permission " + "you cn go to the app settings to grant it"
        }
        else{
            "This app requires Record permission to monitor your plants to tell about their health"
        }
    }
}

class LocationPermissionTextProvider : PermissionTextProvider{
    override fun getDescription(isPermanentlyDeclined: Boolean): String {
        return if(isPermanentlyDeclined){
            "It Seems you permanently decline Location_permission " + "you cn go to the app settings to grant it"
        }
        else{
            "This app requires location permission to get your location to deliver your goods"
        }
    }

}