//// Top-level build file where you can add configuration options common to all sub-projects/modules.
//buildscript{
//    repositories {
//        google()
//    }
//    dependencies{
//        classpath(libs.androidx.navigation.safe.args.gradle.plugin)
//
//    }
//}
//plugins {
//    alias(libs.plugins.android.application) apply false
//    alias(libs.plugins.kotlin.android) apply false
//    id("com.google.devtools.ksp") version "1.5.30-1.0.0" apply false
//
//}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
//        mavenCentral()
    }
    dependencies {
        classpath(libs.androidx.navigation.safe.args.gradle.plugin)
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    id("com.google.devtools.ksp") version "1.9.24-1.0.20" apply false
}