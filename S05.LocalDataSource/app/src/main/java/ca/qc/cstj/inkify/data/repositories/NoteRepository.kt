package ca.qc.cstj.inkify.data.repositories

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import ca.qc.cstj.inkify.models.Note
import kotlinx.coroutines.flow.Flow

//Data Access object
@Dao
interface NoteRepository {

    @Query("SELECT * from notes")
    fun retrieveAll() : Flow<List<Note>>

    @Insert
    suspend fun create(note: Note)

    @Update
    suspend fun update(note: Note)

    @Delete
    suspend fun delete(note: Note)

}