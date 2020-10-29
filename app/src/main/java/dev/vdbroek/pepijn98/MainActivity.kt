package dev.vdbroek.pepijn98

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.runtime.Composable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.WithConstraints
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradient
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.platform.DensityAmbient
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import dev.vdbroek.pepijn98.ui.*
import dev.vdbroek.pepijn98.util.Nature
import dev.vdbroek.pepijn98.util.images

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Pepijn98Theme {
                App()
            }
        }
    }
}

@Composable
fun App() {
    val fabShape = CircleShape
    val context = ContextAmbient.current
    val state = rememberScaffoldState()
    var openDialog by mutableStateOf(false)

    Scaffold(
        scaffoldState = state,
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        state.drawerState.open()
                    }) {
                        Icon(Icons.Filled.Menu)
                    }
                },
                title = {
                    Text(text = stringResource(R.string.app_name))
                },
                actions = {
                    IconButton(onClick = {
                        Toast.makeText(context, "Search", Toast.LENGTH_SHORT).show()
                    }) {
                        Icon(Icons.Filled.Search)
                    }
                }
            )
        },
        drawerShape = RoundedCornerShape(topRight = 10.dp, bottomRight = 10.dp),
        drawerContent = {
            Column(modifier = Modifier.fillMaxHeight()) {
                WithConstraints(modifier = Modifier.fillMaxWidth().height(180.dp)) {
                    Box(modifier = Modifier.fillMaxSize().background(
                        LinearGradient(
                            0.0f to blue200,
                            0.5f to blue500,
                            1.0f to blue700,
                            startX = 0.0f,
                            startY = 0.0f,
                            endX = with(DensityAmbient.current) { constraints.maxWidth.dp.toPx() },
                            endY = with(DensityAmbient.current) { constraints.maxHeight.dp.toPx() }
                        )
                    )) {
                        Box(modifier = Modifier.fillMaxSize().padding(8.dp)) {
                            Column(modifier = Modifier.align(Alignment.BottomStart)) {
                                Image(
                                    asset = imageResource(id = R.drawable.profile),
                                    modifier = Modifier.clip(CircleShape).preferredSize(100.dp)
                                )
                                Text(
                                    text = "Pepijn van den Broek",
                                    modifier = Modifier.padding(
                                        PaddingValues(
                                            0.dp,
                                            8.dp,
                                            8.dp,
                                            8.dp
                                        )
                                    ),
                                    fontWeight = FontWeight(900),
                                    color = Color.White
                                )
                            }
                        }
                    }
                }
                Box(modifier = Modifier.clickable(onClick = {
                    ThemeState.override = true
                    ThemeState.isDark = false
                })) {
                    Text(
                        text = "Light Theme",
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
                        textAlign = TextAlign.Start
                    )
                }
                Divider(color = MaterialTheme.colors.onBackground)
                Box(modifier = Modifier.clickable(onClick = {
                    ThemeState.override = true
                    ThemeState.isDark = true
                })) {
                    Text(
                        text = "Dark Theme",
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
                        textAlign = TextAlign.Start
                    )
                }
                Divider(color = MaterialTheme.colors.onBackground)
            }
        },
        bottomBar = {
            BottomAppBar(cutoutShape = fabShape) {
                IconButton(onClick = {
                    Toast.makeText(context, "Home", Toast.LENGTH_SHORT).show()
                }) {
                    Icon(Icons.Filled.Home)
                }
                Spacer(Modifier.weight(1f, true))
                IconButton(onClick = {
                    Toast.makeText(context, "Profile", Toast.LENGTH_SHORT).show()
                }) {
                    Icon(Icons.Filled.Person)
                }
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        floatingActionButton = {
            FloatingActionButton(
                shape = fabShape,
                onClick = { openDialog = true }
            ) {
                Icon(asset = Icons.Filled.Add)
            }
        },
        bodyContent = {
            NatureList(natureList = images)
            if (openDialog) {
                AlertDialog(
                    onDismissRequest = {
                        openDialog = false
                    },
                    title = {
                        Text(text = "New Nature")
                    },
                    text = {
                        Text("Add new nature alert dialog!")
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
    )
}

@Composable
private fun NatureList(natureList: List<Nature>) {
    val context = ContextAmbient.current
    LazyColumnFor(
        items = natureList,
        modifier = Modifier.fillMaxSize().padding(PaddingValues(0.dp, 0.dp, 0.dp, 0.dp))
    ) { nature ->
        NatureRow(nature = nature, onNatureClick = {
            Toast.makeText(context, "Nature ${nature.id}", Toast.LENGTH_SHORT).show()
        })
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
fun DefaultPreview() {
    Pepijn98Theme {
        App()
    }
}