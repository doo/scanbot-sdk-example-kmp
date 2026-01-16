rootProject.name = "scanbot-sdk-example-app"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        maven {
            url = uri("https://nexus2-staging.scanbot.io/nexus/content/repositories/snapshots/")
            credentials {
                username = ""
                password = ""
            }
        }
        maven { url = uri("https://nexus.scanbot.io/nexus/content/repositories/releases/") }
        maven { url = uri("https://nexus.scanbot.io/nexus/content/repositories/snapshots/") }
    }
}

include(":composeApp")