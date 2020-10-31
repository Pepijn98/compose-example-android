package dev.vdbroek.pepijn98.home

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import dev.vdbroek.pepijn98.openDialog
import dev.vdbroek.pepijn98.util.Nature
import dev.vdbroek.pepijn98.util.images

interface Home {

    companion object {
        @Composable
        fun Content(listState: LazyListState) {
            NatureList(natureList = images, state = listState)

            if (openDialog) {
                AlertDialog(
                    onDismissRequest = {
                        openDialog = false
                    },
                    title = {
                        Text(text = "New Nature")
                    },
                    text = {
                        Text(text = "Add new nature alert dialog!")
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

@Composable
private fun NatureList(natureList: List<Nature>, state: LazyListState) {
    val context = ContextAmbient.current
    LazyColumnFor(
        items = natureList,
        modifier = Modifier.fillMaxSize().padding(PaddingValues(0.dp, 0.dp, 0.dp, 0.dp)),
        state = state
    ) { nature ->
        NatureRow(
            nature = nature,
            onNatureClick = {
                Toast.makeText(context, "Nature ${nature.id}", Toast.LENGTH_SHORT).show()
            }
        )
    }
}

@Composable
private fun NatureRow(nature: Nature, onNatureClick: (Nature) -> Unit) {
    Box(
        modifier = Modifier.fillMaxWidth()
            .padding(PaddingValues(16.dp, 16.dp, 16.dp, if (nature.id == 9) 74.dp else 0.dp))
    ) {
        Card(
            modifier = Modifier.clickable(onClick = { onNatureClick(nature) })
                .fillMaxWidth()
                .height(160.dp),
            shape = RoundedCornerShape(8.dp),
            elevation = 4.dp
        ) {
            Row(modifier = Modifier.fillMaxSize()) {
                Image(
                    modifier = Modifier.fillMaxHeight().width(160.dp),
                    asset = imageResource(id = nature.image),
                    alignment = Alignment.CenterStart
                )
                Text(
                    text = "Nature ${nature.id}",
                    modifier = Modifier.padding(PaddingValues(16.dp, 8.dp, 0.dp, 0.dp)),
                    fontWeight = FontWeight(900)
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewMainScreen() {
    val listState = rememberLazyListState()
    Home.Content(listState)
}
