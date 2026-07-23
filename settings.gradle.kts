pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)

    repositories {
        google()
        mavenCentral()
        maven {
            name = "NodeDistributions"
            url = uri("https://nodejs.org/dist")
        }
    }
}

rootProject.name = "cmp-web-demo"