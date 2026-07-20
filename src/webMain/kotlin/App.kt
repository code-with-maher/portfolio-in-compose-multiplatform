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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {

    var isPlaying by remember { mutableStateOf(false) }
    var currentParagraph by remember { mutableStateOf(0) }
    var page by remember { mutableStateOf(1) }

    MaterialTheme {

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


                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),

                    contentPadding = PaddingValues(20.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    val paragraphs = listOf(

                        "في عالم يتغير بسرعة كبيرة أصبحت التقنية جزءاً أساسياً من حياة الإنسان. تساعدنا الأنظمة الذكية على تحليل المعلومات واكتشاف حلول جديدة.",

                        "الذكاء الاصطناعي يمثل مرحلة جديدة من تطور البرمجيات، حيث تستطيع البرامج التعلم من البيانات والتفاعل مع المستخدم بطرق أكثر طبيعية.",

                        "تطبيقات القراءة الحديثة يمكن أن تجمع بين النص والصوت والتحليل الذكي لتقديم تجربة مختلفة تجعل المعرفة أكثر سهولة ومتعة.",

                        "المستقبل لن يكون صراعاً بين الإنسان والآلة، بل تعاوناً يساعد فيه كل طرف الآخر للوصول إلى نتائج أفضل."
                    )


                    items(paragraphs.size) { index ->

                        val highlighted = currentParagraph == index

                        Text(
                            text = paragraphs[index],
                            fontSize = 18.sp,
                            lineHeight = 32.sp,
                            textAlign = TextAlign.Justify,

                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    if (highlighted)
                                        MaterialTheme.colorScheme.primaryContainer
                                    else
                                        MaterialTheme.colorScheme.surface,

                                    RoundedCornerShape(12.dp)
                                )
                                .padding(16.dp)
                        )
                    }
                }


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
                                    contentDescription = "رجوع"
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
                                    contentDescription = "تقديم"
                                )
                            }


                            IconButton(
                                onClick = {
                                    if (currentParagraph < 3)
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