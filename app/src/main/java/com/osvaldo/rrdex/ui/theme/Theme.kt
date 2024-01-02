package com.osvaldo.rrdex.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    surface = Color(0xFF212121), // Dark surface/background color
    background = Color(0xFF303030), // Dark background color
    primary = Color(0xFFEF5350), // Red primary color
    secondary = Color(0xFF42A5F5), // Blue secondary color
    onSurface = Color.White, // Text color on surface
    onBackground = Color.White, // Text color on background
)

private val LightColorScheme = lightColorScheme(
    surface = Color.White, // Light surface/background color
    background = Color(0xA1F5F5F5), // Light background color
    primary = Color(0xFFEF5350), // Red primary color
    secondary = Color(0xFF42A5F5), // Blue secondary color
    onSurface = Color.Black, // Text color on surface
    onBackground = Color.Black, // Text color on background
)

@Composable
fun RRDexTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}