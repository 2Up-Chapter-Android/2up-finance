import org.gradle.api.JavaVersion

object SdkVersions {
    const val compileSdkVersion = 33
    const val buildToolsVersion = "31.0.0"
    const val minSdkVersion = 23
    const val targetSdkVersion = 33

    val javaVersion = JavaVersion.VERSION_1_8
    const val jvmTarget = "1.8"


    const val kotlinCompilerVersion = "1.4.0"
}

object Versions {
    const val appCompat = "1.6.1"
    const val coreKtx = "1.5.0-alpha01"
    const val constraintLayout = "2.0.0-rc1"
    const val material = "1.8.0"
    const val annotation = "1.4.0"
    const val legacy = "1.0.0"

    const val junit = "4.+"
    const val ext = "1.1.3"
    const val espresso = "3.4.0"

    const val compose = "1.3.3"
    const val material3 = "1.1.0-alpha05"

    const val lifecycleRuntime = "2.5.1"
    const val composeLifecycleRuntime = "2.6.0"

    const val activityCompose = "1.6.1"

    const val timber = "4.7.1"
    const val hyperion = "0.9.34"

    const val hilt = "2.44"
    const val hiltNavigationCompose = "1.0.0"

    const val hawk = "2.0.1"

    const val gson = "2.8.6"
    const val retrofit = "2.9.0"
    const val loggingInterceptor = "4.10.0"

    const val firebaseBom = "31.2.1"
    const val toolBuild = "7.2.2"
    const val googleServices = "4.3.15"
    const val firebaseCrashlyticsGradle = "2.9.4"

    const val materialCompose = "1.3.1"
}

object TestLibraries {
    const val junit = "junit:junit:${Versions.junit}"
    const val ext = "androidx.test.ext:junit:${Versions.ext}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
}

object AndroidLibraries {
    // ANDROID
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val annotation = "androidx.annotation:annotation:${Versions.annotation}"
    const val legacy = "androidx.legacy:legacy-support-v4:${Versions.legacy}"

    const val lifecycleRuntime =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleRuntime}"
    const val materialDesign = "com.google.android.material:material:${Versions.material}"

    //HAWK
    const val hawk = "com.orhanobut:hawk:${Versions.hawk}"

    //RETROFIT
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitGsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val gson = "com.google.code.gson:gson:${Versions.gson}"

    //OKHTTP3
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor}"

}

object Libraries {
    // TIMBER
    val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    //HILT
    const val daggerHilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    const val hiltNavigationCompose = "androidx.hilt:hilt-navigation-compose:${Versions.hiltNavigationCompose}"

    // Hyperion
    val hyperionCore = "com.willowtreeapps.hyperion:hyperion-core:${Versions.hyperion}"
    val hyperionCrash = "com.willowtreeapps.hyperion:hyperion-crash:${Versions.hyperion}"
    val hyperionMeasurement =
        "com.willowtreeapps.hyperion:hyperion-measurement:${Versions.hyperion}"

    val firebaseBom = "com.google.firebase:firebase-bom:${Versions.firebaseBom}"
    val firebaseCrashlytics = "com.google.firebase:firebase-crashlytics-ndk"
    val firebaseAnalytics = "com.google.firebase:firebase-analytics-ktx"
    val toolBuild = "com.android.tools.build:gradle:${Versions.toolBuild}"
    val googleServices = "com.google.gms:google-services:${Versions.googleServices}"
    val firebaseCrashlyticsGradle =
        "com.google.firebase:firebase-crashlytics-gradle:${Versions.firebaseCrashlyticsGradle}"
}

object Compose {
    const val composeUi = "androidx.compose.ui:ui:${Versions.compose}"
    const val composeMaterial = "androidx.compose.material:material:${Versions.materialCompose}"
    const val composeUiPreview = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
    const val activityCompose = "androidx.activity:activity-compose:${Versions.activityCompose}"
    const val composeMaterial3 = "androidx.compose.material3:material3:${Versions.material3}"

    const val composeLifeCycleRuntime = "androidx.lifecycle:lifecycle-runtime-compose:${Versions.composeLifecycleRuntime}"

    // Navigation
    const val navigation = "androidx.navigation:navigation-compose:2.6.0-alpha05"
}

object Modules {
    const val common = ":common"
    const val navigation = ":navigation"
    const val remote = ":remote"
    const val authentication = ":feature:authentication"
}

object Route {
    const val trans = ":feature:trans"
    const val stats = ":feature:stats"
    const val account = ":feature:account"
    const val more = ":feature:more"
}