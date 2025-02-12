package ca.qc.cstj.composables.ui.screens.title

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import ca.qc.cstj.composables.R
import ca.qc.cstj.composables.ui.theme.ButtonBlue
import ca.qc.cstj.composables.ui.theme.TextWhite

@Composable
fun TitleScreen(
    navigateToMain : (String) -> Unit,
    viewModel: TitleScreenViewModel = viewModel()
) {

    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current
    LaunchedEffect(viewModel.sideEffectFlow) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.sideEffectFlow.collect { sideEffect ->
                when(sideEffect) {
                    TitleScreenViewModel.SideEffect.Disconnected -> {
                        Toast.makeText(context, context.getString(R.string.msf_login_error), Toast.LENGTH_LONG).show()
                    }

                    is TitleScreenViewModel.SideEffect.Connected -> {
                        navigateToMain(uiState.name)
                    }
                }

            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically)
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
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            isError = uiState.isError
        )
        OutlinedTextField(
            value = uiState.password,
            onValueChange = { viewModel.updatePassword(it)},
            label = {
                Text(text = stringResource(R.string.password))
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if(uiState.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(
                    onClick = {
                        viewModel.togglePasswordVisibility()
                    }
                ) {
                    if(uiState.isPasswordVisible) {
                        Icon(imageVector = Icons.Filled.VisibilityOff, contentDescription = null)
                    } else {
                        Icon(imageVector = Icons.Filled.Visibility, contentDescription = null)
                    }
                }
            },
            isError = uiState.isError
        )
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = ButtonBlue,
                contentColor = TextWhite
            ),
            onClick = {
                viewModel.login()
                //navigateToMain(uiState.name)
            },
        ) {
        Text(
            text = stringResource(R.string.login),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
    }
    }
}