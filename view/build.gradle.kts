plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.vanniktech.library.publishing)
}

android {
    namespace = "com.akexorcist.adaptivescrolling.view"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        minSdk = 21

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
}

kotlin {
    jvmToolchain(21)
}

dependencies {
    implementation(libs.androidx.core)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

mavenPublishing {
    coordinates(
        groupId = project.property("library.group").toString(),
        artifactId = "view",
        version = project.property("library.version").toString(),
    )

    pom {
        name.set("Adaptive Scrolling - View")
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
