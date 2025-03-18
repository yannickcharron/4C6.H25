package ca.qc.cstj.remotedatasource.ui.screens.planets.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ca.qc.cstj.remotedatasource.R


@Composable
fun DetailsPlanetScreenWithoutRefresh() {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = stringResource(R.string.portals), style = MaterialTheme.typography.headlineLarge)
    }
}

@Composable
fun PlanetDetailsScreen(
    viewModel: PlanetDetailsViewModel = viewModel()
) {

}