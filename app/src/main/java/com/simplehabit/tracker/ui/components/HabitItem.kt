package com.simplehabit.tracker.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.simplehabit.tracker.data.Habit

@Composable
fun HabitItem(habit: Habit, onToggle: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = habit.name, style = MaterialTheme.typography.bodyLarge)
            Button(onClick = onToggle) { Text("Done") }
        }
    }
}
