package ca.qc.cstj.inkify.ui.components


import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ca.qc.cstj.inkify.R
import ca.qc.cstj.inkify.core.Constants
import ca.qc.cstj.inkify.core.format
import ca.qc.cstj.inkify.core.toColor
import ca.qc.cstj.inkify.models.Note

@Composable
fun NoteCard(
    note: Note,
    onDeleteNote: (Note) -> Unit
) {

    var deleteDialog by remember {
        mutableStateOf(false)
    }

    ElevatedCard(
        modifier = Modifier
            .padding(vertical = 4.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = note.color.toColor)
    ) {
        Column(
            modifier = Modifier.padding(8.dp), verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(text = note.title, style = MaterialTheme.typography.titleLarge)
            Text(text = note.content)
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = note.creationDate.format(), style = MaterialTheme.typography.bodyMedium)
                Icon(
                    modifier = Modifier.clickable {
                        deleteDialog = true
                    },
                    imageVector = Icons.Default.Delete,
                    contentDescription = stringResource(R.string.delete),
                    tint = Color.Black
                )
            }
        }
    }

    if(deleteDialog) {
        AlertDialog(
            onDismissRequest = {
                deleteDialog = false
            },
            title = { Text(text= stringResource(R.string.title_delete_note)) },
            text = { Text(text = stringResource(R.string.action_cannot_be_undone))},
            confirmButton = {
                TextButton(
                    onClick = {
                        onDeleteNote(note)
                        deleteDialog = false
                    }
                ) {
                    Text(text = stringResource(R.string.delete))
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        deleteDialog = false
                    }
                ) {
                    Text(text = stringResource(R.string.cancel))
                }
            }
        )
    }
}
