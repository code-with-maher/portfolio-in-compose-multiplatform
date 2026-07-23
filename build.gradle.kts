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
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
        }
    }
}