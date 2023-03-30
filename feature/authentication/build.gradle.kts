plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id ("com.google.dagger.hilt.android")
    id ("kotlin-kapt")
}

android {
    namespace = "com.finance2up.authentication"
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
        sourceCompatibility = SdkVersions.javaVersion
        targetCompatibility = SdkVersions.javaVersion
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
    implementation(Compose.composeMaterial)
    implementation(Compose.composeIcon)
    implementation(AndroidLibraries.appCompat)
    implementation(AndroidLibraries.material)
    implementation(Compose.composeLifeCycleRuntime)

    implementation(Libraries.daggerHilt)
    kapt(Libraries.hiltCompiler)
    implementation(Libraries.hiltNavigationCompose)

    implementation(AndroidLibraries.retrofit)
    implementation(AndroidLibraries.retrofitGsonConverter)
    implementation(AndroidLibraries.gson)
    implementation(AndroidLibraries.loggingInterceptor)

    testImplementation(TestLibraries.junit)
    androidTestImplementation(TestLibraries.ext)
    androidTestImplementation(TestLibraries.espresso)

    implementation(project(Modules.common))
    implementation(project(Modules.navigation))
}