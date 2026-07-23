import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val CreativeDarkColors = darkColorScheme(
    primary = Color(0xFFA855F7),
    onPrimary = Color.White,
    primaryContainer = Color(0xFF3B0764),
    onPrimaryContainer = Color(0xFFE9D5FF),
    secondary = Color(0xFF06B6D4),
    onSecondary = Color.Black,
    background = Color(0xFF0B0F19),
    onBackground = Color(0xFFF1F5F9),
    surface = Color(0xFF151D2A),
    onSurface = Color(0xFFF1F5F9),
    surfaceVariant = Color(0xFF1E293B),
    onSurfaceVariant = Color(0xFF94A3B8)
)

@Composable
private fun BackIcon(tint: Color, modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val stroke = size.minDimension * 0.09f
        val cx = size.width * 0.6f
        val cy = size.height * 0.5f
        val armX = size.width * 0.28f
        val armY = size.height * 0.28f
        drawLine(tint, Offset(cx, cy - armY), Offset(cx - armX, cy), strokeWidth = stroke, cap = StrokeCap.Round)
        drawLine(tint, Offset(cx - armX, cy), Offset(cx, cy + armY), strokeWidth = stroke, cap = StrokeCap.Round)
        drawLine(tint, Offset(size.width * 0.25f, cy), Offset(size.width * 0.78f, cy), strokeWidth = stroke, cap = StrokeCap.Round)
    }
}

@Composable
private fun MoreVertIcon(tint: Color, modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val r = size.minDimension * 0.07f
        val cx = size.width / 2f
        val spacing = size.height * 0.28f
        val cy = size.height / 2f
        drawCircle(tint, radius = r, center = Offset(cx, cy - spacing))
        drawCircle(tint, radius = r, center = Offset(cx, cy))
        drawCircle(tint, radius = r, center = Offset(cx, cy + spacing))
    }
}

@Composable
private fun PlayIcon(tint: Color, modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height
        val path = Path().apply {
            moveTo(w * 0.28f, h * 0.18f)
            lineTo(w * 0.82f, h * 0.5f)
            lineTo(w * 0.28f, h * 0.82f)
            close()
        }
        drawPath(path, color = tint)
    }
}

@Composable
private fun PauseIcon(tint: Color, modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height
        val barWidth = w * 0.22f
        drawRoundRect(
            color = tint,
            topLeft = Offset(w * 0.22f, h * 0.18f),
            size = Size(barWidth, h * 0.64f),
            cornerRadius = CornerRadius(barWidth * 0.3f, barWidth * 0.3f)
        )
        drawRoundRect(
            color = tint,
            topLeft = Offset(w * 0.56f, h * 0.18f),
            size = Size(barWidth, h * 0.64f),
            cornerRadius = CornerRadius(barWidth * 0.3f, barWidth * 0.3f)
        )
    }
}

@Composable
private fun SkipPreviousIcon(tint: Color, modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height
        drawRoundRect(
            color = tint,
            topLeft = Offset(w * 0.2f, h * 0.2f),
            size = Size(w * 0.1f, h * 0.6f),
            cornerRadius = CornerRadius(w * 0.02f, w * 0.02f)
        )
        val path = Path().apply {
            moveTo(w * 0.78f, h * 0.2f)
            lineTo(w * 0.78f, h * 0.8f)
            lineTo(w * 0.32f, h * 0.5f)
            close()
        }
        drawPath(path, color = tint)
    }
}

@Composable
private fun SkipNextIcon(tint: Color, modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height
        drawRoundRect(
            color = tint,
            topLeft = Offset(w * 0.7f, h * 0.2f),
            size = Size(w * 0.1f, h * 0.6f),
            cornerRadius = CornerRadius(w * 0.02f, w * 0.02f)
        )
        val path = Path().apply {
            moveTo(w * 0.22f, h * 0.2f)
            lineTo(w * 0.22f, h * 0.8f)
            lineTo(w * 0.68f, h * 0.5f)
            close()
        }
        drawPath(path, color = tint)
    }
}

@Composable
private fun ReplayForwardIcon(label: String, forward: Boolean, tint: Color, modifier: Modifier = Modifier) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier.matchParentSize()) {
            val stroke = size.minDimension * 0.09f
            val arcSize = Size(size.width * 0.72f, size.height * 0.72f)
            val topLeft = Offset((size.width - arcSize.width) / 2f, (size.height - arcSize.height) / 2f)
            val startAngle = if (forward) -70f else 250f
            val sweep = if (forward) 250f else -250f
            drawArc(
                color = tint,
                startAngle = startAngle,
                sweepAngle = sweep,
                useCenter = false,
                topLeft = topLeft,
                size = arcSize,
                style = Stroke(width = stroke, cap = StrokeCap.Round)
            )
        }
        Text(text = label, color = tint, fontSize = 10.sp, fontWeight = FontWeight.Bold)
    }
}

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
                            BackIcon(
                                tint = MaterialTheme.colorScheme.onBackground,
                                modifier = Modifier.size(24.dp).semantics { contentDescription = "Back" }
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = {}) {
                            MoreVertIcon(
                                tint = MaterialTheme.colorScheme.onBackground,
                                modifier = Modifier.size(24.dp).semantics { contentDescription = "Options" }
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    )
                )

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
                                SkipPreviousIcon(
                                    tint = MaterialTheme.colorScheme.onSurface,
                                    modifier = Modifier.size(24.dp).semantics { contentDescription = "Previous Paragraph" }
                                )
                            }

                            IconButton(onClick = {}) {
                                ReplayForwardIcon(
                                    label = "10",
                                    forward = false,
                                    tint = MaterialTheme.colorScheme.onSurface,
                                    modifier = Modifier.size(24.dp).semantics { contentDescription = "Rewind 10 seconds" }
                                )
                            }

                            FilledIconButton(
                                onClick = { isPlaying = !isPlaying },
                                modifier = Modifier.size(64.dp),
                                shape = CircleShape,
                                colors = IconButtonDefaults.filledIconButtonColors(
                                    containerColor = MaterialTheme.colorScheme.primary,
                                    contentColor = MaterialTheme.colorScheme.onPrimary
                                )
                            ) {
                                if (isPlaying) {
                                    PauseIcon(
                                        tint = MaterialTheme.colorScheme.onPrimary,
                                        modifier = Modifier.size(32.dp).semantics { contentDescription = "Pause" }
                                    )
                                } else {
                                    PlayIcon(
                                        tint = MaterialTheme.colorScheme.onPrimary,
                                        modifier = Modifier.size(32.dp).semantics { contentDescription = "Play" }
                                    )
                                }
                            }

                            IconButton(onClick = {}) {
                                ReplayForwardIcon(
                                    label = "10",
                                    forward = true,
                                    tint = MaterialTheme.colorScheme.onSurface,
                                    modifier = Modifier.size(24.dp).semantics { contentDescription = "Forward 10 seconds" }
                                )
                            }

                            IconButton(
                                onClick = { if (currentParagraph < 3) currentParagraph++ }
                            ) {
                                SkipNextIcon(
                                    tint = MaterialTheme.colorScheme.onSurface,
                                    modifier = Modifier.size(24.dp).semantics { contentDescription = "Next Paragraph" }
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