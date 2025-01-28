package ca.qc.cstj.firstapplication.ui.screens

import ca.qc.cstj.firstapplication.core.Constants

enum class GameStatus {
    NEW_GAME,
    TOO_HIGH,
    TOO_LOW,
    WIN
}

data class GameUiState(
    val userTry:Int = Constants.DEFAULT_TRY,
    val status: GameStatus = GameStatus.NEW_GAME
)
