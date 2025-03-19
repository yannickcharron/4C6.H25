package ca.qc.cstj.remotedatasource.core.composables

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import ca.qc.cstj.remotedatasource.R

@Composable
fun ErrorMessage(
    ex: Exception, @StringRes errorMessageId: Int = 0, onTryAgainClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painterResource(
                id = R.drawable.trex_error
            ), contentDescription = "Error", colorFilter = ColorFilter.tint(Color.Red)
        )
        Text(text = ex.message.toString(), color = Color.Red, fontSize = 14.sp)
        Button(onClick = { onTryAgainClick() }) {
            Text(text = stringResource(R.string.try_again))
        }
    }
}