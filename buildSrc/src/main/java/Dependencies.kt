import org.gradle.api.JavaVersion

object SdkVersions {
    const val compileSdkVersion = 33
    const val buildToolsVersion = "31.0.0"
    const val minSdkVersion = 23
    const val targetSdkVersion = 33

    val javaVersion = JavaVersion.VERSION_1_8
    const val jvmTarget = "1.8"
}

object Versions {
    const val appCompat = "1.3.0-alpha01"
    const val coreKtx = "1.5.0-alpha01"
    const val constraintLayout = "2.0.0-rc1"
    const val material = "1.8.0"
    const val annotation = "1.4.0"
    const val legacy = "1.0.0"

    const val junit = "4.+"
    const val ext = "1.1.3"
    const val espresso = "3.4.0"

    const val compose = "1.3.3"

    const val lifecycleRuntime = "2.5.1"

    const val activityCompose = "1.6.1"
}

object TestLibraries{
    const val junit = "junit:junit:${Versions.junit}"
    const val ext = "androidx.test.ext:junit:${Versions.ext}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"

}

object AndroidLibraries {
    // ANDROID
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val annotation = "androidx.annotation:annotation:${Versions.annotation}"
    const val legacy = "androidx.legacy:legacy-support-v4:${Versions.legacy}"

    const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleRuntime}"
    const val materialDesign = "com.google.android.material:material:${Versions.material}"

}

object Compose{
    const val composeUi = "androidx.compose.ui:ui:${Versions.compose}"
    const val composeMaterial = "androidx.compose.material:material:${Versions.compose}"
    const val composeUiPreview = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
    const val activityCompose = "androidx.activity:activity-compose:${Versions.activityCompose}"
}