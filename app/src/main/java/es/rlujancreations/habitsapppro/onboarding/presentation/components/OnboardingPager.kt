package es.rlujancreations.habitsapppro.onboarding.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import es.rlujancreations.habitsapppro.R
import es.rlujancreations.core.presentation.HabitButton
import es.rlujancreations.core.presentation.HabitTitle
import es.rlujancreations.habitsapppro.onboarding.presentation.OnboardingPagerInformation
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

/**
 * Created by Raúl L.C. on 6/4/24.
 */

@ExperimentalFoundationApi
@Composable
fun OnboardingPager(
    pages: List<OnboardingPagerInformation>,
    onFinish: () -> Unit,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState { pages.size }
    val coroutineScope = rememberCoroutineScope()

    Box(modifier = modifier.background(color = Color.White)) {
        HorizontalPager(state = pagerState) { index ->
            val information = pages[index]

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(32.dp))
                es.rlujancreations.core.presentation.HabitTitle(title = stringResource(id = information.title))
                Spacer(modifier = Modifier.height(32.dp))
                Image(
                    painter = painterResource(id = information.image),
                    contentDescription = "onBoarding $index",
                    modifier = Modifier.aspectRatio(1f),
                    contentScale = ContentScale.FillHeight
                )
                Text(
                    text = stringResource(id = information.subtitle).uppercase(),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.tertiary
                    ),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp, start = 16.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (pagerState.currentPage == pages.lastIndex) {
                es.rlujancreations.core.presentation.HabitButton(
                    text = stringResource(id = R.string.btn_getStarted),
                    modifier = Modifier.fillMaxWidth()
                ) { onFinish() }

            } else {
                TextButton(onClick = onFinish) {
                    Text(
                        text = stringResource(id = R.string.btn_skip),
                        style = TextStyle(color = MaterialTheme.colorScheme.tertiary)
                    )
                }
                HorizontalPagerIndicator(
                    pageCount = pages.size,
                    currentPage = pagerState.currentPage,
                    targetPage = pagerState.targetPage,
                    currentPageOffsetFraction = pagerState.currentPageOffsetFraction,
                    activeColor = MaterialTheme.colorScheme.tertiary,
                    inactiveColor = MaterialTheme.colorScheme.primary
                )
                TextButton(onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                }) {
                    Text(
                        text = stringResource(id = R.string.btn_next),
                        style = TextStyle(color = MaterialTheme.colorScheme.tertiary)
                    )
                }
            }
        }
    }
}

@Composable
private fun HorizontalPagerIndicator(
    pageCount: Int,
    currentPage: Int,
    targetPage: Int,
    currentPageOffsetFraction: Float,
    modifier: Modifier = Modifier,
    activeColor: Color = Color.DarkGray,
    inactiveColor: Color = activeColor.copy(alpha = 0.1f),
    unselectedIndicatorSize: Dp = 8.dp,
    selectedIndicatorSize: Dp = 10.dp,
    indicatorCornerRadius: Dp = 2.dp,
    indicatorPadding: Dp = 2.dp
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .wrapContentSize()
            .height(selectedIndicatorSize + indicatorPadding * 2)
    ) {

        // draw an indicator for each page
        repeat(pageCount) { page ->
            // calculate color and size of the indicator
            val (color, size) =
                if (currentPage == page || targetPage == page) {
                    // calculate page offset
                    val pageOffset =
                        ((currentPage - page) + currentPageOffsetFraction).absoluteValue
                    // calculate offset percentage between 0.0 and 1.0
                    val offsetPercentage = 1f - pageOffset.coerceIn(0f, 1f)

                    val size =
                        unselectedIndicatorSize + ((selectedIndicatorSize - unselectedIndicatorSize) * offsetPercentage)

                    activeColor.copy(alpha = offsetPercentage) to size
                } else {
                    inactiveColor to unselectedIndicatorSize
                }

            // draw indicator
            Box(
                modifier = Modifier
                    .padding(
                        horizontal = ((selectedIndicatorSize + indicatorPadding * 2) - size) / 2,
                        vertical = size / 4
                    )
                    .clip(RoundedCornerShape(indicatorCornerRadius))
                    .background(color)
                    .width(size)
                    .height(size / 2)
            )
        }
    }
}