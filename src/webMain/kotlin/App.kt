import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun App() {
    MaterialTheme {

        var isPlaying by remember { mutableStateOf(false) }
        var currentParagraph by remember { mutableStateOf(0) }
        var page by remember { mutableStateOf(1) }

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {

            Column(
                modifier = Modifier.fillMaxSize()
            ) {

                // الشريط العلوي
                TopAppBar(
                    title = {
                        Text(
                            text = "رحلة إلى عالم الذكاء الاصطناعي",
                            fontWeight = FontWeight.Bold
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = {}) {
                            Icon(
                                Icons.Default.ArrowBack,
                                contentDescription = "رجوع"
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = {}) {
                            Icon(
                                Icons.Default.MoreVert,
                                contentDescription = "خيارات"
                            )
                        }
                    }
                )


                // محتوى الكتاب
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    contentPadding = PaddingValues(20.dp),
                    verticalArrangement = Arrangement.spacedBy(18.dp)
                ) {

                    val paragraphs = listOf(
                        "في بداية هذا العصر ظهرت تقنيات جديدة غيرت طريقة تعامل الإنسان مع المعرفة. أصبح الحاسوب قادراً على فهم النصوص وتحليل البيانات بطريقة لم تكن ممكنة سابقاً.",

                        "الذكاء الاصطناعي ليس مجرد آلة تجيب عن الأسئلة، بل هو مجموعة من الخوارزميات التي تتعلم من المعلومات وتحاول إيجاد أنماط تساعد البشر في حل المشكلات.",

                        "مع تطور التقنية أصبح بإمكان التطبيقات قراءة الكتب وتحويل النصوص إلى أصوات وإنشاء تجارب تفاعلية تجعل المعرفة أقرب وأسهل للجميع.",

                        "المستقبل سيشهد تعاوناً أكبر بين الإنسان والآلة، حيث يستخدم الإنسان الإبداع بينما تساعده الأنظمة الذكية في الحساب والتحليل."
                    )


                    items(paragraphs.size) { index ->

                        val selected = index == currentParagraph

                        Text(
                            text = paragraphs[index],
                            fontSize = 19.sp,
                            lineHeight = 34.sp,
                            textAlign = TextAlign.Justify,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    if (selected)
                                        MaterialTheme.colorScheme.primaryContainer
                                    else
                                        MaterialTheme.colorScheme.surface,
                                    RoundedCornerShape(12.dp)
                                )
                                .padding(14.dp)
                        )
                    }
                }


                // شريط التحكم السفلي
                Surface(
                    tonalElevation = 8.dp,
                    shadowElevation = 8.dp
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            IconButton(
                                onClick = {
                                    if (currentParagraph > 0)
                                        currentParagraph--
                                }
                            ) {
                                Icon(
                                    Icons.Default.SkipPrevious,
                                    contentDescription = "السابق"
                                )
                            }


                            IconButton(onClick = {}) {
                                Icon(
                                    Icons.Default.Replay10,
                                    contentDescription = "رجوع عشر ثواني"
                                )
                            }


                            FilledIconButton(
                                onClick = {
                                    isPlaying = !isPlaying
                                },
                                modifier = Modifier.size(60.dp)
                            ) {

                                Icon(
                                    if (isPlaying)
                                        Icons.Default.Pause
                                    else
                                        Icons.Default.PlayArrow,
                                    contentDescription = "تشغيل"
                                )
                            }


                            IconButton(onClick = {}) {
                                Icon(
                                    Icons.Default.Forward10,
                                    contentDescription = "تقديم عشر ثواني"
                                )
                            }


                            IconButton(
                                onClick = {
                                    currentParagraph++
                                }
                            ) {
                                Icon(
                                    Icons.Default.SkipNext,
                                    contentDescription = "التالي"
                                )
                            }
                        }


                        Spacer(
                            modifier = Modifier.height(8.dp)
                        )


                        Text(
                            text = "الصفحة $page من 20",
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}