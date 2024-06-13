package es.rlujancreations.habitsapppro.home.presentarion.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.maxkeppeler.sheets.clock.ClockDialog
import com.maxkeppeler.sheets.clock.models.ClockConfig
import com.maxkeppeler.sheets.clock.models.ClockSelection
import es.rlujancreations.habitsapppro.R
import es.rlujancreations.core.presentation.HabitTextfield
import es.rlujancreations.habitsapppro.home.presentarion.detail.components.DetailFrequency
import es.rlujancreations.habitsapppro.home.presentarion.detail.components.DetailReminder
import java.time.LocalTime

/**
 * Created by Raúl L.C. on 17/4/24.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    onBack: () -> Unit,
    onSave: () -> Unit,
    viewModel: DetailViewModel = hiltViewModel()
) {

    val state = viewModel.state

    LaunchedEffect(state.isSaved) {
        if (state.isSaved) {
            onSave()
        }
    }

    val clockState = com.maxkeppeker.sheets.core.models.base.rememberSheetState()
    ClockDialog(
        state = clockState,
        selection = ClockSelection.HoursMinutes { hours, minutes ->
            viewModel.onEvent(DetailEvent.ReminderChange(LocalTime.of(hours, minutes)))
        },
        config = ClockConfig(
            defaultTime = state.reminder,
            is24HourFormat = true
        )
    )

    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        CenterAlignedTopAppBar(title = {
            Text(text = stringResource(R.string.newHabitTitle))
        }, navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(id = R.string.cdBack)
                )
            }
        })
    }, floatingActionButton = {
        FloatingActionButton(
            onClick = { viewModel.onEvent(DetailEvent.HabitSave) },
            containerColor = MaterialTheme.colorScheme.primary,
//            shape = CircleShape
        ) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = stringResource(id = R.string.saveHabit),
                tint = MaterialTheme.colorScheme.tertiary
            )
        }
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(horizontal = 20.dp), verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            es.rlujancreations.core.presentation.HabitTextfield(
                value = state.habitName,
                onValueChange = { viewModel.onEvent(DetailEvent.NameChange(it)) },
                placeholder = stringResource(id = R.string.cdNewHabitTextField),
                contentDescription = stringResource(id = R.string.cdNewHabitTextField),
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = Color.White,
                keyboardOptions = KeyboardOptions(autoCorrect = false, imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions { viewModel.onEvent(DetailEvent.HabitSave) }
            )
            DetailFrequency(
                selectedDays = state.frequency,
                onFrequencyChange = { viewModel.onEvent(DetailEvent.FrequencyChange(it)) })
            DetailReminder(reminder = state.reminder, onTimeClick = { clockState.show() })
        }
    }
}
