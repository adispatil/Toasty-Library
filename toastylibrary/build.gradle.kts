plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    `maven-publish`
}

android {
    namespace = "com.aditya.toastylibrary"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
    }
}

// Helper function to get property from multiple sources (local.properties, gradle.properties, or environment)
fun getProperty(key: String, defaultValue: String = ""): String {
    return findProperty(key) as? String
        ?: System.getenv(key)
        ?: defaultValue
}

val libraryVersion = getProperty("libraryVersion", "1.0.0")
val githubOwner = getProperty("githubOwner", "adispatil")
val githubRepo = getProperty("githubRepo", "Toasty-Library")
val githubUsername = getProperty("githubUsername", "adispatil")
val githubToken = getProperty("githubToken", "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")

publishing {
    publications {
        create<MavenPublication>("release") {
            groupId = "com.aditya.toastylibrary"
            artifactId = "toastylibrary"
            version = libraryVersion

            afterEvaluate {
                from(components["release"])
            }

            pom {
                name.set("ToastyLibrary")
                description.set("A simple Android library for displaying toast messages with a prefix")
                url.set("https://github.com/$githubOwner/$githubRepo")

                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }

                developers {
                    developer {
                        id.set(githubUsername)
                        name.set("Aditya Patil")
                    }
                }

                scm {
                    connection.set("scm:git:git://github.com/$githubOwner/$githubRepo.git")
                    developerConnection.set("scm:git:ssh://github.com:$githubOwner/$githubRepo.git")
                    url.set("https://github.com/$githubOwner/$githubRepo")
                }
            }
        }
    }

    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/$githubOwner/$githubRepo")
            credentials {
                username = githubUsername
                password = githubToken
            }
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}