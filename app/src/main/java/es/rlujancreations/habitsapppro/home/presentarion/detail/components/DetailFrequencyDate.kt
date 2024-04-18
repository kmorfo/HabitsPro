package es.rlujancreations.habitsapppro.home.presentarion.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import es.rlujancreations.habitsapppro.core.presentation.HabitCheckBox
import java.time.DayOfWeek

/**
 * Created by Raúl L.C. on 17/4/24.
 */
@Composable
fun DetailFrequencyDate(
    date: DayOfWeek,
    isChecked: Boolean,
    onCheckedChange: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(Color.White)
            .padding(vertical = 8.dp, horizontal = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = date.name.take(3),
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.5f)
        )
        Spacer(modifier = Modifier.height(8.dp))
        HabitCheckBox(
            isChecked = isChecked,
            onCheckedChange = onCheckedChange,
            modifier = Modifier.semantics { contentDescription = date.name })
    }
}

@Preview
@Composable
fun DetailFrequencyDatePreview() {
    DetailFrequencyDate(
        date = DayOfWeek.FRIDAY,
        isChecked = true,
        onCheckedChange = {},
    )
}