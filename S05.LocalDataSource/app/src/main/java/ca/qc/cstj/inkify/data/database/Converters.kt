package ca.qc.cstj.inkify.data.database

import androidx.room.TypeConverter
import kotlinx.datetime.LocalDateTime

class Converters {

    //De la base de données vers la mémoire
    @TypeConverter
    fun toLocalDateTime(value: String) : LocalDateTime {
        return LocalDateTime.parse(value)
    }

    //De la mémoire vers la base de données
    @TypeConverter
    fun fromLocalDateTime(dateTime: LocalDateTime) : String {
        return dateTime.toString()
    }

}