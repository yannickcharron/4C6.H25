package ca.qc.cstj.inkify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ca.qc.cstj.inkify.ui.navigation.Destination
import ca.qc.cstj.inkify.ui.screens.add.AddNoteScreen
import ca.qc.cstj.inkify.ui.screens.list.NotesListScreen
import ca.qc.cstj.inkify.ui.theme.InkifyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InkifyTheme {
                val navController = rememberNavController()
                NavHost(
                    modifier = Modifier.fillMaxSize(),
                    navController = navController,
                    startDestination = Destination.NotesList
                ) {
                    composable<Destination.NotesList> {
                        NotesListScreen(
                            toAddNoteScreen = { navController.navigate(Destination.AddNote) }
                        )
                    }
                    composable<Destination.AddNote> {
                        AddNoteScreen()
                    }
                    composable<Destination.Settings> {

                    }
                }
            }
        }
    }
}

