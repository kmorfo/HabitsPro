package es.rlujancreations.authentication.presentation.login.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.CircularProgressIndicator
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
import es.rlujancreations.authentication.presentation.login.LoginEvent
import es.rlujancreations.authentication.presentation.login.LoginState


/**
 * Created by Raúl L.C. on 9/4/24.
 */

@Composable
fun LoginForm(
    state: LoginState,
    onEvent: (LoginEvent) -> Unit,
    onSignUp: () -> Unit,
    modifier: Modifier = Modifier
) {

    val focusManager = LocalFocusManager.current

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier.background(Color.White, shape = RoundedCornerShape(20.dp)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.loginFormTitle),
                modifier = Modifier.padding(12.dp),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.tertiary
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .height(1.dp)
                    .background(MaterialTheme.colorScheme.primary)
            )
            es.rlujancreations.core.presentation.HabitTextfield(
                value = state.email,
                onValueChange = { onEvent(LoginEvent.EmailChange(it)) },
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
            )
            es.rlujancreations.core.presentation.HabitPasswordTextfield(
                value = state.password,
                onValueChange = { onEvent(LoginEvent.PasswordChange(it)) },
                contentDescription = stringResource(id = R.string.cdPassword),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 6.dp)
                    .padding(horizontal = 30.dp),
                errorMessage = state.passwordError?.let { stringResource(it) },
                isEnabled = !state.isLoading,
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onAny = {
                    focusManager.clearFocus()
                    onEvent(LoginEvent.Login)
                })
            )
            es.rlujancreations.core.presentation.HabitButton(
                text = stringResource(id = R.string.login),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                isEnabled = !state.isLoading
            ) {
                onEvent(LoginEvent.Login)
            }
            TextButton(onClick = { /*TODO*/ }) {
                Text(
                    text = stringResource(id = R.string.forgotPassword),
                    color = MaterialTheme.colorScheme.tertiary,
                    textDecoration = TextDecoration.Underline
                )
            }
            TextButton(onClick = onSignUp) {
                Text(
                    text = buildAnnotatedString {
                        append(stringResource(id = R.string.createAccount))
                        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                            append(stringResource(id = R.string.register))
                        }
                    },
                    color = MaterialTheme.colorScheme.tertiary,
                    textDecoration = TextDecoration.Underline
                )
            }
        }
        if (state.isLoading) CircularProgressIndicator()
    }
}