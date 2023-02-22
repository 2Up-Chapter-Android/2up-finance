plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.track.trans"
    compileSdk = SdkVersions.compileSdkVersion

    defaultConfig {
        minSdk = SdkVersions.minSdkVersion
        targetSdk = SdkVersions.targetSdkVersion
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = SdkVersions.jvmTarget
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = SdkVersions.kotlinCompilerVersion
    }
}

dependencies {

    implementation(AndroidLibraries.coreKtx)
    implementation(AndroidLibraries.lifecycleRuntime)
    implementation(Compose.activityCompose)
    implementation(Compose.composeUi)
    implementation(Compose.composeUiPreview)
    implementation(Compose.composeMaterial3)
    implementation(AndroidLibraries.appCompat)
    implementation(AndroidLibraries.material)
    testImplementation(TestLibraries.junit)
    androidTestImplementation(TestLibraries.ext)
    androidTestImplementation(TestLibraries.espresso)
}