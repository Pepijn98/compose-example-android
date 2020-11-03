package dev.vdbroek.pepijn98.common

import android.widget.Toast
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.BottomAppBar
import androidx.compose.material.IconButton
import androidx.compose.material.ScaffoldState
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.res.stringResource
import dev.vdbroek.pepijn98.R
import dev.vdbroek.pepijn98.Root
import dev.vdbroek.pepijn98.fabShape

@Composable
fun TopBar(state: ScaffoldState, title: String?) {
    val context = ContextAmbient.current

    TopAppBar(
        navigationIcon = {
            IconButton(onClick = { state.drawerState.open() }) {
                Icon(asset = Icons.Filled.Menu)
            }
        },
        title = {
            Text(text = title ?: stringResource(id = R.string.app_name))
        },
        actions = {
            IconButton(onClick = { Toast.makeText(context, "Search", Toast.LENGTH_SHORT).show() }) {
                Icon(asset = Icons.Filled.Search)
            }
        }
    )
}

@Composable
fun BottomBar(fabHidden: Boolean, onButtonClicked: (Root.Routing) -> Unit) {
    BottomAppBar(cutoutShape = if (fabHidden) null else fabShape) {
        IconButton(onClick = { onButtonClicked(Root.Routing.Home) }) {
            Icon(asset = Icons.Filled.Home)
        }
        Spacer(modifier = Modifier.weight(1f, true))
        IconButton(onClick = { onButtonClicked(Root.Routing.Profile) }) {
            Icon(asset = Icons.Filled.Person)
        }
    }
}
