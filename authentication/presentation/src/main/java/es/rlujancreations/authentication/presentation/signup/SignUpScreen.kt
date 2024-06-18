package es.rlujancreations.authentication.presentation.signup

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import es.rlujancreations.authentication.presentation.R
import es.rlujancreations.authentication.presentation.signup.components.SignUpForm

/**
 * Created by Raúl L.C. on 14/4/24.
 */
@Composable
fun SignUpScreen(
    onSignIn: (userId: String) -> Unit,
    onLogin: () -> Unit,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val state = viewModel.state

    LaunchedEffect(state.isSignedIn) {
        if (state.isSignedIn) onSignIn(state.userId)

    }
    LaunchedEffect(state.login) {
        if (state.login) onLogin()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.signup),
                contentDescription = stringResource(id = R.string.cdSignUpBackground),
                modifier = Modifier
                    .aspectRatio(1f)
                    .graphicsLayer(scaleX = 0.9f, scaleY = 0.9f)
            )
            Spacer(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                MaterialTheme.colorScheme.background,
                                MaterialTheme.colorScheme.background
                            )
                        )
                    )
            )
            SignUpForm(
                state = state,
                onEvent = viewModel::onEvent,
                modifier = Modifier.fillMaxSize()
            )
        }
        if (state.isLoading)
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }

        if (state.responseError != null) {
            Toast.makeText(LocalContext.current, state.responseError, Toast.LENGTH_LONG).show()
            viewModel.emptyResponseError()
        }
    }
}