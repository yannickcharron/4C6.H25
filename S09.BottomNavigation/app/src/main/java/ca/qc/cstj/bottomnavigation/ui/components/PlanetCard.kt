package ca.qc.cstj.bottomnavigation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ca.qc.cstj.bottomnavigation.R
import ca.qc.cstj.bottomnavigation.core.Constants
import ca.qc.cstj.bottomnavigation.core.painterResourceFromString
import ca.qc.cstj.bottomnavigation.model.Planet
import ca.qc.cstj.bottomnavigation.model.Portal
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.glide.GlideImageState

@Composable
fun PlanetCard(
    planet: Planet,
    unit: Constants.TemperatureUnit = Constants.TemperatureUnit.Celsius,
    onClick : (Planet) -> Unit = {}
) {

    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable {
                onClick(planet)
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            GlideImage(
                imageModel = { planet.icon },
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Fit,
                    alignment = Alignment.Center
                ),
                onImageStateChanged = {
                    when(it) {
                        is GlideImageState.Failure -> {}
                        GlideImageState.Loading -> {}
                        GlideImageState.None -> {}
                        is GlideImageState.Success -> {}
                    }
                },
                modifier = Modifier.fillMaxWidth(0.4f)
            )
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = planet.name, style = MaterialTheme.typography.headlineMedium)
                Text(
                    text =  when(unit) {
                        Constants.TemperatureUnit.Kelvin -> stringResource(id = R.string.kelvin_format, planet.temperature)
                        Constants.TemperatureUnit.Celsius -> stringResource(id = R.string.celsius_format, planet.temperature)
                        Constants.TemperatureUnit.Fahrenheit -> stringResource(id = R.string.fahrenheit_format, planet.temperature)
                    },
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

    }

}

@Composable
fun PortalCard(portal: Portal) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(modifier = Modifier.size(64.dp).fillMaxWidth(0.3f), painter = painterResourceFromString(portal.affinity), contentDescription="")
            Text(text = portal.position, style = MaterialTheme.typography.headlineLarge)
        }
    }
}