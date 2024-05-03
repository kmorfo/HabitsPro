package es.rlujancreations.habitsapppro.authentication.presentation.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import es.rlujancreations.habitsapppro.R
import es.rlujancreations.habitsapppro.authentication.presentation.signup.components.SignUpForm

/**
 * Created by Raúl L.C. on 14/4/24.
 */
@Composable
fun SignUpScreen(
    onSignIn: (userId:String) -> Unit,
    onLogin: () -> Unit,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val state = viewModel.state

    LaunchedEffect(state.isSignedIn) {
        if (state.isSignedIn) {
            onSignIn(state.userId)
        }
    }
    LaunchedEffect(state.login) {
        if (state.login) {
            onLogin()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Image(
            painter = painterResource(id = R.drawable.signup),
            contentDescription = stringResource(id = R.string.cdSignUpBackground)
        )
        SignUpForm(state = state, onEvent = viewModel::onEvent, modifier = Modifier.fillMaxWidth())
    }
    if (state.isLoading)
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
}