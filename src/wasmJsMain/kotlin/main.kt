import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.configureWebResources

@OptIn(ExperimentalComposeUiApi::class, ExperimentalResourceApi::class)
fun main() {
    // حل مسارات الموارد نسبياً لتعمل الشاشة على GitHub Pages
    configureWebResources {
        resourcePathMapping { path -> "./$path" }
    }

    // ربط الواجهة بعنصر الـ HTML
    ComposeViewport("ComposeTarget") {
        App()
    }
}
