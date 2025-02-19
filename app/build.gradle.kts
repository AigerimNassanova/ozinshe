plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs.kotlin") version "2.7.7"
}

android {
    namespace = "com.example.ozinshe"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.ozinshe"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-runtime-ktx:2.7.7")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")

    //Navigation
    val navVersion = "2.7.7"
    implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navVersion")

    //Dots indicator
    implementation("com.tbuonomo:dotsindicator:5.0")

    //android-youtube-player
    implementation("com.pierfrancescosoffritti.androidyoutubeplayer:core:11.1.0")

    implementation("com.google.android.flexbox:flexbox:3.0.0")

    implementation("com.kyleduo.switchbutton:library:2.1.0")

    //ViewModel
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")

    // Coroutines to make the HTTP requests asynchronous(In the background thread)
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    // Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")

    // Okhttp3 for the POST requests
    implementation ("com.squareup.okhttp3:okhttp:5.0.0-alpha.1")

    // Gson (To convert raw JSON to pretty JSON)
    implementation ("com.google.code.gson:gson:2.9.1")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    //Glide
    implementation ("com.github.bumptech.glide:glide:4.12.0")

    implementation ("com.github.chrisbanes:PhotoView:2.3.0")
}