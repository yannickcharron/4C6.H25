package ca.qc.cstj.bottomnavigation.ui.screens.maps

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import ca.qc.cstj.bottomnavigation.R
import ca.qc.cstj.bottomnavigation.core.BitmapParameters
import ca.qc.cstj.bottomnavigation.core.permission.PermissionRequester
import ca.qc.cstj.bottomnavigation.core.vectorToBitmap
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState


//https://developers.google.com/codelabs/maps-platform/maps-platform-101-compose#0
//https://medium.com/@mahbooberezaee68/how-to-use-google-map-in-jetpack-compose-dc21920b0cb8
//https://google.github.io/accompanist/permissions/
//https://medium.com/@rzmeneghelo/how-to-request-permissions-in-jetpack-compose-a-step-by-step-guide-7ce4b7782bd7
@Composable
fun MapScreen() {

    val permissions = listOf(
        android.Manifest.permission.ACCESS_FINE_LOCATION,
        android.Manifest.permission.ACCESS_COARSE_LOCATION
    )

    var isMapLoaded by remember { mutableStateOf(false) }

    val latLng = LatLng(45.779388,-74.000163)

    val cameraPositionState =  rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(latLng, 10f)
    }

    PermissionRequester(permissions) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                onMapLoaded = { isMapLoaded = true},
                properties = MapProperties(isMyLocationEnabled = true, isTrafficEnabled = true)
            ) {

                val wifiIcon = vectorToBitmap(
                    LocalContext.current, BitmapParameters(
                        id = R.drawable.wifi_24,
                        iconColor = Color.Green.toArgb(),
                        backgroundColor = Color.Black.toArgb()
                    )
                )

                Marker(
                    state = rememberMarkerState(position = latLng),
                    snippet = "${latLng.latitude} ${latLng.longitude}",
                    title = "Cégep",
                    icon = wifiIcon
                )

            }
        }
    }



}