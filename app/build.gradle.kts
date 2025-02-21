plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.devtools.ksp") version "1.9.0-1.0.13"
}

android {
    namespace = "com.example.movie"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.movie"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //for picture
    implementation ("com.github.bumptech.glide:glide:4.15.1")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.15.1")
    implementation ("io.coil-kt:coil:2.4.0")
    implementation ("com.squareup.picasso:picasso:2.71828")
    implementation ("de.hdodenhof:circleimageview:3.1.0")

    //for video---------------------------------------------------
    implementation ("androidx.media3:media3-exoplayer:1.0.0")
    implementation ("androidx.media3:media3-ui:1.0.0")
    //for api---------------------------------------------------------------
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.0")

    var retrofit_version= "2.9.0"
    implementation ("com.squareup.retrofit2:retrofit:$retrofit_version")

    implementation ("com.squareup.retrofit2:converter-gson:$retrofit_version")

    implementation ("com.squareup.retrofit2:adapter-rxjava2:2.9.0")

    // Koin Dependency injection
    implementation ("io.insert-koin:koin-android:3.2.0")
    implementation ("io.insert-koin:koin-androidx-workmanager:3.2.0")
    // Local database
    var room_version = "2.5.0"
    implementation ("androidx.room:room-runtime:$room_version")


    ksp ("androidx.room:room-compiler:$room_version")


    implementation ("androidx.room:room-ktx:$room_version")

    //splash animation
    implementation ("com.airbnb.android:lottie:5.0.3")

    // splash screen
    implementation("androidx.core:core-splashscreen:1.0.1")

    //indicator
    implementation("com.tbuonomo:dotsindicator:4.3")
    implementation ("com.pierfrancescosoffritti.androidyoutubeplayer:core:12.1.0")





}