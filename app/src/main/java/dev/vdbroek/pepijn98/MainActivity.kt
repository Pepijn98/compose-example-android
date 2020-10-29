package dev.vdbroek.pepijn98

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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

    Scaffold(
        scaffoldState = state,
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        state.drawerState.open()
                        Toast.makeText(context, "Menu", Toast.LENGTH_SHORT).show()
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
                onClick = {}
            ) {
                Icon(asset = Icons.Filled.Add)
            }
        },
        bodyContent = {
            ScrollableColumn(modifier = Modifier.padding(PaddingValues(0.dp, 0.dp, 0.dp, 60.dp))) {
                images.forEach { (i, image) ->
                    Card(
                        modifier = Modifier.height(180.dp).fillMaxWidth().padding(8.dp),
                        shape = RoundedCornerShape(8.dp),
                        elevation = 4.dp
                    ) {
                        Row(modifier = Modifier.fillMaxSize()) {
                            Image(
                                modifier = Modifier.height(180.dp).width(180.dp),
                                asset = imageResource(id = image),
                                alignment = Alignment.CenterStart
                            )
                            Text(
                                text = "Nature $i",
                                modifier = Modifier.padding(PaddingValues(16.dp, 8.dp, 0.dp, 0.dp)),
                                fontWeight = FontWeight(900)
                            )
                        }
                    }
                }
            }
        }
    )
}

@Preview
@Composable
fun DefaultPreview() {
    Pepijn98Theme {
        App()
    }
}