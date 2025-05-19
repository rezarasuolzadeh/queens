plugins {
    id(id = "com.android.application")
    id(id = "kotlin-android")
}

android {
    namespace = "ir.rezarasuolzadeh.queens"
    compileSdk = 35

    defaultConfig {
        applicationId = "ir.rezarasuolzadeh.queens"
        minSdk = 23
        targetSdk = 35
        versionCode = 2
        versionName = "1.1"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(dependencyNotation = fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    val androidxAppCompat = "1.7.0"
    implementation(dependencyNotation = "androidx.appcompat:appcompat:$androidxAppCompat")

    val androidxCore = "1.16.0"
    implementation(dependencyNotation = "androidx.core:core-ktx:$androidxCore")

    val androidxConstraintLayout = "2.2.1"
    implementation(dependencyNotation = "androidx.constraintlayout:constraintlayout:$androidxConstraintLayout")
}