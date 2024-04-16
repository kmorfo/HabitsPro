package es.rlujancreations.habitsapppro.core.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import es.rlujancreations.habitsapppro.R

/**
 * Created by Raúl L.C. on 16/4/24.
 */

@Composable
fun HabitCheckBox(
    isChecked: Boolean,
    modifier: Modifier = Modifier,
    onCheckedChange: () -> Unit
) {
    val backgroundColor =
        if (isChecked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.background
    Box(
        modifier = modifier
            .size(35.dp)
            .clip(RoundedCornerShape(12.dp))
            .border(
                BorderStroke(1.dp, color = MaterialTheme.colorScheme.tertiary),
                RoundedCornerShape(12.dp)
            )
            .background(color = backgroundColor)
            .clickable { onCheckedChange() },
        contentAlignment = Alignment.Center
    ) {
        if (isChecked)
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = stringResource(id = R.string.cdCheckedCheckbox),
                tint = MaterialTheme.colorScheme.tertiary
            )

    }
}

@Preview(showBackground = true)
@Composable
fun HabitCheckBoxCheckedPreview() {
    HabitCheckBox(isChecked = true, modifier = Modifier, onCheckedChange = {})
}

@Preview(showBackground = true)
@Composable
fun HabitCheckBoxUnCheckedPreview() {
    HabitCheckBox(isChecked = false, modifier = Modifier, onCheckedChange = {})
}