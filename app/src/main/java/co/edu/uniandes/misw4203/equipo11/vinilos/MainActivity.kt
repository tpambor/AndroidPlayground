package co.edu.uniandes.misw4203.equipo11.vinilos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import co.edu.uniandes.misw4203.equipo11.vinilos.models.VinilosDB
import co.edu.uniandes.misw4203.equipo11.vinilos.ui.theme.VinilosTheme
import co.edu.uniandes.misw4203.equipo11.vinilos.views.NavBar
import co.edu.uniandes.misw4203.equipo11.vinilos.views.NavContent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Room.databaseBuilder(
            this,
            VinilosDB::class.java,
            "vinilos.db"
        ).build()
        VinilosDB.setInstance(db)

        setContent {
            val snackbarHostState = remember { SnackbarHostState() }
            val navController = rememberNavController()
            val currentBackStackEntry by navController.currentBackStackEntryAsState()

            VinilosTheme {
                Scaffold(
                    content = { padding ->
                        Surface(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(padding),
                            color = MaterialTheme.colorScheme.background
                        ) {
                            NavContent(navController, snackbarHostState)
                        }
                    },
                    bottomBar = {
                        NavBar(navController, currentBackStackEntry)
                    },
                    snackbarHost = {
                        SnackbarHost(
                            hostState = snackbarHostState
                        )
                    }
                )
            }
        }
    }
}
