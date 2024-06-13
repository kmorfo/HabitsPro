package es.rlujancreations.authentication.presentation.signup.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import es.rlujancreations.authentication.presentation.R
import es.rlujancreations.authentication.presentation.signup.SignUpEvent
import es.rlujancreations.authentication.presentation.signup.SignUpState


/**
 * Created by Raúl L.C. on 14/4/24.
 */
@Composable
fun SignUpForm(
    state: SignUpState,
    onEvent: (SignUpEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current

    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        es.rlujancreations.core.presentation.HabitTitle(title = stringResource(id = R.string.signUpTitle))
        Spacer(modifier = Modifier.height(32.dp))
        es.rlujancreations.core.presentation.HabitTextfield(
            value = state.email,
            onValueChange = { onEvent(SignUpEvent.EmailChange(it)) },
            placeholder = stringResource(id = R.string.valueEmail),
            contentDescription = stringResource(id = R.string.cdEmail),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 6.dp)
                .padding(horizontal = 30.dp),
            leadingIcon = Icons.Outlined.Email,
            keyboardOptions = KeyboardOptions(
                autoCorrect = false,
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onAny = {
                focusManager.moveFocus(FocusDirection.Next)
            }),
            errorMessage = state.emailError?.let { stringResource(it) },
            isEnabled = !state.isLoading,
            backgroundColor = Color.White
        )
        Spacer(modifier = Modifier.height(8.dp))
        es.rlujancreations.core.presentation.HabitPasswordTextfield(
            value = state.password,
            onValueChange = { onEvent(SignUpEvent.PasswordChange(it)) },
            contentDescription = stringResource(id = R.string.cdPassword),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 6.dp)
                .padding(horizontal = 30.dp),
            errorMessage = state.passwordError?.let { stringResource(it) },
            isEnabled = !state.isLoading,
            backgroundColor = Color.White,
            keyboardOptions = KeyboardOptions(
                autoCorrect = false,
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onAny = {
                focusManager.clearFocus()
                onEvent(SignUpEvent.SignUp)
            })
        )
        Spacer(modifier = Modifier.height(16.dp))
        es.rlujancreations.core.presentation.HabitButton(
            text = stringResource(id = R.string.createAccountBtn),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            isEnabled = !state.isLoading
        ) {
            onEvent(SignUpEvent.SignUp)
        }
        Spacer(modifier = Modifier.height(24.dp))
        TextButton(onClick = { onEvent(SignUpEvent.LogIn) }) {
            Text(
                text = buildAnnotatedString {
                    append(stringResource(id = R.string.alReadyHaveAccount))
                    withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(stringResource(id = R.string.signIn))
                    }
                },
                color = MaterialTheme.colorScheme.tertiary,
                textDecoration = TextDecoration.Underline
            )
        }
    }
}