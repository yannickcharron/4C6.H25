package ca.qc.cstj.bottomnavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import ca.qc.cstj.bottomnavigation.ui.screens.main.MainScreen
import ca.qc.cstj.bottomnavigation.ui.theme.BottomNavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BottomNavigationTheme(dynamicColor = false) {
                MainScreen()
            }
        }
    }
}





