package dev.vdbroek.pepijn98.common

import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import dev.vdbroek.pepijn98.R
import dev.vdbroek.pepijn98.Root
import dev.vdbroek.pepijn98.fabShape
import dev.vdbroek.pepijn98.utils.SnackbarType
import dev.vdbroek.pepijn98.utils.showSnackbar
import kotlinx.coroutines.launch

@Composable
fun TopBar(state: ScaffoldState, title: String? = stringResource(id = R.string.app_name)) {
    val coroutineScope = rememberCoroutineScope()

    TopAppBar(
        navigationIcon = {
            IconButton(onClick = { coroutineScope.launch { state.drawerState.open() } }) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = null)
            }
        },
        title = {
            Text(text = title ?: stringResource(id = R.string.app_name))
        },
        actions = {
            IconButton(onClick = {
                coroutineScope.launch {
                    state.snackbarHostState.showSnackbar("TODO : Search for nature", "X", SnackbarType.WARNING, SnackbarDuration.Indefinite)
                }
            }) {
                Icon(imageVector = Icons.Filled.Search, contentDescription = null)
            }
        }
    )
}

@Composable
fun BottomBar(hasCutout: Boolean, onButtonClicked: (Root.Routing) -> Unit) {
    BottomAppBar(cutoutShape = if (hasCutout) fabShape else null) {
        IconButton(onClick = { onButtonClicked(Root.Routing.Home) }) {
            Icon(imageVector = Icons.Filled.Home, contentDescription = null)
        }
        Spacer(modifier = Modifier.weight(1f, true))
        IconButton(onClick = { onButtonClicked(Root.Routing.Profile) }) {
            Icon(imageVector = Icons.Filled.Person, contentDescription = null)
        }
    }
}
