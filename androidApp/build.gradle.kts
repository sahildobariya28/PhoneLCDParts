plugins {
    kotlin("multiplatform")
    id("com.android.application")
    id("org.jetbrains.compose")
}

kotlin {
    android()
    sourceSets {
        val androidMain by getting {
            dependencies {
                implementation(project(":shared"))
                implementation("io.insert-koin:koin-android:3.4.0")
            }
        }
    }
}

android {
    compileSdk = 33
    namespace = "com.phone.phonelcdparts.android"

    sourceSets["main"].manifest.srcFile("src/main/AndroidManifest.xml")


    defaultConfig {
        applicationId = "com.phone.phonelcdparts.android"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlin {
        jvmToolchain(11)
    }
}

dependencies {
    implementation(compose.runtime)
    implementation(compose.foundation)
    implementation(compose.material3)
    implementation(compose.preview)
    implementation(compose.uiTooling)

    implementation("com.airbnb.android:lottie-compose:5.2.0")
    implementation("io.ktor:ktor-client-okhttp:2.3.1")
    implementation("com.arkivanov.decompose:decompose:2.0.0-compose-experimental-alpha-02")
    implementation("io.github.xxfast:decompose-router:0.2.1")
}