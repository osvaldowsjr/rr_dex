package com.osvaldo.rrdex

import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.osvaldo.rrdex.ui.monList.PokemonList
import com.osvaldo.rrdex.ui.pokemonPage.PokemonPage
import com.osvaldo.rrdex.ui.theme.RRDexTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RRDexTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "pokemonList") {
                        composable("pokemonList") {
                            PokemonList(navController)
                        }
                        composable(
                            route = "pokemonPage/{pokemonId}",
                            arguments = listOf(navArgument("pokemonId") {
                                type = NavType.StringType
                            })
                        ) { navBackStackEntry ->
                            navBackStackEntry.arguments?.getString("pokemonId")?.let {
                                PokemonPage(pokemonId = it)
                            }
                        }

                    }

                }
                hideSystemUI()
            }
        }
    }

    private fun hideSystemUI() {

        actionBar?.hide()


        WindowCompat.setDecorFitsSystemWindows(window, false)

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        } else {
            window.insetsController?.apply {
                hide(WindowInsets.Type.statusBars())
                systemBarsBehavior =
                    WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        }
    }
}