package ca.qc.cstj.inkify.data.repositories

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import ca.qc.cstj.inkify.core.Constants
import ca.qc.cstj.inkify.models.InkifySettings
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "inkify_settings")
class SettingsRepository(
    private val context: Context
) {

    object PreferencesKeys {
        val NAME = stringPreferencesKey("name")
        val DEFAULT_NOTE_COLOR = stringPreferencesKey("default_note_color")
        // Haut ou bas dans le TP !!!!PAS LES DEUX
        //SI COPIÉ VOUS AVEZ ZÉRO POUR LA SESSION ET LES COURS DE L'ANNÉE PROCHAINE
        val JSON_SETTINGS = stringPreferencesKey("json_settings")
    }

    val settings = context.dataStore.data.map {
        val name = it[PreferencesKeys.NAME] ?: ""
        val defaultNoteColor = it[PreferencesKeys.DEFAULT_NOTE_COLOR] ?: Constants.NOTES_COLORS.random()
        InkifySettings(name, defaultNoteColor)
    }

    val jsonSettings = context.dataStore.data.map {
        val jsonSettings = it[PreferencesKeys.JSON_SETTINGS] ?: Json.encodeToString(InkifySettings())
        //decodeFromString -> StringJson en object de type T
        Json.decodeFromString<InkifySettings>(jsonSettings)
    }

    suspend fun save(settings: InkifySettings) {
        context.dataStore.edit {
            it[PreferencesKeys.NAME] = settings.name
            it[PreferencesKeys.DEFAULT_NOTE_COLOR] = settings.noteDefaultColor
            it[PreferencesKeys.JSON_SETTINGS] = Json.encodeToString(settings)
        }
    }

    suspend fun reset() {
        context.dataStore.edit {
            it[PreferencesKeys.NAME] = ""
            it[PreferencesKeys.DEFAULT_NOTE_COLOR] = Constants.NOTES_COLORS.random()
            it[PreferencesKeys.JSON_SETTINGS] = Json.encodeToString(InkifySettings("", Constants.NOTES_COLORS.random()))
        }
    }

}