package ca.qc.cstj.composables.ui.screens.title

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import ca.qc.cstj.composables.R
import ca.qc.cstj.composables.ui.theme.ButtonBlue
import ca.qc.cstj.composables.ui.theme.TextWhite

@Composable
fun TitleScreen(
    viewModel: TitleScreenViewModel = viewModel()
) {

    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.kiwiflow),
            contentDescription = stringResource(R.string.app_name)
        )
        Text(text = stringResource(R.string.kiwiflow), style = MaterialTheme.typography.displayLarge)
        OutlinedTextField(
            value = uiState.name,
            onValueChange = { viewModel.updateName(it)},
            label = {
                Text(text = stringResource(R.string.name))
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        OutlinedTextField(
            value = uiState.password,
            onValueChange = { viewModel.updatePassword(it)},
            label = {
                Text(text = stringResource(R.string.password))
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(
                    onClick = {}
                ) {
                    Icon(imageVector = Icons.Filled.Visibility, contentDescription = null)
                }
            }
        )
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = ButtonBlue,
                contentColor = TextWhite
            ),
            onClick = {},
        ) {
        Text(
            text = stringResource(R.string.login),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
    }
    }
}