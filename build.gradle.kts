// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
}

buildscript {
    // ext.hilt_version = '2.40'
    dependencies {
        classpath("com.android.tools.build:gradle:8.2.2")
        //classpath("com.google.dagger:hilt-android-gradle-plugin:$hilt_version")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.51.1")
    }
}