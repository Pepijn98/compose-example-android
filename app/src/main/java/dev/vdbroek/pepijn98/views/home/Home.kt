package dev.vdbroek.pepijn98.views.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.vdbroek.pepijn98.R
import dev.vdbroek.pepijn98.common.Carousel
import dev.vdbroek.pepijn98.models.Nature
import dev.vdbroek.pepijn98.models.natureList1
import dev.vdbroek.pepijn98.models.natureList2
import dev.vdbroek.pepijn98.openDialog
import dev.vdbroek.pepijn98.title

val padding = 16.dp
val corner = 10.dp
val elevation = 4.dp
val defaultShape = RoundedCornerShape(corner)

interface Home {

    companion object {

        @Composable
        fun Content(
            scrollState: ScrollState,
            carouselState: LazyListState,
            onNatureClicked: (Nature) -> Unit
        ) {
            title = stringResource(id = R.string.app_name)

            Column(
                modifier = Modifier
                    .verticalScroll(scrollState)
                    .padding(0.dp),
            ) {
                for (nature in natureList1) {
                    NatureRow(nature, onNatureClicked)
                }

                val items = natureList1 + natureList2
                Carousel(
                    listItems = items,
                    state = carouselState,
                    contentPadding = PaddingValues(top = padding, end = padding)
                ) { index ->
                    val nature = items[index]

                    Box(
                        modifier = Modifier
                            .size(width = 220.dp, height = 220.dp - padding)
                            .padding(start = padding)
                            .shadow(elevation, defaultShape)
                            .clip(defaultShape)
                    ) {
                        Card(
                            modifier = Modifier
                                .fillMaxSize()
                                .clickable(onClick = { onNatureClicked(nature) }),
                            shape = defaultShape
                        ) {
                            Image(
                                painter = painterResource(id = nature.image),
                                modifier = Modifier.size(220.dp),
                                contentDescription = null
                            )
                        }
                    }
                }

                for (nature in natureList2) {
                    NatureRow(nature, onNatureClicked)
                }
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
private fun NatureRow(nature: Nature, onNatureClicked: (Nature) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(padding, padding, padding, if (nature.id == 9) 74.dp else 0.dp)
            .shadow(elevation, defaultShape)
            .clip(defaultShape)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .clickable(onClick = { onNatureClicked(nature) }),
            shape = defaultShape
        ) {
            Row(modifier = Modifier.fillMaxSize()) {
                Image(
                    painter = painterResource(id = nature.image),
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(160.dp),
                    alignment = Alignment.CenterStart,
                    contentDescription = null
                )
                Text(
                    text = "Nature ${nature.id}",
                    modifier = Modifier.padding(
                        start = padding,
                        top = padding / 2
                    ),
                    fontWeight = FontWeight(900)
                )
            }
        }
    }
}
