package ca.qc.cstj.inkify.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import ca.qc.cstj.inkify.core.Constants
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true) val idNote: Int = 0,
    val title: String = "",
    val content: String = "",
    val color: String = Constants.NOTES_COLORS.random(),
    //val creationDate: LocalDateTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
)