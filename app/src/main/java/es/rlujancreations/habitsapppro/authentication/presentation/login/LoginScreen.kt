package es.rlujancreations.habitsapppro.authentication.presentation.login

import android.icu.text.CaseMap.Title
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import es.rlujancreations.habitsapppro.R
import es.rlujancreations.habitsapppro.authentication.presentation.login.components.LoginForm
import es.rlujancreations.habitsapppro.core.presentation.HabitTitle

/**
 * Created by Raúl L.C. on 9/4/24.
 */

@Composable
fun LoginScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.loginbackground),
            contentDescription = stringResource(id = R.string.cdLoginBackground),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .aspectRatio(1f)
                .graphicsLayer(scaleX = 1.7f, scaleY = 1.7f)
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
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier)
            Spacer(modifier = Modifier)
            HabitTitle(
                title = stringResource(id = R.string.loginTitle),
                modifier = Modifier.padding(horizontal = 32.dp)
            )
            LoginForm()
        }
    }
}