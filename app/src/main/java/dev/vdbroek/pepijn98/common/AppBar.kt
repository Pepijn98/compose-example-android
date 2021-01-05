package dev.vdbroek.pepijn98.common

import android.widget.Toast
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.res.stringResource
import dev.vdbroek.pepijn98.R
import dev.vdbroek.pepijn98.Root
import dev.vdbroek.pepijn98.fabShape

@Composable
fun TopBar(state: ScaffoldState, title: String? = stringResource(id = R.string.app_name)) {
    val context = AmbientContext.current

    TopAppBar(
        navigationIcon = {
            IconButton(onClick = { state.drawerState.open() }) {
                Icon(imageVector = Icons.Filled.Menu)
            }
        },
        title = {
            Text(text = title ?: stringResource(id = R.string.app_name))
        },
        actions = {
            IconButton(onClick = { Toast.makeText(context, "Search", Toast.LENGTH_SHORT).show() }) {
                Icon(imageVector = Icons.Filled.Search)
            }
        }
    )
}

@Composable
fun BottomBar(fabHidden: Boolean, onButtonClicked: (Root.Routing) -> Unit) {
    BottomAppBar(cutoutShape = if (fabHidden) null else fabShape) {
        IconButton(onClick = { onButtonClicked(Root.Routing.Home) }) {
            Icon(imageVector = Icons.Filled.Home)
        }
        Spacer(modifier = Modifier.weight(1f, true))
        IconButton(onClick = { onButtonClicked(Root.Routing.Profile) }) {
            Icon(imageVector = Icons.Filled.Person)
        }
    }
}
