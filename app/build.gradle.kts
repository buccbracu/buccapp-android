import com.android.build.api.dsl.Packaging
import java.util.Properties


plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)

    id("io.realm.kotlin")
    id("com.google.devtools.ksp")
    id("dagger.hilt.android.plugin")
}

android {

    val remoteProperties = Properties()
    val remotePropertiesFile = rootProject.file("remote.properties")
    if (remotePropertiesFile.exists()) {
        remoteProperties.load(remotePropertiesFile.inputStream())
    }

    buildTypes.forEach { buildType ->
        remoteProperties.forEach { key, value ->
            buildType.buildConfigField("String", key.toString(), "\"$value\"")
        }
    }


    namespace = "com.buccbracu.bucc"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.buccbracu.bucc"
        minSdk = 26
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
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    android{
        buildFeatures{
            buildConfig = true
        }
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.13"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }

    }

}

dependencies {



    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3.android)
    implementation(libs.androidx.animation.graphics.android)
    implementation(libs.ui.test.junit4)
    implementation(libs.espresso.core)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)


    implementation("androidx.compose.material:material-icons-extended:1.6.5")
    implementation("androidx.navigation:navigation-compose:2.7.7")


    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.2")

    // dependency injection
    implementation("com.google.dagger:hilt-android:2.49")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

//    implementation("com.airbnb.android:lottie-compose:5.0.3")
    implementation("com.github.LottieFiles:dotlottie-android:0.5.0")

    // viewing lottie animation
    implementation("androidx.media3:media3-exoplayer:1.4.1")
    implementation("androidx.media3:media3-ui:1.4.1")
    implementation("androidx.media3:media3-common:1.4.1")

    // image from network
    implementation("io.coil-kt.coil3:coil-compose:3.0.4")
    implementation("io.coil-kt.coil3:coil-network-okhttp:3.0.4")

    // youtube player
    implementation("com.pierfrancescosoffritti.androidyoutubeplayer:core:12.1.1")

    // realm databsae
    implementation("org.mongodb:bson-kotlin:5.2.0")
    implementation("io.realm.kotlin:library-base:1.16.0")

    // retrofit
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.11.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.15.1")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
    implementation("com.fleeksoft.ksoup:ksoup:0.2.0")

    // preferences
    implementation("androidx.datastore:datastore-preferences:1.1.1")




    ksp("com.google.dagger:hilt-compiler:2.49")
    ksp("com.squareup.moshi:moshi-kotlin-codegen:1.15.1")


}

