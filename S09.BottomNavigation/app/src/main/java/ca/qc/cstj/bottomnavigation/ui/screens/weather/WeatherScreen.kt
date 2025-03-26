package ca.qc.cstj.bottomnavigation.ui.screens.weather

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.GpsFixed
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import ca.qc.cstj.bottomnavigation.R
import ca.qc.cstj.bottomnavigation.core.Constants
import ca.qc.cstj.bottomnavigation.model.CurrentWeather
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel = viewModel()
) {

    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //TODO:
    }
}


@Composable
fun CurrentWeatherSection(currentWeather: CurrentWeather, onMapClick:() -> Unit) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .fillMaxHeight(0.6f),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = "TODO",
                style = MaterialTheme.typography.headlineMedium
            )
            GlideImage(modifier = Modifier.size(64.dp),
                imageModel = { Constants.NetworkEndPoint.FLAGS_API_URL.format("TODO") })
            Text(
                text = "TODO",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "TODO",
                style = MaterialTheme.typography.bodyMedium
            )
            WeatherImage(currentWeather = currentWeather)
            Text(
                text = "TODO",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
            )
            Text(
                modifier = Modifier.padding(start = 12.dp, end = 12.dp),
                text = "TODO",
                style = MaterialTheme.typography.bodyMedium,
            )
            Text(
                modifier = Modifier.padding(bottom = 4.dp),
                text = "TODO",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                modifier = Modifier.padding(bottom = 4.dp),
                text = "TODO",
                style = MaterialTheme.typography.bodySmall
            )
            IconButton(onClick = {
                onMapClick()
            }) {
                Icon(
                    imageVector = Icons.Default.GpsFixed, contentDescription = null
                )
            }

        }
    }
}

@Composable
private fun SearchField(
    searchText: String, onChangeSearchText: (String) -> Unit, onSearchClick: () -> Unit
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = searchText,
        onValueChange = { onChangeSearchText(it) },
        label = {
            Text(text = stringResource(R.string.search_for_city))
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        maxLines = 1,
        trailingIcon = {
            IconButton(onClick = { onSearchClick() }) {
                Icon(
                    imageVector = Icons.Default.Search, contentDescription = null
                )
            }
        }
    )
}

@Composable
fun WeatherImage(currentWeather: CurrentWeather) {
    //TODO:
}