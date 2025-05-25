package com.rati.sikelon.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Purple700,
    onPrimary = White,
    secondary = Purple300,
    tertiary = Purple100,
    background = Neutral900,
    surface = Neutral700,
    onBackground = White,
    onSurface = White,
    error = Error100,
    onError = White
)

private val LightColorScheme = lightColorScheme(
    primary = Purple500,
    onPrimary = White,
    secondary = Purple300,
    tertiary = Purple100,
    background = Neutral100,
    surface = White,
    onBackground = Black,
    onSurface = Black,
    error = Error100,
    onError = White
)

@Composable
fun SIKELONTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
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

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
