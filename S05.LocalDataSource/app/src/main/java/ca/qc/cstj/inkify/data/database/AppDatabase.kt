package ca.qc.cstj.inkify.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ca.qc.cstj.inkify.data.repositories.NoteRepository
import ca.qc.cstj.inkify.models.Note

@Database(entities = [Note::class], version = 1, exportSchema = true)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun noteRepository() : NoteRepository

    companion object {
        @Volatile
        private var _instance: AppDatabase? = null

        fun instance(context: Context): AppDatabase = _instance ?: synchronized(this) {
            _instance ?: build(context).also { _instance = it }
        }

        private fun build(context: Context) = Room.databaseBuilder(
            context.applicationContext, AppDatabase::class.java, "inkify_classe"
        ).addCallback(object : Callback() {

        }).build()
    }

}