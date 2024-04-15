@file:OptIn(ExperimentalMaterial3Api::class)

package es.rlujancreations.habitsapppro.home.presentarion.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import es.rlujancreations.habitsapppro.R
import es.rlujancreations.habitsapppro.home.presentarion.home.components.HomeDateSelector
import es.rlujancreations.habitsapppro.home.presentarion.home.components.HomeQuote

/**
 * Created by Raúl L.C. on 15/4/24.
 */

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {

    val state = viewModel.state

    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        CenterAlignedTopAppBar(title = {
            Text(text = "Home")
        }, navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = stringResource(id = R.string.cdSettings)
                )
            }
        })
    }) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(20.dp), verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            HomeQuote(
                quote = R.string.quoteText,
                author = R.string.quoteAuthor,
                imageId = R.drawable.onboarding1
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = stringResource(id = R.string.habits).uppercase(),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.tertiary
                )
                HomeDateSelector(
                    selectedDate = state.selectedDate,
                    mainDate = state.currentDate,
                    onDateClick = { viewModel.onEvent(HomeEvent.ChangeDate(it)) }, datesToShow = 3
                )
            }
            Text(text = "Listado de Habitos")
        }
    }
}