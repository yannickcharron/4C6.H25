package ca.qc.cstj.bottomnavigation.ui.screens.barcode

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import ca.qc.cstj.bottomnavigation.R
import ca.qc.cstj.bottomnavigation.core.composables.ErrorMessage
import ca.qc.cstj.bottomnavigation.core.composables.LoadingAnimation
import ca.qc.cstj.bottomnavigation.model.CheckIn
import io.github.g00fy2.quickie.QRResult
import io.github.g00fy2.quickie.ScanCustomCode
import io.github.g00fy2.quickie.config.BarcodeFormat
import io.github.g00fy2.quickie.config.ScannerConfig

@Composable
fun BarcodeScreen(viewModel: BarcodeViewModel = viewModel()) {

    val lifecycleOwner = LocalLifecycleOwner.current
    val lifecycleState by lifecycleOwner.lifecycle.currentStateFlow.collectAsStateWithLifecycle()

    LaunchedEffect(lifecycleState) {
        when (lifecycleState) {
            Lifecycle.State.DESTROYED -> {}
            Lifecycle.State.INITIALIZED -> {}
            Lifecycle.State.CREATED -> {}
            Lifecycle.State.STARTED -> {}
            Lifecycle.State.RESUMED -> {
                viewModel.loadCheckIn()
            }
        }
    }

   //TODO:
    val scanQrCodeLauncher = rememberLauncherForActivityResult(ScanCustomCode()) { qrResult ->
        when(qrResult) {
            is QRResult.QRError -> { /* Message l'utilisateur de l'erreur */ }
            QRResult.QRMissingPermission -> { /* Je n'ai pas permission */ }
            is QRResult.QRSuccess -> {
                //TODO: appelle au viewModel
                viewModel.addCheckIn(qrResult.content.rawValue ?: "")
            }
            QRResult.QRUserCanceled -> {}
        }
    }


    when(val uiState = viewModel.uiState.collectAsStateWithLifecycle().value) {
        BarcodeScreenUiState.Loading -> LoadingAnimation()
        is BarcodeScreenUiState.Error -> ErrorMessage(ex = uiState.exception)
        is BarcodeScreenUiState.Success -> {
            Column(
                modifier = Modifier.padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(onClick = {
                    scanQrCodeLauncher.launch(
                        ScannerConfig.build {
                            setBarcodeFormats(listOf(BarcodeFormat.FORMAT_QR_CODE))
                            setOverlayStringRes(R.string.scan_the_id)
                            setOverlayDrawableRes(R.drawable.planet_24dp)
                            setShowCloseButton(true)
                        }
                    )
                }) {
                    Text(text = stringResource(R.string.check_in))
                }
                LazyColumn(contentPadding = PaddingValues(4.dp)) {
                    items(uiState.checkIns) {
                        CheckInCard(checkIn = it)
                    }
                }


            }
        }
    }
}

@Composable
fun CheckInCard(checkIn: CheckIn) {

    ElevatedCard(modifier = Modifier.padding(bottom = 4.dp)) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(text = checkIn.door.toString(), style = MaterialTheme.typography.headlineMedium)
                Text(text = checkIn.scanId, style = MaterialTheme.typography.headlineMedium)
            }
            Text(text = checkIn.scannedDate)
        }
    }

}