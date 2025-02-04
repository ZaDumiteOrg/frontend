plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.example.zadumite_frontend"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.zadumite_frontend"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation ("androidx.core:core-ktx:1.13.1")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.8.6")
    implementation ("androidx.activity:activity-compose:1.9.3")
    implementation (platform("androidx.compose:compose-bom:2023.08.00"))
    implementation ("androidx.compose.ui:ui")
    implementation ("androidx.compose.ui:ui-graphics")
    implementation ("androidx.compose.ui:ui-tooling-preview")
    implementation ("androidx.compose.material3:material3")
    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.2.1")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.6.1")
    androidTestImplementation (platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation ("androidx.compose.ui:ui-test-junit4")
    debugImplementation ("androidx.compose.ui:ui-tooling")
    debugImplementation ("androidx.compose.ui:ui-test-manifest")


    //UI
    implementation ("androidx.compose.material:material")
    implementation("androidx.compose.material3:material3")

    //Navigation
    implementation ("androidx.navigation:navigation-compose:2.8.5")
    implementation ("androidx.compose.ui:ui:1.7.6")
    implementation ("androidx.compose.ui:ui-tooling:1.7.6")

    //Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation ("com.squareup.okhttp3:okhttp:5.0.0-alpha.3")

    //GSON
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    // Koin
    implementation ("io.insert-koin:koin-android:4.0.1")

    // Koin for Jetpack Compose
    implementation ("io.insert-koin:koin-androidx-compose:4.0.1")

    // Compose UI
    implementation ("androidx.compose.ui:ui:1.5.1")

    // Compose runtime with LiveData support
    implementation ("androidx.compose.runtime:runtime-livedata:1.5.1")

    // Lifecycle Runtime for Compose
    implementation ("androidx.lifecycle:lifecycle-runtime-compose:2.6.1")

    //Jetpack Security
    implementation ("androidx.security:security-crypto:1.1.0-alpha06")
    implementation ("androidx.security:security-crypto:1.1.0-alpha06")

    //Data store
    implementation ("androidx.datastore:datastore-preferences:1.1.1")

    //OkHttp
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    //Notifications
    implementation ("androidx.work:work-runtime-ktx:2.8.0")
    implementation ("androidx.core:core-ktx:1.12.0")
    implementation("androidx.compose.material3:material3:1.2.0-alpha12")
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.27.0")
    implementation("com.google.accompanist:accompanist-permissions:0.31.1-alpha")

    //Icons
    implementation(platform("androidx.compose:compose-bom:2025.01.01"))
    implementation ("androidx.compose.material:material-icons-extended")

    // JUnit 5 for unit testing
    testImplementation ("org.junit.jupiter:junit-jupiter:5.10.0")

    // Mockito for mocking
    testImplementation ("org.mockito:mockito-core:4.11.0")
    testImplementation ("org.mockito:mockito-inline:4.11.0")


    // Coroutines Test library for testing suspend functions
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")

}