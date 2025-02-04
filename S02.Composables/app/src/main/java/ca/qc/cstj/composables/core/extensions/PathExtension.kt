package ca.qc.cstj.composables.core.extensions

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import kotlin.math.abs

fun Path.standardQuadFromTo(from: Offset, to: Offset) {
    quadraticTo(
        from.x,
        from.y,
        abs(from.x + to.x) / 2f,
        abs(from.y + to.y) / 2f
    )
}

fun colorPaths(width: Float, height: Float): Pair<Path, Path> {

    val mediumColorPoints = listOf(
        Offset(-100f, height * 0.3f),
        Offset(width * 0.3f, height * 0.35f),
        Offset(width * 0.45f, height * 0.1f),
        Offset(width * 0.75f, height * 0.6f),
        Offset(width * 1.4f, -height)
    )

    val lightColorsPoints = listOf(
        Offset(-100f, height * 0.35f),
        Offset(width * 0.2f, height * 0.65f),
        Offset(width * 0.50f, height * 0.35f),
        Offset(width * 0.7f, height * 0.95f),
        Offset(width * 1.4f, -height * 0.2f)
    )

    val mediumColoredPath = Path().apply {
        moveTo(mediumColorPoints.first().x, mediumColorPoints.first().y)
        mediumColorPoints.take(lightColorsPoints.size - 1).forEachIndexed { i, offset ->
            standardQuadFromTo(offset, mediumColorPoints[i + 1])
        }

        lineTo(width + 100, height + 50)
        lineTo(-100f, height + 50)
        close()
    }


    val lightColoredPath = Path().apply {
        moveTo(lightColorsPoints.first().x, lightColorsPoints.first().y)
        lightColorsPoints.take(lightColorsPoints.size - 1).forEachIndexed { i, offset ->
            standardQuadFromTo(offset, lightColorsPoints[i + 1])
        }

        lineTo(width + 100, height + 50)
        lineTo(-100f, height + 50)
        close()
    }

    return Pair(mediumColoredPath, lightColoredPath)
}