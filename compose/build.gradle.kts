plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.vanniktech.library.publishing)
}

android {
    namespace = "com.akexorcist.adaptivescrolling.compose"
    compileSdk {
        version = release(libs.versions.compileSdk.get().toInt())
    }

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    buildFeatures {
        compose = true
    }
}

kotlin {
    jvmToolchain(21)
}

dependencies {
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.foundation)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)

    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.compose.ui.test.junit4)
    debugImplementation(libs.compose.ui.tooling)
    debugImplementation(libs.compose.ui.test.manifest)
}

mavenPublishing {
    coordinates(
        groupId = project.property("library.group").toString(),
        artifactId = "compose",
        version = project.property("library.version").toString(),
    )

    pom {
        name.set("Adaptive Scrolling - Compose")
        description.set("Adaptive scrolling behavior that automatically adjusts based on content size")

        inceptionYear.set(project.property("library.year").toString())
        url.set(project.property("library.url").toString())
        licenses {
            license {
                name.set(project.property("library.license.name").toString())
                url.set(project.property("library.license.url").toString())
                distribution.set(project.property("library.license.distribution").toString())
            }
        }
        developers {
            developer {
                id.set(project.property("library.developer.id").toString())
                name.set(project.property("library.developer.name").toString())
                url.set(project.property("library.developer.url").toString())
            }
        }
        scm {
            url.set(project.property("library.scm.url").toString())
            connection.set(project.property("library.scm.connection").toString())
            developerConnection.set(project.property("library.scm.devConnection").toString())
        }
    }
    publishToMavenCentral()
    signAllPublications()
}
