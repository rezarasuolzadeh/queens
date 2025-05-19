buildscript {
    repositories {
        google()
        jcenter()
    }

    dependencies {
        val gradle = "8.6.1"
        classpath("com.android.tools.build:gradle:$gradle")

        val kotlin = "1.9.10"
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}