package ca.qc.cstj.remotedatasource.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource

@Composable
fun painterResourceFromString(resourceName:String) : Painter {
    val context = LocalContext.current
    val drawableId = remember(resourceName) {
        context.resources.getIdentifier(resourceName.lowercase(), "drawable", context.packageName)
    }

    return painterResource(id = drawableId)
}