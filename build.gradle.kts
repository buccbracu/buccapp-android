// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false

    id("io.realm.kotlin") version ("1.16.0") apply false
    id("com.google.dagger.hilt.android") version ("2.49") apply false
    id("com.google.devtools.ksp") version "1.9.23-1.0.20" apply false
    id("com.google.gms.google-services") version "4.4.2" apply false
}

