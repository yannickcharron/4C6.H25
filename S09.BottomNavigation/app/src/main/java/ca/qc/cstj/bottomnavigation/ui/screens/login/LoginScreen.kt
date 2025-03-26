package ca.qc.cstj.bottomnavigation.ui.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ca.qc.cstj.bottomnavigation.R


@Composable
fun LoginScreen(
    navigateToMain: () -> Unit
) {
    Scaffold { innerPaddings ->
        Column(
            modifier = Modifier.fillMaxSize().padding(innerPaddings).padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                navigateToMain()
            }) {
                Text(text = stringResource(R.string.open))
            }
            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

}