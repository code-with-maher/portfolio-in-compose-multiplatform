import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

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
            implementation(compose.materialIconsExtended)
            implementation(compose.ui)
            implementation(compose.components.resources)
        }
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.targets.js.binaryen.BinaryenExec>().configureEach {
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
        "-Oz"
    )
}
