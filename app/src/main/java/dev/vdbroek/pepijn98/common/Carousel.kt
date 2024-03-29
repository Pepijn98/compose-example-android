package dev.vdbroek.pepijn98.common

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.vdbroek.pepijn98.utils.copy
import dev.vdbroek.pepijn98.utils.end
import dev.vdbroek.pepijn98.utils.start

@Composable
fun <T> Carousel(
    listItems: List<T>,
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState(),
    contentPadding: PaddingValues = PaddingValues(0.dp),
    itemSpacing: Dp = 0.dp,
    verticalAlignment: Alignment.Vertical = Alignment.Top,
    itemContent: @Composable LazyItemScope.(index: Int) -> Unit
) {
    val halfSpacing = itemSpacing / 2

    LazyRow(
        modifier = modifier,
        state = state,
        contentPadding = contentPadding.copy(
            start = (contentPadding.start - halfSpacing).coerceAtLeast(0.dp),
            end = (contentPadding.end - halfSpacing).coerceAtLeast(0.dp)
        ),
        verticalAlignment = verticalAlignment
    ) {
        items(listItems.count(), null, itemContent)
    }
}
