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

        applyBinaryen {
            binaryenArgs = mutableListOf(
                "--enable-gc",
                "--enable-reference-types",
                "--enable-exception-handling",
                "--enable-bulk-memory",
                "--enable-nontrapping-float-to-int",
                "--closed-world",
                "--inline-functions-with-loops",
                "--traps-never-happen",
                "--fast-math",
                "--strip-debug",
                "--strip-dwarf",
                "--strip-producers",
                "-O3",
                "-O3",
                "--gufa",
                "-O3",
                "-Oz"
            )
        }
    }

    sourceSets {
        wasmJsMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui)
            implementation(libs.compose.components.resources)
        }
    }
}