package ca.qc.cstj.inkify.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ca.qc.cstj.inkify.data.repositories.NoteRepository
import ca.qc.cstj.inkify.models.Note

@Database(entities = [Note::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {

    abstract fun noteRepository() : NoteRepository

    companion object {
        @Volatile
        private var _instance: AppDatabase? = null

        fun instance(context: Context): AppDatabase = _instance ?: synchronized(this) {
            _instance ?: build(context).also { _instance = it }
        }

        private fun build(context: Context) = Room.databaseBuilder(
            context.applicationContext, AppDatabase::class.java, "inkify"
        ).addCallback(object : Callback() {

        }).build()
    }

}