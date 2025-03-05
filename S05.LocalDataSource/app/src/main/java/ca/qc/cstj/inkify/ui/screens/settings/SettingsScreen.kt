package ca.qc.cstj.inkify.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.sharp.CheckCircle
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ca.qc.cstj.inkify.R
import ca.qc.cstj.inkify.core.Constants
import ca.qc.cstj.inkify.core.toColor
import ca.qc.cstj.inkify.ui.components.TopBar


@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = viewModel(),
    navigateUp: () -> Unit
) {

    val uiState = viewModel.uiState.collectAsState().value

    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        TopBar(
            title = stringResource(R.string.app_name),
            navigateUp = navigateUp
        )
    }) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxHeight()
        ) {
            OutlinedTextField(modifier = Modifier.fillMaxWidth(),
                value = uiState.settings.name,
                onValueChange = { newName ->
                    viewModel.updateName(newName)
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = { }),
                label = { Text(text = "Name") },
                leadingIcon = {
                    Icon(
                        Icons.Filled.AccountCircle,
                        "error",
                        tint = MaterialTheme.colorScheme.outline
                    )
                })

            LazyVerticalGrid(
                modifier = Modifier,
                contentPadding = PaddingValues(8.dp),
                columns = GridCells.Fixed(4)
            ) {
                items(Constants.NOTES_COLORS) {
                    Box(modifier = Modifier
                        .padding(
                            end = 8.dp, top = 8.dp
                        )
                        .size(80.dp)
                        .clip(CircleShape)
                        .background(it.toColor)
                        .clickable {
                            viewModel.updateColor(it)
                        }) {
                        if (uiState.settings.noteDefaultColor == it) {
                            Icon(
                                imageVector = Icons.Sharp.CheckCircle,
                                contentDescription = Icons.Sharp.CheckCircle.toString(),
                                tint = Color.Black,
                                modifier = Modifier.align(
                                    Alignment.Center
                                )
                            )
                        }
                    }
                }
            }

            ElevatedButton(onClick = { viewModel.reset() }) {
                Text(text = stringResource(R.string.reset))
            }
        }
    }
}