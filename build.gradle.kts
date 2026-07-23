plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    @OptIn(org.jetbrains.kotlin.gradle.ExperimentalWasmDsl::class)
    wasmJs {
        compilerOptions {
            sourceMap = false
            freeCompilerArgs.addAll(
                "-Xwasm-enable-array-range-checks=false",
                "-Xwasm-enable-asserts=false"
            )
        }

        browser {
            commonWebpackConfig {
                outputFileName = "cmpApp.js"
            }
        }

        binaries.executable()
    }

    sourceSets {
        wasmJsMain.dependencies {
            implementation(org.jetbrains.compose.runtime)
            implementation(org.jetbrains.compose.foundation)
            implementation(org.jetbrains.compose.material3)
            implementation(org.jetbrains.compose.ui)
            implementation(org.jetbrains.compose.components.resources)
        }
    }
}