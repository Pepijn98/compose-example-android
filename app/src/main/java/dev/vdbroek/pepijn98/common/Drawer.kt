package dev.vdbroek.pepijn98.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.vdbroek.pepijn98.R
import dev.vdbroek.pepijn98.ui.ColorUI
import dev.vdbroek.pepijn98.ui.ThemeState
import dev.vdbroek.pepijn98.utils.px

@Composable
fun Drawer() {
    Column(modifier = Modifier.fillMaxHeight()) {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.linearGradient(
                            0.0f to ColorUI.blue200,
                            0.5f to ColorUI.blue500,
                            1.0f to ColorUI.blue700,
                            start = Offset(x = 0.0f, y = 0.0f),
                            end = Offset(x = constraints.maxWidth.px, y = constraints.maxHeight.px)
                        )
                    )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                ) {
                    Column(modifier = Modifier.align(Alignment.BottomStart)) {
                        Image(
                            painter = painterResource(id = R.drawable.profile),
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(100.dp),
                            contentDescription = null
                        )
                        Text(
                            text = "Pepijn van den Broek",
                            modifier = Modifier.padding(top = 8.dp, end = 8.dp, bottom = 8.dp),
                            fontWeight = FontWeight(900),
                            color = Color.White
                        )
                    }
                }
            }
        }
        DrawerRow(
            title = "Light Theme",
            selected = !ThemeState.isDark,
            onClick = {
                ThemeState.override = true
                ThemeState.isDark = false
            }
        )
        DrawerRow(
            title = "Dark Theme",
            selected = ThemeState.isDark,
            onClick = {
                ThemeState.override = true
                ThemeState.isDark = true
            }
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun DrawerRow(title: String, selected: Boolean, onClick: () -> Unit) {
    val background = if (selected) MaterialTheme.colors.primary.copy(alpha = 0.12f) else Color.Transparent
    val textColor = if (selected) MaterialTheme.colors.primary else MaterialTheme.colors.onSurface
    ListItem(modifier = Modifier
        .clickable(onClick = onClick)
        .background(background)) {
        Text(color = textColor, text = title)
    }
}
