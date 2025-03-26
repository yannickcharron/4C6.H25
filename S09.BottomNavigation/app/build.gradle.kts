plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.google.devtools.ksp)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.google.maps)
}

android {
    namespace = "ca.qc.cstj.bottomnavigation"
    compileSdk = 35

    defaultConfig {
        applicationId = "ca.qc.cstj.bottomnavigation"
        minSdk = 28
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    implementation(libs.androidx.navigation.compose)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.kotlinx.datetime)

    //Bibliothèque Fuel pour les requêtes HTTP
    implementation(libs.fuel.android)
    implementation(libs.fuel.json)

    //https://github.com/skydoves/landscapist
    implementation(libs.landscapist.glide)
    implementation(libs.landscapist.placeholder)
    implementation(libs.landscapist.animation)


    //https://developers.google.com/codelabs/maps-platform/maps-platform-101-compose#0
    implementation(libs.maps.compose)
    // Google Maps Compose utility library
    implementation(libs.maps.compose.utils)
    // Google Maps Compose widgets library
    implementation(libs.maps.compose.widgets)

    //Code QR => https://github.com/G00fY2/quickie
    //https://barcode.tec-it.com/en/QRCode?data=ycharron
    implementation(libs.quickie.bundled)

    //https://github.com/PierfrancescoSoffritti/android-youtube-player
    implementation(libs.androidyoutubeplayer.core)
    implementation(libs.androidyoutubeplayer.chromecast)

    implementation(libs.accompanist.permissions)


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}

secrets {
    // Optionally specify a different file name containing your secrets.
    // The plugin defaults to "local.properties"
    propertiesFileName = "secrets.properties"

    // A properties file containing default secret values. This file can be
    // checked in version control.
    defaultPropertiesFileName = "local.defaults.properties"
}