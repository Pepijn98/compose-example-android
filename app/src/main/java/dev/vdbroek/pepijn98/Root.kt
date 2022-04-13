package dev.vdbroek.pepijn98

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
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
import dev.vdbroek.pepijn98.models.Nature
import dev.vdbroek.pepijn98.utils.Alert
import dev.vdbroek.pepijn98.views.home.Home
import dev.vdbroek.pepijn98.views.home.NatureDetails
import dev.vdbroek.pepijn98.views.profile.Profile

val fabShape = CircleShape
var openDialog: Boolean by mutableStateOf(false)
var title: String? by mutableStateOf(null)

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

                val natureListState = rememberScrollState()
                val natureCarouselState = rememberLazyListState()

//                title = stringResource(id = R.string.app_name)

                fun modifyBackStack(routing: Routing) {
                    // If route is second-last in the backstack use pop instead of push
                    if (backStack.lastIndex != 0 && backStack.elements[backStack.lastIndex - 1] == routing) {
                        backStack.pop()
                    } else {
                        backStack.push(routing)
                    }
                }

                // Hide FAB on nature details screen
                val fabHidden = backStack.last() is Routing.NatureDetails

                // Default scaffold which holds all the content
                Scaffold(
                    scaffoldState = state,
                    topBar = {
                        TopBar(state = state, title = title)
                    },
                    drawerShape = RoundedCornerShape(topEnd = 10.dp, bottomEnd = 10.dp),
                    drawerContent = { Drawer() },
                    bottomBar = {
                        BottomBar(hasCutout = !fabHidden) {
                            // Only modify the backstack if the last route isn't the same as the one it's trying to go to
                            if (backStack.last() != it) {
                                modifyBackStack(it)
                            }
                        }
                    },
                    floatingActionButtonPosition = FabPosition.Center,
                    isFloatingActionButtonDocked = true,
                    floatingActionButton = {
                        if (!fabHidden) {
                            FloatingActionButton(
                                shape = fabShape,
                                onClick = { openDialog = true }
                            ) {
                                Icon(imageVector = Icons.Filled.Add, contentDescription = null)
                            }
                        }
                    },
                    snackbarHost = {
                        Alert(
                            hostState = it,
                            onDismiss = {
                                it.currentSnackbarData?.dismiss()
                            }
                        )
                    },
                    content = {
                        // Changes the body content based on which route we're on
                        when (val routing = backStack.last()) {
                            is Routing.Home -> Home.Content(natureListState, natureCarouselState) {
                                modifyBackStack(Routing.NatureDetails(it))
                            }
                            is Routing.NatureDetails -> NatureDetails.Content(routing.nature)
                            is Routing.Profile -> Profile.Content()
                        }
                    }
                )
            }
        }
    }
}
