package ru.andreypoltev.em202410.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Green,// Progress indicator,
    onPrimary = White, // text color on the button,
    primaryContainer = Red,
    onPrimaryContainer = Red,
    inversePrimary = Red,
    secondary = Red,
    onSecondary = Red,
    secondaryContainer = Color.Transparent, // selection color in navigation bar
    onSecondaryContainer = Blue, // icon color when selected,
    tertiary = Red,
    onTertiary = Red,
    tertiaryContainer = Red,
    onTertiaryContainer = Red,
    background = Black, // background, literally,
    onBackground = White, // text color, cards icons color for some reason.,
    surface = Color.Transparent, // Color of the topbar in the details pane
    onSurface = White, // color of the text in the navbar WHEN SELECTED!, color of the backbutton in the topbar,,
    surfaceVariant = Red,
    onSurfaceVariant = Grey4, // color of icons in the topbar, color of icons and text in navigatiion bar,

    ///
    surfaceTint = Red,
    inverseSurface = Red,
    inverseOnSurface = Red,
    error = Red,
    onError = Red,
    errorContainer = Red,
    onErrorContainer = Red,
    outline = Red,
    outlineVariant = Red,
    scrim = Red,
    surfaceBright = Red,
    surfaceContainer = Shadows, // background color of the navigation bar
    surfaceContainerHigh = Red,
    surfaceContainerHighest = Red,
    surfaceContainerLow = Red,
    surfaceContainerLowest = Red,
    surfaceDim = Red

)


private val LightColorScheme = lightColorScheme(
    primary = Purple40, secondary = PurpleGrey40, tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)


@Composable
fun Theme(
    darkTheme: Boolean = true,
//    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
    // Dynamic color is available on Android 12+
//    dynamicColor: Boolean = false,
//    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme, content = content
    )
}