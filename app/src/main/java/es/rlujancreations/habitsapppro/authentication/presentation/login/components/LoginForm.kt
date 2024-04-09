package es.rlujancreations.habitsapppro.authentication.presentation.login.components

import androidx.compose.foundation.background
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import es.rlujancreations.habitsapppro.R
import es.rlujancreations.habitsapppro.core.presentation.HabitButton
import es.rlujancreations.habitsapppro.core.presentation.HabitPasswordTextfield
import es.rlujancreations.habitsapppro.core.presentation.HabitTextfield

/**
 * Created by Raúl L.C. on 9/4/24.
 */

@Composable
fun LoginForm(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.background(Color.White, shape = RoundedCornerShape(20.dp)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.loginFormTitle),
            modifier = Modifier.padding(12.dp),
            style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.tertiary
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .height(1.dp)
                .background(MaterialTheme.colorScheme.primary)
        )
        HabitTextfield(
            value = stringResource(id = R.string.valueEmail),
            onValueChange = {},
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
                //TODO Cambiar foco to password
            }),
            errorMessage = null,
            isEnabled = true,
        )
        HabitPasswordTextfield(
            value = stringResource(id = R.string.valuePassword),
            onValueChange = {},
            contentDescription = stringResource(id = R.string.cdPassword),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 6.dp)
                .padding(horizontal = 30.dp),
            errorMessage = null,
            isEnabled = true,
            keyboardOptions = KeyboardOptions(
                autoCorrect = false,
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onAny = {
                //TODO Cambiar foco to password
            })
        )
        HabitButton(
            text = stringResource(id = R.string.login),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            isEnabled = true
        ) {
            //TODO login process
        }
        TextButton(onClick = { /*TODO*/ }) {
            Text(
                text = stringResource(id = R.string.forgotPassword),
                color = MaterialTheme.colorScheme.tertiary,
                textDecoration = TextDecoration.Underline
            )
        }
        TextButton(onClick = { /*TODO*/ }) {
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

}