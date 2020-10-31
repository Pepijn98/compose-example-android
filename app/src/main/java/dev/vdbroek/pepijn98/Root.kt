package dev.vdbroek.pepijn98

import androidx.compose.foundation.Icon
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import com.github.zsoltk.compose.router.Router
import dev.vdbroek.pepijn98.common.BottomBar
import dev.vdbroek.pepijn98.common.Drawer
import dev.vdbroek.pepijn98.common.TopBar
import dev.vdbroek.pepijn98.home.Home
import dev.vdbroek.pepijn98.profile.Profile

val fabShape = CircleShape
var openDialog by mutableStateOf(false)

interface Root {

    sealed class Routing {
        object Home : Routing()
        object Profile : Routing()
    }

    companion object {
        @Composable
        fun Content(defaultRouting: Routing = Routing.Home) {
            Router(defaultRouting) { backStack ->
                val state = rememberScaffoldState()
                val listState = rememberLazyListState()

                val onButtonClicked: (Routing) -> Unit = {
                    // pop element from backstack if it's more or equal to 2
                    // I have done this in this example app to prevent a massive backstack of just 2 routes
                    // 2 should be the amount of routes
                    if (backStack.elements.count() >= 2) {
                        backStack.pop()
                    }

                    // Only push to the backstack if the last route isn't the same as the one it's trying to go to
                    if (backStack.last() != it) {
                        when (it) {
                            Routing.Home -> backStack.push(Routing.Home)
                            Routing.Profile -> backStack.push(Routing.Profile)
                        }
                    }
                }

                // Default scaffold which holds all the content
                Scaffold(
                    scaffoldState = state,
                    topBar = { TopBar(state = state) },
                    drawerShape = RoundedCornerShape(topRight = 10.dp, bottomRight = 10.dp),
                    drawerContent = { Drawer() },
                    bottomBar = { BottomBar(onButtonClicked) },
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
                        // Changes the body content based on which route we're on
                        when (backStack.last()) {
                            is Routing.Home -> Home.Content(listState = listState)
                            is Routing.Profile -> Profile.Content()
                        }
                    }
                )
            }
        }
    }
}
