import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    @OptIn(org.jetbrains.kotlin.gradle.ExperimentalWasmDsl::class)
    wasmJs {
        moduleName = "cmpApp"
        browser {
            commonWebpackConfig {
                outputFileName = "cmpApp.js"
            }
        }
        binaries.executable()
    }

    sourceSets {
        wasmJsMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.materialIconsExtended)
            implementation(compose.ui)
            implementation(compose.components.resources)
        }
    }
}

// تحسينات أمان الأداء وضغط حجم ملف الـ Wasm لأقصى حد في بناء Production
tasks.withType<org.jetbrains.kotlin.gradle.targets.js.ir.KotlinJsIrLink>().configureEach {
    compilerOptions {
        freeCompilerArgs.add("-Xwasm-enable-array-range-checks=false")
    }
}
