package dev.vdbroek.pepijn98.home

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Box
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
import dev.vdbroek.pepijn98.common.Carousel
import dev.vdbroek.pepijn98.openDialog
import dev.vdbroek.pepijn98.util.Nature
import dev.vdbroek.pepijn98.util.natureList1
import dev.vdbroek.pepijn98.util.natureList2

val padding = 16.dp
val corner = 8.dp
val elevation = 4.dp

interface Home {

    companion object {
        @Composable
        fun Content(
            scrollState: ScrollState,
            carouselState: LazyListState
        ) {
            ScrollableColumn(
                modifier = Modifier.padding(PaddingValues(0.dp, 0.dp, 0.dp, 0.dp)),
                scrollState = scrollState
            ) {
                NatureList(natures = natureList1)

                Carousel(
                    items = natureList1 + natureList2,
                    state = carouselState,
                    contentPadding = PaddingValues(0.dp, padding, padding, 0.dp)
                ) { nature, _ ->
                    Card(
                        modifier = Modifier.height(220.dp - padding).width(220.dp)
                            .padding(PaddingValues(padding, 0.dp, 0.dp, 0.dp)),
                        shape = RoundedCornerShape(corner),
                        elevation = elevation
                    ) {
                        Image(
                            asset = imageResource(id = nature.image),
                            modifier = Modifier.size(220.dp)
                        )
                    }
                }

                NatureList(natures = natureList2)
            }

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
private fun NatureList(natures: List<Nature>) {
    val context = ContextAmbient.current
    for (nature in natures) {
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
            .padding(PaddingValues(padding, padding, padding, if (nature.id == 9) 74.dp else 0.dp))
    ) {
        Card(
            modifier = Modifier.clickable(onClick = { onNatureClick(nature) })
                .fillMaxWidth()
                .height(160.dp),
            shape = RoundedCornerShape(corner),
            elevation = elevation
        ) {
            Row(modifier = Modifier.fillMaxSize()) {
                Image(
                    modifier = Modifier.fillMaxHeight().width(160.dp),
                    asset = imageResource(id = nature.image),
                    alignment = Alignment.CenterStart
                )
                Text(
                    text = "Nature ${nature.id}",
                    modifier = Modifier.padding(PaddingValues(padding, padding / 2, 0.dp, 0.dp)),
                    fontWeight = FontWeight(900)
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewMainScreen() {
    val scrollState = rememberScrollState()
    val carouselState = rememberLazyListState()
    Home.Content(scrollState, carouselState)
}
