import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// 🎨 Dynamic Dark Cyberpunk / Neon Palette
private val CreativeDarkColors = darkColorScheme(
    primary = Color(0xFFA855F7),           // Vivid Neon Purple
    onPrimary = Color.White,
    primaryContainer = Color(0xFF3B0764),  // Deep Violet Highlight
    onPrimaryContainer = Color(0xFFE9D5FF),
    secondary = Color(0xFF06B6D4),        // Electric Cyan
    onSecondary = Color.Black,
    background = Color(0xFF0B0F19),       // Deep Midnight Slate
    onBackground = Color(0xFFF1F5F9),
    surface = Color(0xFF151D2A),          // Dark Navy Card Surface
    onSurface = Color(0xFFF1F5F9),
    surfaceVariant = Color(0xFF1E293B),
    onSurfaceVariant = Color(0xFF94A3B8)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    var isPlaying by remember { mutableStateOf(false) }
    var currentParagraph by remember { mutableStateOf(0) }
    var page by remember { mutableStateOf(1) }

    MaterialTheme(colorScheme = CreativeDarkColors) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                // Top App Bar
                TopAppBar(
                    title = {
                        Text(
                            text = "Journey into AI Realm",
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = {}) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = MaterialTheme.colorScheme.onBackground
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = {}) {
                            Icon(
                                imageVector = Icons.Default.MoreVert,
                                contentDescription = "Options",
                                tint = MaterialTheme.colorScheme.onBackground
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    )
                )

                // Paragraphs List
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    contentPadding = PaddingValues(20.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    val paragraphs = listOf(
                        "In a rapidly changing world, technology has become an essential part of human life. Intelligent systems empower us to analyze data and discover novel solutions.",
                        "Artificial Intelligence represents a new epoch in software evolution, where systems learn from data and interact with users in far more natural ways.",
                        "Modern reading platforms seamlessly blend text, audio, and smart insights to deliver immersive experiences that make knowledge accessible and captivating.",
                        "The future isn't a battle between human and machine, but a symbiotic partnership where both sides collaborate to achieve extraordinary outcomes."
                    )

                    items(paragraphs.size) { index ->
                        val isHighlighted = currentParagraph == index

                        // Smooth Color Transition
                        val cardBgColor by animateColorAsState(
                            targetValue = if (isHighlighted)
                                MaterialTheme.colorScheme.primaryContainer
                            else
                                MaterialTheme.colorScheme.surface,
                            animationSpec = tween(durationMillis = 300),
                            label = "CardBgColor"
                        )

                        val borderColor by animateColorAsState(
                            targetValue = if (isHighlighted)
                                MaterialTheme.colorScheme.primary
                            else
                                Color.Transparent,
                            animationSpec = tween(durationMillis = 300),
                            label = "BorderColor"
                        )

                        Card(
                            onClick = { currentParagraph = index },
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = cardBgColor),
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(
                                    width = if (isHighlighted) 1.5.dp else 0.dp,
                                    color = borderColor,
                                    shape = RoundedCornerShape(16.dp)
                                )
                        ) {
                            Text(
                                text = paragraphs[index],
                                fontSize = 17.sp,
                                lineHeight = 28.sp,
                                textAlign = TextAlign.Justify,
                                color = if (isHighlighted)
                                    MaterialTheme.colorScheme.onPrimaryContainer
                                else
                                    MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.padding(18.dp)
                            )
                        }
                    }
                }

                // Audio Control Bottom Bar
                Surface(
                    tonalElevation = 12.dp,
                    shadowElevation = 16.dp,
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(
                                onClick = { if (currentParagraph > 0) currentParagraph-- }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.SkipPrevious,
                                    contentDescription = "Previous Paragraph",
                                    tint = MaterialTheme.colorScheme.onSurface
                                )
                            }

                            IconButton(onClick = {}) {
                                Icon(
                                    imageVector = Icons.Default.Replay10,
                                    contentDescription = "Rewind 10 seconds",
                                    tint = MaterialTheme.colorScheme.onSurface
                                )
                            }

                            // Glowing Play/Pause Button
                            FilledIconButton(
                                onClick = { isPlaying = !isPlaying },
                                modifier = Modifier.size(64.dp),
                                shape = CircleShape,
                                colors = IconButtonDefaults.filledIconButtonColors(
                                    containerColor = MaterialTheme.colorScheme.primary,
                                    contentColor = MaterialTheme.colorScheme.onPrimary
                                )
                            ) {
                                Icon(
                                    imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                                    contentDescription = if (isPlaying) "Pause" else "Play",
                                    modifier = Modifier.size(32.dp)
                                )
                            }

                            IconButton(onClick = {}) {
                                Icon(
                                    imageVector = Icons.Default.Forward10,
                                    contentDescription = "Forward 10 seconds",
                                    tint = MaterialTheme.colorScheme.onSurface
                                )
                            }

                            IconButton(
                                onClick = { if (currentParagraph < 3) currentParagraph++ }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.SkipNext,
                                    contentDescription = "Next Paragraph",
                                    tint = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = "Page $page of 20",
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }
    }
}
