package dev.vdbroek.pepijn98.views.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import dev.vdbroek.pepijn98.openDialog
import dev.vdbroek.pepijn98.title

interface Profile {

    companion object {
        @Composable
        fun Content() {
            title = "Profile"

            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = "Profile",
                    fontWeight = FontWeight(900),
                    fontSize = 28.sp
                )
            }

            if (openDialog) {
                AlertDialog(
                    onDismissRequest = {
                        openDialog = false
                    },
                    title = {
                        Text(text = "New Item")
                    },
                    text = {
                        Text(text = "Add new item alert dialog!")
                    },
                    confirmButton = {
                        Button(onClick = {
                            openDialog = false
                        }) {
                            Text(text = "Confirm")
                        }
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewMainScreen() {
    Profile.Content()
}
