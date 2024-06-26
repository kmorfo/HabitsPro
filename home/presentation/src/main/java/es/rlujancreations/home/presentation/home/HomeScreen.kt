@file:OptIn(ExperimentalMaterial3Api::class)

package es.rlujancreations.home.presentation.home

import android.Manifest
import android.os.Build
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import es.rlujancreations.home.presentation.R
import es.rlujancreations.home.presentation.home.components.HomeAskPermission
import es.rlujancreations.home.presentation.home.components.HomeDateSelector
import es.rlujancreations.home.presentation.home.components.HomeHabit
import es.rlujancreations.home.presentation.home.components.HomeQuote

/**
 * Created by Raúl L.C. on 15/4/24.
 */

@Composable
fun HomeScreen(
    onNewHabit: (userId: String) -> Unit,
    onSettings: () -> Unit,
    onEditHabit: (habitId: String, userId: String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val state = viewModel.state

    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        CenterAlignedTopAppBar(
            title = {
                Text(text = stringResource(R.string.homeScreenTitle))
            },
            navigationIcon = {
                IconButton(onClick = onSettings) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = stringResource(id = R.string.cdSettings)
                    )
                }
            },
        )
    }, floatingActionButton = {
        FloatingActionButton(
            onClick = { onNewHabit(state.userId) },
            containerColor = MaterialTheme.colorScheme.primary,
//            shape = CircleShape
            modifier = Modifier.semantics {
                contentDescription = "Add a new habit"
            }
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = stringResource(id = R.string.createHabit),
                tint = MaterialTheme.colorScheme.tertiary
            )
        }
    }) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            HomeAskPermission(permission = Manifest.permission.POST_NOTIFICATIONS)
        }
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .padding(start = 20.dp), verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(bottom = 20.dp)
        ) {
            item {
                HomeQuote(
                    quote = R.string.quoteText,
                    author = R.string.quoteAuthor,
                    imageId = R.drawable.onboarding1,
                    modifier = Modifier.padding(end = 20.dp)
                )
            }
            item {
                Text(
                    text = stringResource(id = R.string.habits).uppercase(),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.tertiary
                )
            }
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 20.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    HomeDateSelector(
                        selectedDate = state.selectedDate,
                        mainDate = state.currentDate,
                        onDateClick = { viewModel.onEvent(HomeEvent.ChangeDate(it)) },
                        datesToShow = 5
                    )
                }
            }
            items(state.habits) {
                HomeHabit(
                    habit = it,
                    selectedDate = state.selectedDate.toLocalDate(),
                    onCheckedChange = { viewModel.onEvent(HomeEvent.CompleteHabit(it)) },
                    onHabitClick = { onEditHabit(it.id, state.userId) }
                )
            }
        }
    }
}