package es.rlujancreations.home.presentation.home.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import es.rlujancreations.home.presentation.R


/**
 * Created by Raúl L.C. on 1/5/24.
 */
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeAskPermission(
    permission: String,

    modifier: Modifier = Modifier
) {
    val permissionState = rememberPermissionState(permission = permission)
    LaunchedEffect(key1 = Unit) {
        permissionState.launchPermissionRequest()
    }

    if (permissionState.status.shouldShowRationale) {
        AlertDialog(
            onDismissRequest = { },
            modifier = modifier,
            confirmButton = {
                es.rlujancreations.core.presentation.HabitButton(
                    text = stringResource(R.string.permissionAccept),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    permissionState.launchPermissionRequest()
                }
            },
            title = { Text(text = stringResource(R.string.permissionTitle)) },
            text = { Text(text = stringResource(R.string.permissionText)) }
        )
    }
}