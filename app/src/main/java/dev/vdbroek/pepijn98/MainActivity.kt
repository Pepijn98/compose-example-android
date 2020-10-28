package dev.vdbroek.pepijn98

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import dev.vdbroek.pepijn98.ui.Pepijn98Theme

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
            Text(text = "Drawer")
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
            //bodyContent()
        }
    )
}

@Preview
@Composable
fun DefaultPreview() {
    Pepijn98Theme(darkTheme = true) {
        App()
    }
}