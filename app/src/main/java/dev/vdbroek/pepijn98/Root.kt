package dev.vdbroek.pepijn98

import androidx.compose.foundation.Icon
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import com.github.zsoltk.compose.router.Router
import dev.vdbroek.pepijn98.common.*
import dev.vdbroek.pepijn98.models.Nature
import dev.vdbroek.pepijn98.views.home.Home
import dev.vdbroek.pepijn98.views.home.NatureDetails
import dev.vdbroek.pepijn98.views.profile.Profile

val fabShape = CircleShape
var openDialog by mutableStateOf(false)

interface Root {

    sealed class Routing {
        object Home : Routing()
        data class NatureDetails(val nature: Nature) : Routing()
        object Profile : Routing()
    }

    companion object {
        @Composable
        fun Content(defaultRouting: Routing = Routing.Home) {
            Router(defaultRouting) { backStack ->
                val state = rememberScaffoldState()

                val scrollState = rememberScrollState()
                val carouselState = rememberLazyListState()

                val handleRoute: (Routing) -> Unit = {
                    // Only push to the backstack if the last route isn't the same as the one it's trying to go to
                    if (backStack.last() != it) {
                        when (it) {
                            Routing.Home -> backStack.push(Routing.Home)
                            Routing.Profile -> backStack.push(Routing.Profile)
                            else -> {} // Do Nothing
                        }
                    }
                }

                // Default scaffold which holds all the content
                Scaffold(
                    scaffoldState = state,
                    topBar = {
                        TopBar(state = state)
                    },
                    drawerShape = RoundedCornerShape(topRight = 10.dp, bottomRight = 10.dp),
                    drawerContent = {
                        Drawer()
                    },
                    bottomBar = {
                        BottomBar(handleRoute)
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
                        // Changes the body content based on which route we're on
                        when (val routing = backStack.last()) {
                            is Routing.Home -> Home.Content(scrollState, carouselState, onNatureClicked = {
                                backStack.push(Routing.NatureDetails(it))
                            })
                            is Routing.NatureDetails -> NatureDetails.Content(routing.nature)
                            is Routing.Profile -> Profile.Content()
                        }
                    }
                )
            }
        }
    }
}
